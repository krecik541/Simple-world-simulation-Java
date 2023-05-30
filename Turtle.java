package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
import jdk.jfr.Percentage;

import java.util.Random;

public class Turtle extends Animal{
    public Turtle(Pair pos, World wor)
    {
        super(2, 1, pos, wor);
    }
    public Turtle(Pair pos, World wor, int lifeDur)
    {
        super(2, 1, pos, wor, lifeDur);
    }
    public Turtle(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        super(str, ini, pos, wor, lifeDur);
    }
    @Override
    public final void action()
    {
        Random rand = new Random();
        int move = rand.nextInt(0,4);
        if(move > 0)
            return;
        super.action();
    }
    @Override
    public final int collision(Organism org)
    {
        String orgType = org.getOrgName();
        String THIS = this.getOrgName();
        if (THIS.compareTo(orgType) == 0)
        {
            if (this.lifeDuration >= 0 && org.getLifeDuration() >= 0)
                breed();
            return BREED;
        }
        if (org.getStrength() < 5)
            return REPL_ATTACK;
        else
            return super.collision(org);
    }
    @Override
    public final char draw()
    {
        return 'Z';
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Turtle(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
