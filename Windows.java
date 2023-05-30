package Everything;

import Everything.Organism.Animal.*;
import Everything.Organism.Organism;
import Everything.Organism.Plant.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Windows extends JFrame implements ActionListener, KeyListener {
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons;
    JPanel Text = new JPanel();
    JTextArea text = new JTextArea();
    private World world;
    private int n, m, move = -1;
    private double full;
    public final int NOT = -1, RIGHT = 3, LEFT = 1, UP = 0, DOWN = 2, Q = 4, L = 5, S = 6, U = 7;
    public Windows()
    {
        this.world = null;
        this.setVisible(true);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(50, 50, 50));
        this.setLayout(new GridLayout(1,2));
        this.setTitle("MACEIJ SZYMCZAK 193456 | JAVA WORLD");

        String str = JOptionPane.showInputDialog("Enter the dimensions of the board y:");
        n = Integer.parseInt(str);
        str = JOptionPane.showInputDialog("Enter the dimensions of the board x:");
        m = Integer.parseInt(str);
        str = JOptionPane.showInputDialog("Fill the board (range 0-1):");
        full = Double.parseDouble(str);

        int i=0;

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(n, m));
        button_panel.setBackground(new Color(150, 150, 150));

        buttons = new JButton[n*m];

        for(i=0; i<n*m; i++)
        {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont((new Font("MV Boli", Font.BOLD, 16)));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);

        Text.setLayout(new BorderLayout());
        //text.setBackground(new Color(100,100,100));
        Text.add(text);

        this.add(Text, BorderLayout.EAST);
        this.add(button_panel, BorderLayout.WEST);
        this.addKeyListener(this);
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //System.out.println("1");
        for(int i=0; i<n*m; i++)
        {
            if(e.getSource() == buttons[i])
            {
                String[] opt = {"Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Grass", "Dandelion", "Guarana", "Deadly Nightshade", "Pine Borscht"};
                int num = JOptionPane.showOptionDialog(null, "Choose orgnaism", "Add a new organism", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, null);
                //System.out.println(num);
                Organism tmp = null;
                Pair pos = new Pair(i/m, i%m);
                switch (num)
                {
                    case 0:
                        tmp = new Wolf(pos, world);
                        break;
                    case 1:
                        tmp = new Sheep(pos, world);
                        break;
                    case 2:
                        tmp = new Fox(pos, world);
                        break;
                    case 3:
                        tmp = new Turtle(pos, world);
                        break;
                    case 4:
                        tmp = new Antelope(pos, world);
                        break;
                    case 5:
                        tmp = new Grass(pos, world);
                        break;
                    case 6:
                        tmp = new Dandelion(pos, world);
                        break;
                    case 7:
                        tmp = new Guarana(pos, world);
                        break;
                    case 8:
                        tmp = new Deadly_nightshade(pos, world);
                        break;
                    case 9:
                        tmp = new Pine_borscht(pos, world);
                        break;
                }
                world.setOrganism(tmp);
                world.drawWorld(this);
            }
        }

    }

    public void addTxt(String s)
    {
        if(s.compareTo("") == 0)
            text.setText("");
        else
            text.append(s+"\n");
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("2");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("3");
        /*if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move = RIGHT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move = LEFT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            move = UP;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            move = DOWN;
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("4");
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move = RIGHT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move = LEFT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            move = UP;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            move = DOWN;
        }
        else if (e.getKeyCode() == KeyEvent.VK_Q) {
            move = Q;
        }
        else if (e.getKeyCode() == KeyEvent.VK_L) {
            move = L;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            move = S;
        }
        else if (e.getKeyCode() == KeyEvent.VK_U) {
            move = U;
        }
    }

    public int returnMove()
    {
        return move;
    }
    public void setMove(int move)
    {
        this.move = move;
    }

    public int getN()
    {
        return n;
    }
    public int getM()
    {
        return m;
    }
    public double getFull()
    {
        return full;
    }
    public String readName()
    {
        String str = JOptionPane.showInputDialog("Enter file name:");
        return str;
    }
    public void setFrame(int n, char s, Color color)
    {
        if(buttons[n] == null)
            return;
        String str = ""+s+"";
        buttons[n].setText(str);
        buttons[n].setBackground(color);

    }
    public void load(int n, int m)
    {
        this.m = m;
        this.n = n;
        this.remove(button_panel);
        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(n, m));
        button_panel.setBackground(new Color(150, 150, 150));
        buttons = new JButton[n*m];
        buttons = new JButton[n*m];

        for(int i=0; i<n*m; i++)
        {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont((new Font("MV Boli", Font.BOLD, 24)));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        this.add(button_panel);
    }
    public void refresh()
    {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
