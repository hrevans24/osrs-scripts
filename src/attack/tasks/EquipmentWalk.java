package attack.tasks;

import attack.Task;
import attack.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment;

import java.util.ArrayList;

public class EquipmentWalk extends Task {

    private final int SWORD = 1277;
    private final int NET = 303;
    private final int SHIELD = 1171;
    private final int MATCHES = 590;
    private final int AXE = 1351;
    private final Tile LUMBRIDGE = new Tile(3222, 3219, 0);
    public static final Tile[] path = {new Tile(3222, 3219, 0), new Tile(3218, 3219, 0), new Tile(3215, 3216, 0), new Tile(3215, 3212, 0), new Tile(3211, 3211, 0), new Tile(3207, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};
    private Walker walker = new Walker(ctx);

    public EquipmentWalk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return  ((ctx.inventory.select().id(NET).count() != 1
                || ctx.inventory.select().id(MATCHES).count() != 1
                || ctx.inventory.select().id(AXE).count() != 1)
                || !equipped()) || (equipped() && ctx.inventory.select().count() == 3);
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() > Random.nextInt(50, 90)) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(!equipped()) {
                walker.walkPath(path);
            }else{
                walker.walkPathReverse(path);
            }
        }
    }

    private boolean equipped(){
        return ctx.equipment.itemAt(Equipment.Slot.MAIN_HAND).id() == SWORD && ctx.equipment.itemAt(Equipment.Slot.OFF_HAND).id() == SHIELD;
    }
}
