package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class Fish extends Task {

    private final Tile FISHING_SPOT = new Tile(3244,3153,0);
    private final int FISHING_LOC = 1530;

    public Fish(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()<28 && ctx.players.local().tile().distanceTo(FISHING_SPOT)<6;
    }

    @Override
    public void execute() {
        Npc fishing = ctx.npcs.select().id(FISHING_LOC).nearest().poll();
        fishing.interact("Net");
        Condition.wait(new Callable<Boolean>(){
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 200, 10);
    }
}
