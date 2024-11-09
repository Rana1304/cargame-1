
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Cargame extends JFrame implements KeyListener, ActionListener {

    private int xpos = 300;//x position of car
    private int ypos = 700;//y position of car
    private ImageIcon car1, car2, car3, car4;//car image

    Random random = new Random();//random number generator

    private int num1 = 400;//x position of the obstacle
    private int tree1ypos = 400, tree2ypos = 200, tree3ypos = -500, tree4ypos = 100, tree5ypos = -300, tree6ypos = -200;
    private int roadmove = 0;//y position of the road
    private final int carxpos[] = {100, 200, 300, 400, 500};//x position
    private final int carypos[] = {-240, -480, -720, -960, -1200};
    private int cxpos1 = 0, cxpos2 = 2, cxpos3 = 4;//x position of cars
    private int cypos1 = random.nextInt(5), cypos2 = random.nextInt(5), cypos3 = random.nextInt(5);//random number for y position
    int x1pos = carxpos[cxpos1];
    int x2pos = carxpos[cxpos2];
    int x3pos = carxpos[cxpos3];
    int y1pos = carypos[cypos1];
    int y2pos = carypos[cypos2];
    int y3pos = carypos[cypos3];//actual y position of car
    int score = 0;//score by default
    int delay = 100;//delay to move the cars step by step
    int speed = 90;//speed of car

    private ImageIcon tree1, tree2, tree3;//tree images
    private boolean gameover = false, paint = false;//logics of game

    public Cargame(String title) {
        super(title);//call the constructor of the parent class and set the value of the title of the frame
        setBounds(300, 10, 700, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);//set the layout null to frame
        setFocusable(true);//set the focus on the frame
        setResizable(false);//the frame is not resizable
        addKeyListener(this);
    }

    //method paint to display the graphic on the screen
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void paint(Graphics g) {
        g.setColor(Color.green);//color of the grass
        g.fillRect(0, 0, 700, 700);//draw the grass
        g.setColor(Color.gray);//color of the road
        g.fillRect(90, 0, 10, 700);
        g.fillRect(600, 0, 10, 700);
        g.fillRect(100, 0, 500, 700);

        //let's draw the lines
        if (roadmove == 0) {
            for (int i = 0; i <= 700; i += 100) {
                g.setColor(Color.white);
                g.fillRect(350, i, 10, 70);
            }
            roadmove = 1;
        }//draw the roadlines for the next frame
        else if (roadmove == 1) {
            for (int i = 50; i <= 700; i += 100) {
                g.setColor(Color.white);
                g.fillRect(350, i, 10, 70);
            }
            roadmove = 0;//set the roadmove to zero
        }
        try {
            tree1 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/tree.png")));//load my png tree
            tree2 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/tree.png")));//load my png tree
            tree3 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/tree.png")));//load my png tree
        } catch (IOException e) {
            e.printStackTrace();
        }
        tree1.paintIcon(this, g, 0, tree1ypos);
        num1 = random.nextInt(500);//generate random number
        tree1ypos += 50;//increment the position of the tree
        tree2.paintIcon(this, g, 0, tree2ypos);
        num1 = random.nextInt(500);
        tree2ypos += 50;//increment the position of the tree
        tree3.paintIcon(this, g, 0, tree3ypos);
        num1 = random.nextInt(500);
        tree3ypos += 50;//increment the position of the tree
        tree1.paintIcon(this, g, 600, tree4ypos);
        tree4ypos += 50;//increment the position of the tree
        tree2.paintIcon(this, g, 600, tree5ypos);
        tree5ypos += 50;//increment the position of the tree
        tree3.paintIcon(this, g, 600, tree6ypos);
        tree6ypos += 50;//increment the position of the tree
        if (tree1ypos > 700) {
            num1 = random.nextInt(500);
            tree1ypos = -num1;//reset the position of tree
        }
        if (tree2ypos > 700) {
            num1 = random.nextInt(500);
            tree2ypos = -num1;//reset the position of tree
        }
        if (tree3ypos > 700) {
            num1 = random.nextInt(500);
            tree3ypos = -num1;//reset the position of tree
        }
        if (tree4ypos > 700) {
            num1 = random.nextInt(500);
            tree4ypos = -num1;//reset the position of tree
        }
        if (tree5ypos > 700) {
            num1 = random.nextInt(500);
            tree5ypos = -num1;//reset the position of tree
        }
        if (tree6ypos > 700) {
            num1 = random.nextInt(500);
            tree6ypos = -num1;//reset the position of tree
        }
        //load image for car
        try {
            car1 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/CAR1.png")));//load the car iamge
        } catch (IOException e) {
            e.printStackTrace();
        }
        car1.paintIcon(this, g, xpos, ypos);//draw a car on my screen 
        ypos -= 40;
        if (ypos < 500) {
            ypos = 500;
        }
        //load the other cars
        try {
            car2 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/CAR2.png")));//load the car iamge
            car3 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/CAR2.png")));//load the car iamge
            car4 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/CAR2.png")));//load the car iamge
        } catch (IOException e) {
            e.printStackTrace();
        }
        car2.paintIcon(this, g, x1pos, y1pos);
        car3.paintIcon(this, g, x2pos, y2pos);
        car4.paintIcon(this, g, x3pos, y3pos);
        y1pos += 50;
        y2pos += 50;
        y3pos += 50;
        //car will move out of the screen so let's reset the position
        if (y1pos > 700) {
            cxpos1 = random.nextInt(5);
            cypos1 = random.nextInt(5);
            y1pos = carypos[cypos1];
        }
        if (y2pos > 700) {
            cxpos2++;
            if (cxpos2 > 4) {
                cxpos2 = 0;
            }
            cxpos2 = random.nextInt(5);
            cypos2 = random.nextInt(5);
            y2pos = carypos[cypos2];
        }
        if (y3pos > 700) {
            cxpos3++;
            if (cxpos3 > 4) {
                cxpos3 = 0;
            }
            cxpos3 = random.nextInt(5);
            cypos3 = random.nextInt(5);
            y3pos = carypos[cypos3];
        }
        if (cxpos1 == cxpos2 && cypos1 > -100 && cypos2 > -100) {
            cxpos1 -= 1;
            if (cxpos1 < 0) {
                cxpos1 += 2;
            }
        }
        if (cxpos1 == cxpos3 && cypos1 > -100 && cypos3 > -100) {
            cxpos3 -= 1;
            if (cxpos3 < 0) {
                cxpos3 += 2;
            }
        }
        if (cxpos2 == cxpos3 && cypos2 > -100 && cypos3 > -100) {
            cxpos2 -= 1;
            if (cxpos2 < 0) {
                cxpos2 += 2;
            }
        }
        //more logic
        if (cxpos1 < 2 && cxpos2 < 2 && cxpos3 < 2) {
            if (cxpos1 == 0 && cxpos2 == 0 && cxpos3 == 1) {
                cxpos3++;
                cxpos2++;
            } else if (cxpos1 == 0 && cxpos2 == 1 && cxpos3 == 0) {
                cxpos3++;
                cxpos2++;
            } else if (cxpos1 == 1 && cxpos2 == 0 && cxpos3 == 0) {
                cxpos1++;
                cxpos2++;
            }
        }
        //let's put logic for gameover
        if (y1pos < ypos && y1pos + 190 > ypos && x1pos == xpos) {
            gameover = true;
        }
        if (y2pos < ypos && y2pos + 190 > ypos && x2pos == xpos) {
            gameover = true;
        }
        if (y3pos < ypos && y3pos + 190 > ypos && x3pos == xpos) {
            gameover = true;
        }
        if (ypos < y1pos && y1pos + 190 > y1pos && x1pos == xpos) {
            gameover = true;
        }
        if (ypos < y2pos && y2pos + 190 > y2pos && x2pos == xpos) {
            gameover = true;
        }
        if (ypos < y3pos && y3pos + 190 > y3pos && x3pos == xpos) {
            gameover = true;
        }
        if (gameover) {
            g.setColor(Color.green);
            g.fillRect(150, 220, 400, 200);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("New Times Roman", Font.BOLD, 50));
            g.setColor(Color.red);
            g.drawString("GAME OVER!", 210, 270);
            g.setColor(Color.white);
            g.setFont(new Font("New Times Roman", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);
            if (!paint) {
                repaint();
                paint = true;
            }
        } else {
            repaint();
        }
        //score
        g.setColor(Color.red);
        g.fillRect(120, 35, 220, 50);//border of the rectangle
        g.setColor(Color.black);
        g.fillRect(125, 40, 210, 40);
        //speed
        g.setColor(Color.red);
        g.fillRect(385, 35, 180, 50);//border of the rectangle
        g.setColor(Color.white);
        g.fillRect(390, 40, 170, 40);
        g.setFont(new Font("New Times Roman", Font.BOLD, 30));
        g.drawString("Score:" + score, 130, 67);
        g.setFont(new Font("New Times Roman", Font.BOLD, 30));
        g.drawString(speed + "Km/h", 400, 67);
        score++;
        speed++;
        if (speed > 140) {
            speed = 240 - delay;
        }
        if (speed % 50 == 0) {
            delay -= 10;
            if (delay < 60) {
                delay = 60;//reset the delay to 60
            }
        }
        try {
            TimeUnit.MICROSECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameover) {
            //if the left key is pressed then move the car to left
            xpos -= 100;
            if (xpos < 100) {
                xpos = 100;//set car to left most position
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameover) {
            //if the left key is pressed then move the car to right
            xpos += 100;
            if (xpos > 500) {
                xpos = 500;//set car to right most position
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && gameover) {
            gameover = false;
            paint = false;
            cxpos1 = 0;
            cxpos2 = 2;
            cxpos3 = 4;
            cypos1 = random.nextInt(5);
            cypos2 = random.nextInt(5);
            cypos3 = random.nextInt(5);
            y1pos = carypos[cypos1];
            y2pos = carypos[cypos2];
            y3pos = carypos[cypos3];
            x1pos = carxpos[cxpos1];
            x2pos = carxpos[cxpos2];
            x3pos = carxpos[cxpos3];
            speed = 90;
            score = 0;
            delay = 100;
            xpos = 300;
            ypos = 700;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
