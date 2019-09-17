package attack.tasks;

import attack.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Cook extends Task {

    private final int LOG = 1511;
    private final int TINDER = 590;
    private final int FIRE = 26185;
    private final int SHRIMP = 317;

    public Cook(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(LOG).count() == 1;
    }

    @Override
    public void execute() {
        if(ctx.game.tab().equals(Game.Tab.INVENTORY)){
            if(ctx.inventory.select().id(TINDER).poll().click()){
                Condition.sleep(300);
            }
            if(ctx.inventory.select().id(LOG).poll().click()){
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().animation() == -1;
                    }
                }, 2000, 10);
            }
            GameObject cookingFire = ctx.objects.select().id(FIRE).poll();
            if(ctx.inventory.select().id(SHRIMP).poll().click()){
                Condition.sleep(300);
            }
            if(cookingFire.click()){
                Condition.sleep(300);
            }
            if(ctx.widgets.component(270,14).click()){
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().animation() != -1;
                    }
                },2000,10);
            }
        }else{
            if(ctx.game.tab(Game.Tab.INVENTORY)){
                Condition.sleep(300);
            }
        }
    }
}
