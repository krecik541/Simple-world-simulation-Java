package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Antelope extends Animal{
    public Antelope(Pair pos, World wor)
    {
        super(4, 4, pos, wor);
    }
    public Antelope(Pair pos, World wor, int lifeDur)
    {
        super(4, 4, pos, wor, lifeDur);
    }
    public Antelope(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        super(str, ini, pos, wor, lifeDur);
    }
    @Override
    public final char draw()
    {
        return 'A';
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Antelope(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
    @Override
    public final int collision(Organism org)
    {
        //System.out.println(this.getOrgName()+" "+this.strength+" "+org.getOrgName()+" "+org.getStrength());
        Random rand = new Random();
        String orgType = org.getOrgName();
        String THIS = this.getOrgName();
        if(THIS.compareTo(orgType) == 0)
        {
            if(this.lifeDuration >= 0 && org.getLifeDuration() >= 0)
                breed();
            return BREED;
        }
        if(rand.nextBoolean() == true)
            return RUNNING;
        else
            return super.collision(org);
    }
    @Override
    public final void action()
    {
        Random rand = new Random();
        int move = rand.nextInt(0,4);
        Organism org = null;
        Pair tmpPos = new Pair();
        tmpPos.x = position.x;
        tmpPos.y = position.y;
        for(int i=1; i<=2; i++)
        {
            move = rand.nextInt(0,4);
            if (move == LEFT && position.y - 1 >= 0)
                tmpPos.y--;
            else if (move == RIGHT && position.y + 1 < world.getM())
                tmpPos.y++;
            else if (move == UP && position.x - 1 >= 0)
                tmpPos.x--;
            else if (move == DOWN && position.x + 1 < world.getN())
                tmpPos.x++;
            else
                return;
        }
        org = world.getOrganism(tmpPos);
        if(org != null)
        {
            int col = org.collision(this);
            if(col == REPL_ATTACK)
                ;
            else if (col == RUNNING)
            {
                setPosition(tmpPos);
                tmpPos = this.searchPos(org.getPosition());
                if(tmpPos == null)
                {
                    world.setOrganism(org.getPosition(), this.position);
                }
                else
                    org.setPosition(tmpPos);
            }
            else if (col == WIN_ATTACK)
                world.setOrganism(this, org);
            else if (col == WIN_DEFENCE)
                world.setOrganism(this.position, org.getPosition());
            else if (col == BOTH_DYING)
            {
                world.setOrganism(this.position, org.getPosition());
                world.setOrganism(org.getPosition(), this.position);
            }
        }
        else
        {
            world.getWindows().addTxt("Organizm " + getOrgName() + " [" + position.x + "," + position.y + "] zmienil polozenie na [" + tmpPos.x + "," + tmpPos.y + "]");
            setPosition(tmpPos);
        }
    }
}
