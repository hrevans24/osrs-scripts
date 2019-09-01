package mining.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import mining.GrandExchange;
import mining.Task;

public class Trade extends Task {

    private Tile geLocation = new Tile(3165,3487,0);
    final static int[] GE_CLERKS = {2148, 2149};
    private final int COPPER_ORE_NOTE = 437;
    private Item item;
    GrandExchange ge = new GrandExchange(ctx);

    public Trade(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(COPPER_ORE_NOTE).count() > 28 && ctx.players.local().tile().distanceTo(geLocation) < 6;
    }

    @Override
    public void execute() {
        //TODO: MAKE IT SO YOU WITHDRAW AS NOTES AND THEN SELL IN BULK AND GO BACK TO MINING

        if (ctx.npcs.select().id(GE_CLERKS).nearest().poll().interact("Exchange")) {
            Condition.sleep(700);
        }
        if (ctx.inventory.select().id(COPPER_ORE_NOTE).poll().click()) {
            Condition.sleep(700);
        }
        if (ctx.widgets.component(465, 24).component(48).click()) {
            Condition.sleep(700);
        }
//        }else{
//            MiningDeposit miningDeposit = new MiningDeposit(ctx);
//            miningDeposit.execute();
//            if(ctx.bank.close()){
//                Condition.sleep(700);
//            }
//            if(ctx.npcs.select().id(GE_CLERKS).nearest().poll().interact("Exchange")){
//                Condition.sleep(700);
//            }
//            if(ctx.widgets.component(465,6).component(1).click()){
//                Condition.sleep(700);
//            }
//            if(ctx.widgets.component(465,2).component(11).click()){
//                Condition.sleep(700);
//            }
//            miningDeposit.execute();
//        }
            if (ctx.widgets.component(465, 24).component(50).click()) {
                Condition.sleep(700);
            }
            if (ctx.widgets.component(465, 24).component(54).click()) {
                Condition.sleep(700);
            }
            if (ctx.widgets.component(465, 2).component(11).click()) {
                Condition.sleep(700);
            }
    }
}
