package mining.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import mining.Task;

import java.util.concurrent.Callable;

public class MiningDeposit extends Task {

    public MiningDeposit(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 27 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            final int INVENTORY_COUNT = ctx.inventory.select().count();
            if (ctx.bank.depositInventory()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() != INVENTORY_COUNT;
                    }
                }, 250, 20);
            }
        } else {
            if (ctx.bank.inViewport()) {
                if (ctx.bank.open()) {
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }

        }
    }
}
