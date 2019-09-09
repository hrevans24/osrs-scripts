package attack;

import attack.tasks.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Cow killing attack farm", description = "Power level your attack by afk killing cows. Shouldn't ever die, script accounts for health and acquiring food when you run out. Must have completed the \"Cooks assistant\" quest", properties = "client=4; author=hunter")
public class AttackFarm extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        Task first = new LocationCheck(ctx);
        if(first.activate()){
            first.execute();
        }
        taskList.add(new GetEquipped(ctx));
        taskList.add(new EquipmentWalk(ctx));
        taskList.add(new Fish(ctx));
        taskList.add(new Eat(ctx));
        taskList.add(new Attack(ctx));
    }

    @Override
    public void poll() {
        for(Task task: taskList){
            if (ctx.controller.isStopping()){
                break;
            }
            if(task.activate()) {
                task.execute();
                break;
            }
        }
    }
}
