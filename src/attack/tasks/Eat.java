package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

public class Eat extends Task {

    final int[] EDIBLES = {2309, 315};

    public Eat(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return getHealth() < 5 && ctx.inventory.contains(ctx.inventory.select().id(EDIBLES).poll());
    }

    @Override
    public void execute() {
        if (ctx.game.tab().equals(Game.Tab.INVENTORY)) {
            if (ctx.inventory.select().id(EDIBLES).poll().interact("Eat")) {
                Condition.sleep(500);
            }
        } else {
            ctx.game.tab(Game.Tab.INVENTORY);
            Condition.sleep(300);
        }
    }

    private int getHealth() {
        return Integer.parseInt(ctx.widgets.component(160, 5).text());
    }
}
