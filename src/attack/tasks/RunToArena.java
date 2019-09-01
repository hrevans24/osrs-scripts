package attack.tasks;

import attack.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class RunToArena extends Task {

    public static final Tile[] LUMBRIDGE_TO_ARENA = {new Tile(3221, 3219, 0), new Tile(3225, 3219, 0), new Tile(3229, 3219, 0), new Tile(3233, 3219, 0), new Tile(3237, 3222, 0), new Tile(3240, 3225, 0), new Tile(3244, 3225, 0), new Tile(3248, 3225, 0), new Tile(3252, 3225, 0), new Tile(3256, 3226, 0), new Tile(3259, 3230, 0), new Tile(3259, 3234, 0), new Tile(3259, 3238, 0), new Tile(3259, 3242, 0), new Tile(3258, 3246, 0), new Tile(3255, 3250, 0), new Tile(3253, 3254, 0), new Tile(3252, 3258, 0), new Tile(3250, 3262, 0), new Tile(3252, 3266, 0), new Tile(3254, 3270, 0), new Tile(3256, 3274, 0), new Tile(3259, 3277, 0)};

    public RunToArena(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

    }
}
