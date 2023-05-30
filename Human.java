package Everything.Organism.Animal;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Human extends Animal{
    private int cooldown, move;
    public Human(Pair pos, World wor)
    {
        super(5, 4, pos, wor);
        cooldown = 0;
        move = -1;
    }
    public Human(int str, int ini, Pair pos, World wor, int lifeDur, int cool)
    {
        super(str, ini, pos, wor, lifeDur);
        this.cooldown = cool;
        move = -1;
    }
    @Override
    public final void action()
    {
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
        //System.out.println(position.x+" "+ position.y);
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
        if(strength > 5 && cooldown > 5)
            strength--;
        if(cooldown > 0)
            cooldown--;
    }
    public void setMove(int mov)
    {
        move = mov;
    }
    public int getMove()
    {
        return move;
    }
    public int getCooldown()
    {
        return cooldown;
    }
    public void skill()
    {
        cooldown = 10;
        strength += 5;
    }
    @Override
    public final char draw()
    {
        return '^';
    }
}
