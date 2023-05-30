package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
public class Sheep extends Animal{
    public Sheep(Pair pos, World wor)
    {
        super(4, 4, pos, wor);
    }
    public Sheep(Pair pos, World wor, int lifeDur)
    {
        super(4, 4, pos, wor, lifeDur);
    }
    public Sheep(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        super(str, ini, pos, wor, lifeDur);
    }
    @Override
    public final char draw()
    {
        return 'O';
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Sheep(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
