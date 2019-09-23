package attack.tasks;

import attack.Task;
import attack.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class FishRun extends Task {

    public static final Tile[] path = {new Tile(3260, 3275, 0), new Tile(3256, 3272, 0), new Tile(3254, 3268, 0), new Tile(3250, 3266, 0), new Tile(3250, 3262, 0), new Tile(3250, 3258, 0), new Tile(3250, 3254, 0), new Tile(3252, 3250, 0), new Tile(3255, 3247, 0), new Tile(3258, 3244, 0), new Tile(3259, 3240, 0), new Tile(3259, 3236, 0), new Tile(3259, 3232, 0), new Tile(3258, 3228, 0), new Tile(3254, 3226, 0), new Tile(3250, 3226, 0), new Tile(3246, 3226, 0), new Tile(3242, 3226, 0), new Tile(3238, 3223, 0), new Tile(3234, 3220, 0), new Tile(3234, 3216, 0), new Tile(3234, 3212, 0), new Tile(3234, 3208, 0), new Tile(3235, 3204, 0), new Tile(3238, 3201, 0), new Tile(3240, 3197, 0), new Tile(3242, 3193, 0), new Tile(3244, 3189, 0), new Tile(3244, 3185, 0), new Tile(3243, 3181, 0), new Tile(3243, 3177, 0), new Tile(3243, 3173, 0), new Tile(3243, 3169, 0), new Tile(3243, 3165, 0), new Tile(3243, 3161, 0), new Tile(3243, 3157, 0), new Tile(3243, 3153, 0), new Tile(3242, 3149, 0)};
    Walker walker = new Walker(ctx);

    public FishRun(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return getHealth() < 4;
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(50, 90)) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            walker.walkPath(path);
        }
    }

    private int getHealth() {
        return Integer.parseInt(ctx.widgets.component(160, 5).text());
    }
}
