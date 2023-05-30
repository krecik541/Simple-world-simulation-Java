package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;

public abstract class Plant extends Organism {
    public Plant(int str, Pair pos, World wor)
    {
        super(str, 0, pos, wor, 0);
    }
    public Plant(int str, Pair pos, World wor, int lifeDur)
    {
        super(str, 0, pos, wor, lifeDur);
    }
    @Override
    public void breed()
    {

    }
    @Override
    public void action()
    {

    }
    @Override
    public int collision(Organism org)
    {
        return WIN_ATTACK;
    }
}
