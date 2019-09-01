package mining.tasks;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import mining.Task;
import mining.Walker;

public class GEWalk extends Task {

    public static final Tile[] PATH_TO_GE = {new Tile(3209, 3220, 2), new Tile(3206, 3217, 2), new Tile(3206, 3213, 2), new Tile(3205, 3209, 2), new Tile(3206, 3208, 1), new Tile(3206, 3208, 0), new Tile(3210, 3209, 0), new Tile(3214, 3210, 0), new Tile(3215, 3214, 0), new Tile(3215, 3218, 0), new Tile(3219, 3218, 0), new Tile(3223, 3218, 0), new Tile(3227, 3218, 0), new Tile(3231, 3218, 0), new Tile(3233, 3222, 0), new Tile(3236, 3225, 0), new Tile(3240, 3225, 0), new Tile(3244, 3225, 0), new Tile(3248, 3225, 0), new Tile(3252, 3225, 0), new Tile(3256, 3227, 0), new Tile(3259, 3230, 0), new Tile(3260, 3234, 0), new Tile(3260, 3238, 0), new Tile(3259, 3242, 0), new Tile(3258, 3246, 0), new Tile(3255, 3249, 0), new Tile(3252, 3252, 0), new Tile(3250, 3256, 0), new Tile(3250, 3260, 0), new Tile(3250, 3264, 0), new Tile(3248, 3268, 0), new Tile(3247, 3272, 0), new Tile(3243, 3274, 0), new Tile(3241, 3278, 0), new Tile(3240, 3282, 0), new Tile(3239, 3286, 0), new Tile(3239, 3290, 0), new Tile(3239, 3294, 0), new Tile(3239, 3298, 0), new Tile(3239, 3302, 0), new Tile(3239, 3306, 0), new Tile(3242, 3309, 0), new Tile(3245, 3312, 0), new Tile(3248, 3315, 0), new Tile(3251, 3318, 0), new Tile(3253, 3322, 0), new Tile(3257, 3322, 0), new Tile(3261, 3322, 0), new Tile(3264, 3325, 0), new Tile(3267, 3328, 0), new Tile(3263, 3330, 0), new Tile(3259, 3330, 0), new Tile(3255, 3330, 0), new Tile(3252, 3333, 0), new Tile(3248, 3335, 0), new Tile(3244, 3335, 0), new Tile(3240, 3335, 0), new Tile(3236, 3335, 0), new Tile(3232, 3336, 0), new Tile(3228, 3338, 0), new Tile(3228, 3342, 0), new Tile(3227, 3346, 0), new Tile(3227, 3350, 0), new Tile(3226, 3354, 0), new Tile(3223, 3357, 0), new Tile(3220, 3360, 0), new Tile(3219, 3364, 0), new Tile(3216, 3367, 0), new Tile(3214, 3371, 0), new Tile(3214, 3375, 0), new Tile(3214, 3379, 0), new Tile(3213, 3383, 0), new Tile(3213, 3387, 0), new Tile(3213, 3391, 0), new Tile(3213, 3395, 0), new Tile(3212, 3399, 0), new Tile(3212, 3403, 0), new Tile(3212, 3407, 0), new Tile(3212, 3411, 0), new Tile(3212, 3415, 0), new Tile(3212, 3419, 0), new Tile(3210, 3423, 0), new Tile(3209, 3427, 0), new Tile(3209, 3431, 0), new Tile(3206, 3434, 0), new Tile(3206, 3438, 0), new Tile(3203, 3441, 0), new Tile(3200, 3444, 0), new Tile(3197, 3447, 0), new Tile(3194, 3450, 0), new Tile(3190, 3451, 0), new Tile(3186, 3453, 0), new Tile(3183, 3456, 0), new Tile(3179, 3456, 0), new Tile(3175, 3459, 0), new Tile(3172, 3462, 0), new Tile(3169, 3465, 0), new Tile(3166, 3468, 0), new Tile(3166, 3472, 0), new Tile(3166, 3476, 0), new Tile(3166, 3480, 0), new Tile(3166, 3484, 0), new Tile(3163, 3487, 0)};
    public static final Tile GE_LOCATION = new Tile(3165,3487,0);
    Walker walker = new Walker(ctx);

    public GEWalk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(GE_LOCATION) > 6;
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(40,60)){
            ctx.movement.running(true);
        }
        if(!ctx.players.local().inMotion() ||ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local())<5){
            walker.walkPath(PATH_TO_GE);
        }
    }
}
