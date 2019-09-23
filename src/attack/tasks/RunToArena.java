package attack.tasks;

import attack.Task;
import attack.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment;

public class RunToArena extends Task {

    public static final Tile[] LUMBRIDGE_TO_ARENA = {new Tile(3221, 3219, 0), new Tile(3225, 3219, 0), new Tile(3229, 3219, 0), new Tile(3233, 3219, 0), new Tile(3237, 3222, 0), new Tile(3240, 3225, 0), new Tile(3244, 3225, 0), new Tile(3248, 3225, 0), new Tile(3252, 3225, 0), new Tile(3256, 3226, 0), new Tile(3259, 3230, 0), new Tile(3259, 3234, 0), new Tile(3259, 3238, 0), new Tile(3259, 3242, 0), new Tile(3258, 3246, 0), new Tile(3255, 3250, 0), new Tile(3253, 3254, 0), new Tile(3252, 3258, 0), new Tile(3250, 3262, 0), new Tile(3252, 3266, 0), new Tile(3254, 3270, 0), new Tile(3256, 3274, 0), new Tile(3259, 3277, 0)};
    private final Tile LUMBRIDGE_LOC = new Tile(3222, 3219, 0);
    Walker walker = new Walker(ctx);

    public RunToArena(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(LUMBRIDGE_LOC) < 6 ;
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(50, 90)) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            walker.walkPath(LUMBRIDGE_TO_ARENA);
        }
    }

    private int getHealth() {
        return Integer.parseInt(ctx.widgets.component(160, 5).text());
    }
}
