package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Grass extends Plant {
    public Grass(Pair pos, World wor)
    {
        super(0, pos, wor);
    }
    public Grass(Pair pos, World wor, int lifeDur)
    {
        super(0, pos, wor, lifeDur);
    }
    @Override
    public final void action()
    {
        Random rand = new Random();
        if(rand.nextInt(0,7) == 0)
            breed();
    }
    @Override
    public final char draw()
    {
        return 'T';
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Grass(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
