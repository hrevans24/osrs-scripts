package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import osrs.Task;

import java.util.concurrent.Callable;

public class Drop extends Task {

    final static int COPPER_ORE = 436;

    public Drop (ClientContext ctx){
        super(ctx);
    }
    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 27;
    }

    @Override
    public void execute() {
        for(Item copperOre: ctx.inventory.select().id(COPPER_ORE)) {

            if (ctx.controller.isStopping()){
                break;
            }

            final int startCopper = ctx.inventory.select().id(COPPER_ORE).count();
            copperOre.interact("Drop", "Copper");

            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(COPPER_ORE).count() != startCopper;
                }
            }, 25, 20);
        }
    }
}
