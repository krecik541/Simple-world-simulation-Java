package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
import Everything.Pair;
import java.util.Random;
public abstract class Animal extends Organism {
    protected final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
    public Animal(int strength, int initiative, Pair position, World world, int lifeDuration)
    {
        super(strength, initiative, position, world, lifeDuration);
    }
    public Animal(int strength, int initiative, Pair position, World world)
    {
        super(strength, initiative, position, world, 0);
    }
    @Override
    public void action()
    {
        Random rand = new Random();
        int move = rand.nextInt(0,4);
        Organism org = null;
        Pair tmpPos = new Pair();
        tmpPos.x = position.x;
        tmpPos.y = position.y;
        if(move == LEFT && position.y - 1 >= 0)
            tmpPos.y--;
        else if(move == RIGHT && position.y + 1 < world.getM())
            tmpPos.y++;
        else if(move == UP && position.x - 1 >= 0)
            tmpPos.x--;
        else if(move == DOWN && position.x + 1 < world.getN())
            tmpPos.x++;
        else
            return;
        org = world.getOrganism(tmpPos);
        //System.out.println(this.getOrgName()+" "+this.position.x+" "+this.position.y+" "+org.getOrgName()+" "+org.getPosition().x+" "+org.getPosition().y);
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
    @Override
    public void breed()
    {

    }
    @Override
    public int collision(Organism org)
    {
        String orgType = org.getOrgName();
        String THIS = this.getOrgName();
        if(THIS.compareTo(orgType) == 0)
        {
            if(this.getLifeDuration() >= 0 && org.getLifeDuration() >= 0)
                breed();
            return BREED;
        }
        if(this.strength > org.getStrength() || this.strength == org.getStrength())
            return WIN_DEFENCE;
        else if(this.strength < org.getStrength())
            return WIN_ATTACK;
        return EMPTY;
    }
}
