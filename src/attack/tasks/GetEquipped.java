package attack.tasks;

import attack.Task;
import attack.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GetEquipped extends Task {

    private final int SWORD = 1277;
    private final int NET = 303;
    private final int SHIELD = 1171;
    private final int MATCHES = 590;
    private final int AXE = 1351;
    private final Tile LUMBRIDGE = new Tile(3222, 3219, 0);
    public static final Tile[] path = {new Tile(3222, 3219, 0), new Tile(3218, 3219, 0), new Tile(3215, 3216, 0), new Tile(3215, 3212, 0), new Tile(3211, 3211, 0), new Tile(3207, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};
    private Equipment.Slot[] slots = Equipment.Slot.values();

    public GetEquipped(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(ctx.bank.nearest().tile()) < 6 && (!equipped() || ctx.inventory.select().count() != 3);
    }

    @Override
    public void execute() {
        if (ctx.game.tab().equals(Game.Tab.EQUIPMENT)) {
            if (!ctx.equipment.isEmpty() && ctx.inventory.select().count() < 28) {
                for (Equipment.Slot slot : slots) {
                    if (!slot.equals(ctx.equipment.nil())) {
                        ctx.equipment.itemAt(slot).interact("Remove");
                    }
                }
            }
            if (ctx.bank.opened()) {
                final int INVENTORY_COUNT = ctx.inventory.select().count();
                if (ctx.bank.depositInventory()) {
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.inventory.select().count() != INVENTORY_COUNT;
                        }
                    }, 250, 20);
                    if(ctx.bank.withdraw(SWORD, 1)){
                        Condition.sleep(500);
                    }if(ctx.bank.withdraw(SHIELD, 1)){
                        Condition.sleep(500);
                    }if(ctx.bank.withdraw(NET, 1)){
                        Condition.sleep(500);
                    }if(ctx.bank.withdraw(MATCHES, 1)){
                        Condition.sleep(500);
                    }if(ctx.bank.withdraw(AXE, 1)){
                        Condition.sleep(500);
                    }
                    if(ctx.bank.close()){
                        equip();
                    }
                }
            } else {
                if (ctx.bank.inViewport()) {
                    if (ctx.bank.open()) {
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return ctx.bank.opened();
                            }
                        }, 250, 20);
                    }
                } else {
                    ctx.camera.turnTo(ctx.bank.nearest());
                }
            }
        } else {
            if (ctx.game.tab(Game.Tab.EQUIPMENT)) {
                Condition.sleep(500);
            }
        }
    }

    private void equip(){
        if(ctx.game.tab(Game.Tab.INVENTORY)) {
            Condition.sleep(300);
        }
        if(ctx.inventory.select().id(SHIELD).poll().interact("Wield")){
            Condition.sleep(300);
        }
        if(ctx.inventory.select().id(SWORD).poll().interact("Wield")){
            Condition.sleep(300);
        }
    }

    private boolean equipped(){
        return ctx.equipment.itemAt(Equipment.Slot.MAIN_HAND).id() == SWORD && ctx.equipment.itemAt(Equipment.Slot.OFF_HAND).id() == SHIELD;
    }
}
