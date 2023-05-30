package Everything.Organism;
import Everything.*;
import Everything.Organism.Animal.*;
import Everything.Organism.Plant.*;

import java.util.Random;

public abstract class Organism {
    protected final int EMPTY = 6, BREED = 0, REPL_ATTACK = 0, WIN_ATTACK = 1, WIN_DEFENCE = 2, RUNNING = 3, BOTH_DYING = 5;
    protected int strength, initiative, lifeDuration;
    protected World world;
    protected Pair position;
    public Organism(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        strength = str;
        initiative = ini;
        position = pos;
        world = wor;
        lifeDuration = lifeDur;
    }
    public abstract void action();
    public abstract int collision(Organism org);
    public abstract char draw();
    public abstract void breed();

    public int getStrength()
    {
        return strength;
    }
    public void setStrength(int str)
    {
        strength = str;
    }
    public int getInitiative()
    {
        return initiative;
    }
    public void setInitiative(int ini)
    {
        initiative = ini;
    }
    public Pair getPosition()
    {
        return position;
    }
    public void setPosition(Pair pos)
    {
        position = pos;
    }
    public World getWorld()
    {
        return world;
    }
    public void setWorld(World wor)
    {
        world = wor;
    }
    public int getLifeDuration()
    {
        return lifeDuration;
    }
    public void setLifeDuration()
    {
        lifeDuration++;
    }
    public Pair searchPos(Pair pos)
    {
        Random rand = new Random();
        Pair n = new Pair();
        int i=0, ma = world.getM() * world.getN();
        boolean up = true, down = true, left = true, right = true;
        if(pos.y - 1 < 0 || world.getOrganism(new Pair(pos.x, pos.y-1)) != null)
                left = false;
        if(pos.y + 1 >= world.getM() || world.getOrganism(new Pair(pos.x, pos.y+1)) != null)
            right = false;
        if(pos.x - 1 < 0 || world.getOrganism(new Pair(pos.x - 1, pos.y)) != null)
            up = false;
        if(pos.x + 1 >= world.getN() || world.getOrganism(new Pair(pos.x+ 1, pos.y ))!= null)
            down = false;
        if(!up && !down && !left && !right)
            return null;
        while(true)
        {
            i = rand.nextInt(0,4) ;
            if(i == 1 && left)
                return new Pair(pos.x, pos.y-1);
            else if(i == 3 && right)
                return new Pair(pos.x, pos.y+1);
            else if(i == 0 && up)
                return new Pair(pos.x - 1, pos.y);
            else if(i == 2 && down)
                return new Pair(pos.x + 1, pos.y);
        }
    }
    public String getOrgName()
    {
        Organism org = this;
        if(org instanceof Animal)
        {
            if(org instanceof Antelope)
                return "Antelope";
            if(org instanceof Fox)
                return "Fox";
            if(org instanceof Sheep)
                return "Sheep";
            if(org instanceof Wolf)
                return "Wolf";
            if(org instanceof Turtle)
                return "Turtle";
            if(org instanceof Human)
                return "Human";
        }
        if(org instanceof Plant)
        {
            if(org instanceof Dandelion)
                return "Dandelion";
            if(org instanceof Deadly_nightshade)
                return "Deadly Nightshade";
            if(org instanceof Grass)
                return "Grass";
            if(org instanceof Guarana)
                return "Guarana";
            if(org instanceof Pine_borscht)
                return "Pine Borscht";
        }
        return null;
    }
}
