package attack.tasks;

import attack.Task;
import attack.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment;

public class GetEquipped extends Task {

    private final int SWORD = 1277;
    private final int NET = 303;
    private final int SHIELD = 1171;
    private final Tile LUMBRIDGE= new Tile(3222, 3219, 0);
    public static final Tile[] path = {new Tile(3222, 3219, 0), new Tile(3218, 3219, 0), new Tile(3215, 3216, 0), new Tile(3215, 3212, 0), new Tile(3211, 3211, 0), new Tile(3207, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};

    public GetEquipped(ClientContext ctx) {
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