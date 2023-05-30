package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;
import Everything.Organism.Animal.*;
import java.util.Random;

public class Pine_borscht extends Plant{
    public Pine_borscht(Pair pos, World wor)
    {
        super(10, pos, wor);
    }
    public Pine_borscht(Pair pos, World wor, int lifeDur)
    {
        super(10, pos, wor, lifeDur);
    }
    @Override
    public final void action()
    {
        Organism tmp;
        Pair tmpPos=new Pair(position.x, position.y - 1);
        tmp = world.getOrganism(tmpPos);
        if (tmp instanceof Animal) {
            world.setOrganism(tmpPos, position);
        }

        tmpPos.y = position.y+1;
        tmp = world.getOrganism(tmpPos);
        if (tmp instanceof Animal) {
            world.setOrganism(tmpPos, position);
        }

        tmpPos.x = position.x - 1;
        tmpPos.y = position.y;
        tmp = world.getOrganism(tmpPos);
        if (tmp instanceof Animal) {
            world.setOrganism(tmpPos, position);
        }

        tmpPos.x = position.x+1;
        tmp = world.getOrganism(tmpPos);
        if (tmp instanceof Animal) {
            world.setOrganism(tmpPos, position);
        }

        Random rand = new Random();
        if(rand.nextInt(0,9) == 0)
            breed();
    }
    @Override
    public final char draw()
    {
        return 'B';
    }
    @Override
    public final int collision(Organism org)
    {
        return BOTH_DYING;
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Pine_borscht(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
