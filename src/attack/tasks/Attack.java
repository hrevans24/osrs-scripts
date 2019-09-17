package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class Attack extends Task {

    final Tile COW_ARENA = new Tile(3259, 3271, 0);
    final int[] COWS = {2790, 2791, 2792, 2793, 2794};

    public Attack(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(COW_ARENA) < 8 && getHealth() > 4;
    }

    @Override
    public void execute() {
        Npc cow = ctx.npcs.select().id(COWS).nearest().poll();
        if (cow.interact("Attack")) {
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation() != -1 || getHealth() < 4;
                }
            }, 2000, 10);
        }
    }

    private int getHealth() {
        return Integer.parseInt(ctx.widgets.component(160, 5).text());
    }
}
