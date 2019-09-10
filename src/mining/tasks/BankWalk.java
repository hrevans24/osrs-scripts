package mining.tasks;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import mining.Task;
import mining.Walker;

public class BankWalk extends Task {

    public static final Tile[] PATH_TO_BANK = {new Tile(3229, 3147, 0), new Tile(3231, 3151, 0), new Tile(3233, 3155, 0), new Tile(3236, 3158, 0), new Tile(3237, 3162, 0), new Tile(3237, 3166, 0), new Tile(3237, 3170, 0), new Tile(3239, 3174, 0), new Tile(3240, 3178, 0), new Tile(3240, 3182, 0), new Tile(3240, 3186, 0), new Tile(3243, 3189, 0), new Tile(3244, 3193, 0), new Tile(3241, 3197, 0), new Tile(3239, 3201, 0), new Tile(3236, 3204, 0), new Tile(3235, 3208, 0), new Tile(3235, 3212, 0), new Tile(3234, 3216, 0), new Tile(3230, 3218, 0), new Tile(3226, 3218, 0), new Tile(3222, 3218, 0), new Tile(3218, 3218, 0), new Tile(3215, 3215, 0), new Tile(3215, 3211, 0), new Tile(3211, 3211, 0), new Tile(3207, 3209, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};
    private final Walker walker = new Walker(ctx);

    public BankWalk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 27 || (ctx.inventory.select().count() < 28 && PATH_TO_BANK[0].distanceTo(ctx.players.local()) > 6);
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(40, 60)) {
            ctx.movement.running(true);
        }

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.select().count() > 27) {
                walker.walkPath(PATH_TO_BANK);
            } else {
                walker.walkPathReverse(PATH_TO_BANK);
            }
        }
    }
}
