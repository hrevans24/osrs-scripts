package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import osrs.GrandExchange;
import osrs.Task;

import java.util.concurrent.Callable;

public class TradeDeposit extends Task {

    GrandExchange ge = new GrandExchange(ctx);
    private final Tile GE_TILE =  new Tile(3165,3487,0);

    public TradeDeposit(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 1 && ctx.players.local().tile().distanceTo(GE_TILE) < 6;
    }

    @Override
    public void execute() {
        if(ge.opened()){
            Condition.wait(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    return ge.close();
                }
            }, 25, 20);
        }
        if(ctx.bank.opened()){
            final int INVENTORY_COUNT = ctx.inventory.select().count();
            if(ctx.bank.depositInventory()){
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() != INVENTORY_COUNT;
                    }
                }, 250, 20);
            }
            ctx.bank.close();
        }else{
            if(ctx.bank.inViewport()){
                if(ctx.bank.open()){
                    Condition.wait(new Callable<Boolean>(){
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                }
            }else{
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }

    }
}
