package mining.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import mining.Task;

import java.util.concurrent.Callable;

public class Mine extends Task {
    final static int[] ROCK_IDS = {11161, 10943};
    Tile rockLocation = Tile.NIL;

    public Mine(ClientContext ctx){
        super(ctx);
    }
    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1 || ctx.objects.select().at(rockLocation).id(ROCK_IDS).poll().equals(ctx.objects.nil());
    }

    @Override
    public void execute() {
        GameObject rockToMine = ctx.objects.select().id(ROCK_IDS).nearest().poll();
        rockLocation = rockToMine.tile();
        rockToMine.interact("Mine");

        Condition.wait(new Callable<Boolean>(){
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 200, 10);
    }
}
