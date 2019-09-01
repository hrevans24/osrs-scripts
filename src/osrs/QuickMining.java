package osrs;


import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import osrs.tasks.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Quick mining", description = "tutorial", properties = "client=4; author=hunter")
public class QuickMining extends PollingScript<ClientContext> implements PaintListener {

    List<Task> taskList = new ArrayList<Task>();
    int startXP = 0;

    @Override
    public void start(){
        String[] userOptions = {"Deposit", "Power Mine", "Bank"};
        String userChoice = "" + (String)JOptionPane.showInputDialog(null, "Deposit, Power Mine, or Bank", "Quick Mining", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[1]);

        if(userChoice.equals("Deposit")){
            taskList.add(new MiningDeposit(ctx));
            taskList.add(new BankWalk(ctx));
        }else if(userChoice.equals("Power Mine")){
            taskList.add(new Drop(ctx));
            taskList.add(new BankWalk(ctx));
        }else if(userChoice.equals("Bank")){
            taskList.add(new Trade(ctx));
            taskList.add(new Withdraw(ctx));
            taskList.add(new GEWalk(ctx));
        } else{
            ctx.controller.stop();
        }
        taskList.add(new Mine(ctx));
        startXP = ctx.skills.experience(Constants.SKILLS_MINING);
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

//    @Override
    public void repaint(Graphics graphics) {
//        long milliseconds = this.getTotalRuntime();
//        long seconds = (milliseconds/1000) % 60;
//        long minutes = (milliseconds/(1000*60) % 60);
//        long hours = (milliseconds/(1000*60*60)) % 24;
//
//        int XPGained = ctx.skills.experience(Constants.SKILLS_MINING) - startXP;
//
//        Graphics2D g = (Graphics2D)graphics;
//
//        g.setColor(new Color(0,0,0,180));
//        g.fillRect(0,0,150,100);
//
//        g.setColor(new Color(255,255,255));
//        g.drawRect(0,0,150,100);
//        g.drawString("Quick Miner", 20,20);
//        g.drawString("Running: " + String.format("%02d%02d:%02d", hours, minutes, seconds), 20, 40);
//        g.drawString("XP/Hour: " + (int)(XPGained * (3600000D / milliseconds)), 20, 60);
    }
}
