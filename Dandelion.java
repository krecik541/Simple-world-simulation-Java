package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Dandelion extends Plant{
    public Dandelion(Pair pos, World wor)
    {
        super(0, pos, wor);
    }
    public Dandelion(Pair pos, World wor, int lifeDur)
    {
        super(0, pos, wor, lifeDur);
    }
    @Override
    public final void action()
    {
        Random rand = new Random();
        int i = 1;
        Organism tmp;
        Pair pol = searchPos(this.position);
        while(i<=3)
        {
            if (rand.nextInt(0, 7) == 0)
            {
                if (pol != null)
                {
                    tmp = new Dandelion(pol, world, -1);
                    world.setOrganism(tmp);
                }
                else
                    break;
            }
            i++;
            pol = searchPos(this.position);
        }
    }
    @Override
    public final char draw()
    {
        return 'M';
    }
}
