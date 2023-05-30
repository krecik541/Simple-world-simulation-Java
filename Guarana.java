package Everything.Organism.Plant;
import Everything.*;
import Everything.Organism.*;
import java.util.Random;

public class Guarana extends Plant{
        public Guarana(Pair pos, World wor)
        {
                super(99, pos, wor);
        }
        public Guarana(Pair pos, World wor, int lifeDur)
        {
                super(99, pos, wor, lifeDur);
        }
        @Override
        public final void action()
        {
                Random rand = new Random();
                if(rand.nextInt(0,8) == 0)
                        breed();
        }
        @Override
        public final char draw()
        {
                return 'G';
        }
        @Override
        public final int collision(Organism org)
        {
                org.setStrength(org.getStrength() + 3);
                return WIN_ATTACK;
        }
        @Override
        public final void breed()
        {
                Pair pos = searchPos(position);
                if(pos == null)
                        return;
                Organism tmp = new Guarana(pos, world, -1);
                if(tmp != null)
                        world.setOrganism(tmp);
        }
}
