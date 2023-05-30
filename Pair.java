package Everything;
public class Pair {
    public int x, y;
    public Pair()
    {
        x = 0;
        y = 0;
    }
    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    boolean Equal(Pair par1, Pair par2)
    {
        if(par1.x == par2.x && par1.y == par2.y)
            return true;
        else
            return false;
    }
}
