package mining.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import mining.Task;

import java.util.concurrent.Callable;

public class Withdraw extends Task {

    public static final Tile GE_LOCATION = new Tile(3165, 3487, 0);
    public final int COPPER_ORE = 436;

    public Withdraw(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(GE_LOCATION) < 7 && ctx.inventory.select().count() < 1;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            final int INVENTORY_COUNT = ctx.inventory.select().count();
            if (ctx.widgets.component(12, 25).click() && ctx.bank.select().id(COPPER_ORE).poll().interact("Withdraw-All")) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() > INVENTORY_COUNT;
                    }
                }, 250, 20);
            }
            ctx.bank.close();
        } else {
            if (ctx.bank.inViewport()) {
                if (ctx.bank.open()) {
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                } else {
                    ctx.camera.turnTo(ctx.bank.nearest());
                }
            }
        }
    }
}
