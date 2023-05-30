import Everything.*;
import Everything.Organism.Animal.Human;
import Everything.Organism.Organism;
import Everything.Pair;
import Everything.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Windows windows = new Windows();

        Scanner scanner = new Scanner(System.in);
        int zn = windows.NOT;
        int n, m;
        double full;
        Pair pos = new Pair();
        pos.x = 0;
        pos.y = 0;
        boolean isHumanAlive = true;
        n = windows.getN();
        m = windows.getM();
        full = windows.getFull();
        World world = new World(n, m, full, windows);
        try{Thread.sleep(50);}catch(InterruptedException e){}
        windows.setWorld(world);
        Organism tmp;
        String str = null;
        Human human = new Human(pos, world);
        tmp = human;
        world.setOrganism(tmp);
        isHumanAlive = world.drawWorld(windows);
        while (true)
        {
            zn = windows.returnMove();
            try{Thread.sleep(50);}catch(InterruptedException e){}
            if(zn == -1)
                continue;
            windows.addTxt("");
            if(zn == windows.Q) {
                windows.dispose();
                System.exit(0);
            }
            else if(zn == windows.U && human.getCooldown() == 0 && isHumanAlive)
            {
                human.skill();
                isHumanAlive = world.drawWorld(windows);
            }
            else if(zn == windows.S)
            {
                str = windows.readName();
                world.save(human, str);
                windows.addTxt("SAVE COMPLETED SUCCESSFULLY \n");
                isHumanAlive = world.drawWorld(windows);
            }
            else if(zn == windows.L)
            {
                str = windows.readName();
                human = world.load(human, str);
                n = world.getN();
                m = world.getM();
                windows.load(n, m);
                windows.addTxt("LOAD COMPLETED SUCCESSFULLY \n");
                isHumanAlive = world.drawWorld(windows);
            }
            else if (zn == windows.RIGHT)
            {
                human.setMove(windows.RIGHT);
                world.makeTurn();
            }
            else if (zn == windows.LEFT)
            {
                human.setMove(windows.LEFT);
                world.makeTurn();
            }
            else if (zn == windows.UP)
            {
                human.setMove(windows.UP);
                world.makeTurn();
            }
            else if (zn == windows.DOWN)
            {
                human.setMove(windows.DOWN);
                world.makeTurn();
            }

            isHumanAlive = world.drawWorld(windows);
            if(isHumanAlive) {
                windows.addTxt("\nSkill cooldown " + human.getCooldown()+"\n");
                windows.addTxt("Human strength "+human.getStrength()+"\n");
            }
            windows.setMove(-1);
        }
    }
}