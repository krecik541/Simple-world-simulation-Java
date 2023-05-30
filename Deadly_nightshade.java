package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Deadly_nightshade extends Plant{
    public Deadly_nightshade(Pair pos, World wor)
    {
        super(99, pos, wor);
    }
    public Deadly_nightshade(Pair pos, World wor, int lifeDur)
    {
        super(99, pos, wor, lifeDur);
    }
    @Override
    public final void action()
    {
        Random rand = new Random();
        if(rand.nextInt(0, 9) == 0)
            breed();
    }
    @Override
    public final char draw()
    {
        return 'J';
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
        Organism tmp = new Deadly_nightshade(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
