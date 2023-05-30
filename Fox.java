package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Fox extends Animal{
    public Fox(Pair pos, World wor)
    {
        super(3, 7, pos, wor);
    }
    public Fox(Pair pos, World wor, int lifeDur)
    {
        super(3, 7, pos, wor, lifeDur);
    }
    public Fox(int str, int ini, Pair pos, World wor, int lifeDur)
    {
        super(str, ini, pos, wor, lifeDur);
    }
    @Override
    public final char draw()
    {
        return 'L';
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
        if(org != null)
        {
            if(org.getStrength() <= this.strength) {
                int col = org.collision(this);
                if (col == REPL_ATTACK)
                    ;
                else if (col == RUNNING) {
                    setPosition(tmpPos);
                    tmpPos = this.searchPos(org.getPosition());
                    if(tmpPos == null)
                    {
                        world.setOrganism(org.getPosition(), this.position);
                    }
                    else
                        org.setPosition(tmpPos);
                } else if (col == WIN_ATTACK)
                    world.setOrganism(this, org);
                else if (col == WIN_DEFENCE)
                    world.setOrganism(this.position, org.getPosition());
                else if (col == BOTH_DYING) {
                    world.setOrganism(this.position, org.getPosition());
                    world.setOrganism(org.getPosition(), this.position);
                }
            }
        }
        else
        {
            world.getWindows().addTxt("Organizm " + getOrgName() + " [" + position.x + "," + position.y + "] zmienil polozenie na [" + tmpPos.x + "," + tmpPos.y + "]");
            setPosition(tmpPos);
        }
    }
    @Override
    public final void breed()
    {
        Pair pos = searchPos(position);
        if(pos == null)
            return;
        Organism tmp = new Fox(pos, world, -1);
        if(tmp != null)
            world.setOrganism(tmp);
    }
}
