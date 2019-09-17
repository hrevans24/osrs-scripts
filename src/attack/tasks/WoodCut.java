package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class WoodCut extends Task {

    private final int LOG = 1511;
    private final int TREE = 1278;

    public WoodCut(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(LOG).count() < 1;
    }

    @Override
    public void execute() {
        GameObject woodToCut = ctx.objects.select().id(TREE).nearest().poll();
        if(woodToCut.interact("Chop down")){
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation() == -1;
                }
            },1500, 20);
        }
    }
}
