package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Magic;

import java.util.concurrent.Callable;

public class LocationCheck extends Task {

    private final Tile CORRECT = new Tile(3222, 3219, 0);

    public LocationCheck(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(CORRECT) > 6;
    }

    @Override
    public void execute() {
        if (ctx.magic.cast(Magic.Spell.HOME_TELEPORT)) {
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation() == -1;
                }
            }, 2500, 20);
        }
    }
}
