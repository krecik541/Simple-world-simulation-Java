package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
public class Wolf extends Animal{
    public Wolf(Pair pos, World wor)
    {
        super(9, 5, pos, wor);
    }
    public Wolf(Pair pos, World wor, int lifeDur)
    {
        super(9, 5, pos, wor, lifeDur);
    }
    public Wolf(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        super(str, ini, pos, wor, lifeDur);
    }
    @Override
    public final char draw()
    {
        return 'W';
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Wolf(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
