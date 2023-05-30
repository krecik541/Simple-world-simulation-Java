package Everything;
import Everything.Pair;
import Everything.Organism.*;
import Everything.Organism.Plant.*;
import Everything.Organism.Animal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class World {
    private int n, m;
    private Windows windows;
    private Organism t[];
    private char tab[][];
    private double full;
    private void sort()
    {
        for (int i = 0; i < n * m; i++)
        {
            for (int j = 1; j < n * m - i; j++)
            {
                if (t[j - 1] == null || t[j] == null)
                    continue;
                if (t[j - 1].getInitiative() < t[j].getInitiative())
                {
                    Organism tmp = t[j-1];
                    t[j-1] = t[j];
                    t[j] = tmp;
                }
			else if (t[j - 1].getInitiative() == t[j].getInitiative())
                {
                    if(t[j-1].getLifeDuration() > t[j].getLifeDuration())
                    {
                        Organism tmp = t[j-1];
                        t[j-1] = t[j];
                        t[j] = tmp;
                    }
                }
            }
        }
    }
    public World(int n1, int m1, double fu, Windows windows)
    {
        this.windows = windows;
        n = n1;
        m = m1;
        t = new Organism[n*m];
        tab = new char[n][m];

        full = fu;
        for(int i=0; i<n*m; i++)
            t[i] = null;
        generate();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
                tab[i][j] = ' ';
        }
        Pair pos;
        int i=0;
        while(i<n*m)
        {
            if(t[i] != null)
            {
                pos = t[i].getPosition();
                if(0 <= pos.x && pos.x < n && 0 <= pos.y && pos.y < m)
                    tab[pos.x][pos.y] = t[i].draw();
            }
            i++;
        }
    }

    public void setWindows(Windows windows)
    {
        this.windows = windows;
    }
    public Windows getWindows()
    {
        return windows;
    }

    /*public World()
    {
        n = 20;
        m = 20;
        full = 0.5;
        for(int i=0; i<n*m; i++)
            t[i] = null;
        generate();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
                tab[i][j] = ' ';
        }
        Pair pos;
        int i=0;
        while(i<n*m)
        {
            if(t[i] != null)
            {
                pos = t[i].getPosition();
                if(0 <= pos.x && pos.x < n && 0 <= pos.y && pos.y < m)
                    tab[pos.x][pos.y] = t[i].draw();
            }
            i++;
        }
    }*/
    public int getN()
    {
        return n;
    }
    public int getM()
    {
        return m;
    }
    public void makeTurn()
    {
        //system("CLS");
        sort();
        for(int i=0; i<n*m; i++)
        {
            if(t[i] != null)
            {
                t[i].setLifeDuration();
                if(t[i].getLifeDuration() > 0)
                    t[i].action();
            }
        }
    }
    public boolean drawWorld(Windows windows)
    {
        boolean life = false;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
                tab[i][j] = ' ';
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                windows.setFrame((i)*m+j, ' ', new Color(255, 255, 255));
            }
        }

        Pair pol;
        int i = 0;
        while (i < n * m)
        {
            if(t[i] != null)
            {
                pol = t[i].getPosition();
                if(pol != null)
                    if (0 <= pol.x && pol.x < n && 0 <= pol.y && pol.y < m)
                        tab[pol.x][pol.y] = t[i].draw();
            }
            i++;
        }

        /*for(i=0; i<n;i++) {
            String s="";
            for (int j = 0; j < m; j++)
                s = s+tab[i][j];
            System.out.println(s);
        }*/


        for (i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (tab[i][j] == 'A' || tab[i][j] == 'L' || tab[i][j] == 'O' || tab[i][j] == 'W' || tab[i][j] == 'Z')
                    windows.setFrame((i)*m+j, tab[i][j], new Color(227, 82, 87));
                else if (tab[i][j] == 'J' || tab[i][j] == 'M' || tab[i][j] == 'T' || tab[i][j] == 'G' || tab[i][j] == 'B')
                    windows.setFrame((i)*m+j, tab[i][j], new Color(29, 242, 40));
                else if (tab[i][j] == '^')
                {
                    life = true;
                    windows.setFrame((i)*m+j, tab[i][j], new Color(242, 114, 29));
                }
                else if (tab[i][j] == ' ')
                    windows.setFrame((i)*m+j, ' ', new Color(255, 255, 255));
            }
        }
        windows.refresh();
        return life;
    }
    public void generate()
    {
        int n = 0;
        int countOrg = (int)Math.floor(this.n * this.m * full)-1;
        Organism tmp = null;
        while (countOrg > 0)
        {
            Random rand = new Random();
            Pair pos = new Pair();
            pos.x = rand.nextInt(0, this.n);
            pos.y = rand.nextInt(0, this.m);
            if ((pos.x == 0 && pos.y == 0) || getOrganism(pos) != null)
                continue;
            n = rand.nextInt(0, 10);
            switch (n)
            {
                case 0:
                {
                    Sheep sheep = new Sheep(pos, this);
                    tmp = sheep;
                    break;
                }
                case 1:
                {
                    Wolf wolf = new Wolf(pos, this);
                    tmp = wolf;
                    break;
                }
                case 2:
                {
                    Fox fox = new Fox(pos, this);
                    tmp = fox;
                    break;
                }
                case 3:
                {
                    Turtle turtle = new Turtle(pos, this);
                    tmp = turtle;
                    break;
                }
                case 4:
                {
                    Antelope antelope = new Antelope(pos, this);
                    tmp = antelope;
                    break;
                }
                case 5:
                {
                    Grass grass = new Grass(pos, this);
                    tmp = grass;
                    break;
                }
                case 6:
                {
                    Dandelion dandelion = new Dandelion(pos, this);
                    tmp = dandelion;
                    break;
                }
                case 7:
                {
                    Guarana guarana = new Guarana(pos, this);
                    tmp = guarana;
                    break;
                }
                case 8:
                {
                    Deadly_nightshade deadlyNightshade = new Deadly_nightshade(pos, this);
                    tmp = deadlyNightshade;
                    break;
                }
                case 9:
                {
                    Pine_borscht pineBorscht = new Pine_borscht(pos, this);
                    tmp = pineBorscht;
                    break;
                }
            }
            setOrganism(tmp);
            countOrg--;
        }
    }
    public void setOrganism(Organism org)
    {
        if (org == null)
            return;
        int i = 0;
        while (i<n*m && t[i] != null)
        {
            i++;
        }
        if (i >= n * m)
            return;
        t[i] = org;
        windows.addTxt("Powstal Organizm "+t[i].getOrgName()+" na pozycji ["+t[i].getPosition().x+","+t[i].getPosition().y+"]");
    }
    public void setOrganism(Organism org, Organism toDelete)
    {
        int i = 0;
        while (i < n * m)
        {
            if (t[i] == toDelete)
            {
                windows.addTxt("Organizm "+toDelete.getOrgName()+" ["+toDelete.getPosition().x+","+toDelete.getPosition().y+"] zostal zabity przez "+org.getOrgName()+" ["+org.getPosition().x+","+org.getPosition().y+"]");
                org.setPosition(toDelete.getPosition());
                t[i] = null;
                break;
            }
            i++;
        }
    }
    public void setOrganism(Pair posDelete, Pair to)
    {
        int i = 0;
        while (i < n * m)
        {
            if (t[i] != null && t[i].getPosition().x == posDelete.x && t[i].getPosition().y == posDelete.y)
            {
                if(getOrganism(to) != null)
                    windows.addTxt(t[i].getOrgName()+"["+t[i].getPosition().x+","+t[i].getPosition().y+"] zostal zabity przez "+getOrganism(to).getOrgName()+" ["+to.x+","+to.y+"]");
			    else
                    windows.addTxt("Jednoczesnie Organizm "+t[i].getOrgName()+" ["+ t[i].getPosition().x+","+t[i].getPosition().y+"] zostal zabity");
                t[i] = null;
                break;
            }
            i++;
        }
    }
    public Organism getOrganism(Pair pos)
    {
        int i = 0;
        while (i < n*m)
        {
            if (t[i] != null && t[i].getPosition()!=null)
                if(t[i].getPosition().x == pos.x && t[i].getPosition().y == pos.y)
                    return t[i];
            i++;
        }
        return null;
    }
    public Human load(Human human, String str)
    {
        Pair pos = new Pair();
        pos.x = 0;
        Organism tmp = null;
        pos.y = 0;
        int stre=0, ini = 0, lifeDur = 0, coold = 0;
        Scanner lo = new Scanner(System.in);
        try {
            File file = new File(str);
            lo = new Scanner(file);
            n = Integer.parseInt(lo.nextLine());
            m = Integer.parseInt(lo.nextLine());
            t = new Organism[n*m];
            for(int i=0; i<n*m; i++)
                t[i] = null;
            tab = new char[n][m];
            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++)
                    tab[i][j] = ' ';
            while (lo.hasNextLine())
            {
                pos = new Pair();
                String name;
                name = lo.nextLine();
                if(name.compareTo("0") == 0)
                    break;
                stre = Integer.parseInt(lo.nextLine());
                ini = Integer.parseInt(lo.nextLine());
                pos.x = Integer.parseInt(lo.nextLine());
                pos.y = Integer.parseInt(lo.nextLine());
                lifeDur = Integer.parseInt(lo.nextLine());

                if (name.compareTo("Human") == 0)
                {
                    //coold = Integer.parseInt(lo.nextLine());
				    human = new Human(stre, ini, pos, this, lifeDur, coold);
                    tmp = human;
                }
                else if (name.compareTo("Antelope") == 0)
                    tmp = new Antelope(stre, ini, pos, this, lifeDur);
                else if (name.compareTo("Pine Borscht") == 0)
                    tmp = new Pine_borscht(pos, this, lifeDur);
                else if (name.compareTo("Guarana") == 0)
                    tmp = new Guarana(pos, this, lifeDur);
                else if (name.compareTo("Fox") == 0)
                    tmp = new Fox(stre, ini, pos, this, lifeDur);
                else if (name.compareTo("Dandelion") == 0)
                tmp = new Dandelion(pos, this, lifeDur);
                else if (name.compareTo("Sheep") == 0)
                    tmp = new Sheep(stre, ini, pos, this, lifeDur);
                else if (name.compareTo("Grass") == 0)
                    tmp = new Grass(pos, this, lifeDur);
                else if (name.compareTo("Deadly Nightshade") == 0)
                    tmp = new Deadly_nightshade(pos, this, lifeDur);
                else if (name.compareTo("Wolf") == 0)
                    tmp = new Wolf(stre, ini, pos, this, lifeDur);
                else if (name.compareTo("Turtle") == 0)
                    tmp = new Turtle(stre, ini, pos, this, lifeDur);
                setOrganism(tmp);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            if(lo != null)
                lo.close();
        }

        return human;
    }
    public void save(Human human, String str)
    {
        PrintWriter sav = null;
        try {
            sav = new PrintWriter(str);
            sav.print(n + "\n" + m);
            for (int i = 0; i < n * m; i++) {
                if(t[i] == null)
                    continue;
                String name = t[i].getOrgName();
                //int z = name.compareTo("Human");
                if (name != "Human" && name != "")
                    sav.print( "\n" + name + "\n" + t[i].getStrength() + "\n" + t[i].getInitiative() + "\n" + t[i].getPosition().x + "\n" + t[i].getPosition().y + "\n" + t[i].getLifeDuration());
                else if (name == "Human")
                    sav.print("\n" + name + "\n" + t[i].getStrength() + "\n" + t[i].getInitiative() + "\n" + t[i].getPosition().x + "\n" + t[i].getPosition().y + "\n" + t[i].getLifeDuration() + "\n" + human.getCooldown());
                else
                    System.out.println("123");
            }
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            if(sav != null)
                sav.close();
        }

    }
}
