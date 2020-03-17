import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.*;
import sun.audio.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Screen extends JPanel implements ActionListener, KeyListener
{
    //ArrayList<Item> myItems;
    private JButton startButton;
    private JButton exitButton;
    private JButton introButton;
    private JButton exitintroButton;
    private JButton MenuButton;
    //private JButton SkipButton;

    private String gameplayState;
    private String dialog = "";
    private String dialogColor = "blue";
    //private String text1;
    
    private Clip clip1;
    private Clip clip;
    Map[] maps;
    
    private int blockSize = 50; //every block is 20x20
    private int displayWidth = 15; //measured in blocks
    private int displayHeight = 13;
    private int playerRow;
    private int playerCol;
    private BufferedImage Grass;
    private BufferedImage Water;
    private BufferedImage Mountain;
    private BufferedImage Knight;
    private BufferedImage Key;
    private BufferedImage Sword;
    private BufferedImage Wall;
    private BufferedImage Smashwall;
    private BufferedImage Lockdoor;
    private BufferedImage Passdoor1;
    private BufferedImage Teacher;
    private BufferedImage Background;
    private BufferedImage Herb;
    private BufferedImage BleedingSoldier;
    private BufferedImage Soldier;
    private BufferedImage intro;
    private BufferedImage Shield;
    private BufferedImage GreenKey;
    private BufferedImage Stranger;
    private BufferedImage Lava;
    private BufferedImage redKey;
    private BufferedImage Armor;
    private BufferedImage dragon;
    private BufferedImage dragonbody;
    private BufferedImage castle;
    private BufferedImage superSword;
    private BufferedImage BossDoor;
    private BufferedImage Paper;
    private BufferedImage Person;
    private BufferedImage Dragon1;
    private BufferedImage BlackKey;
    private BufferedImage Finish;
    private BufferedImage Lock;
    private String Passcode = " ";
    private int HerbNumber = 0;
    private int times = 0;
    private int total = 0;
    private boolean teacherHasSword;
    private boolean herbmission = false;
    private boolean hasShield = false;
    private boolean hasArmor = false;
    private boolean hasSword;
    private boolean hassuperSword;
    private boolean hasKey2;
    private boolean hasKey3;
    private boolean hasKey4;
    private int time = 0;
    
    Font font1;
    //private boolean hasSword = false;
    private boolean hasKey = false;
    ArrayList<Item> myItems;
    
    Level lev;

    public Screen()
    {
        setLayout(null);
        lev = new Level();

        maps = new Map[5];        
        //level = lev.getlevel();
        maps[0] = new Map(0);
        maps[1] = new Map(1);
        maps[2] = new Map(2);
        maps[3] = new Map(3);
        maps[4] = new Map(4);
        playerRow = maps[0].getPlayerStartRow();
        playerCol = maps[0].getPlayerStartCol();
        
		
		
		//import images
		try
        {
            Background = ImageIO.read(new File("Background.jpg"));
            
            Grass = ImageIO.read(new File("Grass.jpg"));
            Water = ImageIO.read(new File("Water.jpg"));
            Mountain = ImageIO.read(new File("Mountain.jpg"));
            Teacher = ImageIO.read(new File("Teacher.png"));
            Key = ImageIO.read(new File("Key.png"));
            Knight = ImageIO.read(new File("Knight.png"));
            Sword = ImageIO.read(new File("Sword.png"));
            Wall = ImageIO.read(new File("Wall.jpg"));
            Lockdoor = ImageIO.read(new File("Lockdoor.jpg"));
            Smashwall = ImageIO.read(new File("Smashwall.jpg"));
            Passdoor1 = ImageIO.read(new File("Passdoor1.png"));
            intro = ImageIO.read(new File("Introduction.jpg"));
            BleedingSoldier = ImageIO.read(new File("BleedingSoldier.png"));
            Herb = ImageIO.read(new File("Herb.jpg"));
            Soldier = ImageIO.read(new File("Soldier.png"));
            Shield = ImageIO.read(new File("Shield.png"));
            GreenKey = ImageIO.read(new File("Key2.png"));
            Lava = ImageIO.read(new File("Lava.jpg"));
            Stranger = ImageIO.read(new File("Stranger.png"));
            redKey = ImageIO.read(new File("Key3.png"));
            Armor = ImageIO.read(new File("Armor.png"));
            dragon = ImageIO.read(new File("dragon.png"));
            dragonbody = ImageIO.read(new File("dragonbody.png"));
            superSword = ImageIO.read(new File("Sword1.png"));
            castle = ImageIO.read(new File("Castle.png"));
            BossDoor = ImageIO.read(new File("BossDoor.jpg"));
            Paper = ImageIO.read(new File("Paper.png"));
            Person = ImageIO.read(new File("Person.png"));
            BlackKey = ImageIO.read(new File("Key4.png"));
            Lock = ImageIO.read(new File("Lock.jpg"));
            Dragon1 = ImageIO.read(new File("Dragon1.png"));
            Finish = ImageIO.read(new File("Finish.jpg"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try
        {
            URL newGameSound = this.getClass().getClassLoader().getResource("Menu.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(newGameSound));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }

		//startbutton
		startButton = new JButton("Start Game");
		startButton.setBounds(325,200,150,30);
		startButton.addActionListener(this);
		this.add(startButton);
		gameplayState = "menu";
		addKeyListener(this);
		//exitButton
		exitButton = new JButton("Exit Game");
		exitButton.setBounds(325,300,150,30);
		exitButton.addActionListener(this);
		this.add(exitButton);
		//introduction
		introButton = new JButton("Introduction");
		introButton.setBounds(325,250,150,30);
		introButton.addActionListener(this);
		this.add(introButton);
		//Exit the introduction
		exitintroButton = new JButton("Back");
		exitintroButton.setBounds(600,530,150,30);
		exitintroButton.addActionListener(this);
		this.add(exitintroButton);
		exitintroButton.setVisible(false);
		// go back to Menu
		MenuButton = new JButton("Menu");
		MenuButton.setBounds(750,20,60,30);
		MenuButton.addActionListener(this);
		this.add(MenuButton);
		MenuButton.setVisible(false);
		//skip level
		/*SkipButton = new JButton("Skip");
		SkipButton.setBounds(740,70,60,30);
		SkipButton.addActionListener(this);
		this.add(SkipButton);
		SkipButton.setVisible(false);*/
		//addKeyListener(this);
    }    
    
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

	public Dimension getPreferredSize() 
    {
        //Sets the size of the panel
        return new Dimension(800,600);
    }

	public void paintComponent(Graphics g)
	{
	    g.setColor(Color.black);
	    g.fillRect(740,0,800,600);
	    
	    /*if(gameplayState.equals("playing"))
	    {
	        if(lev.getLevel() == 0)
	            g.drawString("Level : 1" ,150 , 100);
	        if(lev.getLevel() == 1)
	            g.drawString("Level : 2" ,150 , 100);
	        if(lev.getLevel() == 2)
	            g.drawString("Level : 3" ,150 , 100);
	        if(lev.getLevel() == 3)
	            g.drawString("Level : Boss",150 , 100);
	    }*/
	    if(gameplayState.equals("menu"))
	    {
            g.drawImage(Background, 0, 0, null);
            Font font = new Font("Arial", Font.PLAIN, 50);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString("SWORD OF DRAGON", 150, 100);

	    }
	    else if(gameplayState.equals("introduction"))
	    {
	        g.drawImage(intro, 0, 0, null);
	        //g.setFont(new Font("Purisa", Font.PLAIN, 25));
	        Font font = new Font("Arial",Font.PLAIN, 15);
		    Color blue = new Color(0,0,255);
		    g.setColor(blue);

		    String text = "10 Years ago, an evil dragon came to this country, he burned and looted everything he saw, the people there live in the verge of despair. You are the strongest warrior in this country, and you are going to save your country!";
		
		    //drawText(String text, Font font, Graphics g, int width, int startX, int startY)
		    DrawString.drawText(text, font, g, 200, 100, 120);
		    text = "Press [a], [w], [s], [d] to move the character, and press [p] to skip the level";
		    font = new Font("Arial",Font.PLAIN, 40);
		    DrawString.drawText(text, font, g, 500, 340, 120);
		    repaint();
	    }
        else if(gameplayState.equals("playing"))
        {
           
            //if(lev.getLevel == 4)
                
            //Font font = new Font("Arial", Font.PLAIN, 50);
            //g.setFont(font);
            //g.setColor(Color.red);
            for(int row = 0; row<displayHeight; row++)
            {
                for(int col=0; col<displayWidth; col++)
                {
                    int mapRow = row+playerRow-displayHeight/2;
                    int mapCol = col+playerCol-displayWidth/2;
                    if(mapRow < 0 || mapCol < 0 || mapRow >= maps[lev.getLevel()].getHeight() || mapCol >= maps[lev.getLevel()].getWidth())
                        if(lev.getLevel() == 0 || lev.getLevel() == 1 || lev.getLevel() == 2)
                            g.drawImage(Water, col*blockSize, row*blockSize, null);
                        else
                            g.drawImage(Lava, col*blockSize, row*blockSize, null);
                    else if(maps[lev.getLevel()].get(mapRow, mapCol) == '#') //# is wall
                        g.drawImage(Mountain, col*blockSize, row*blockSize, null);
                    else if(maps[lev.getLevel()].get(mapRow, mapCol) == '@') //@ is castle wall
                        g.drawImage(castle, col*blockSize, row*blockSize, null);
                    //else if(maps[lev.getLevel()].get(mapRow, mapCol) == '&') //# is wall
                       // g.drawImage(Finish, col*blockSize, row*blockSize, null);
                    else
                    {
                        //not mountain or water, then ALWAYS draw grass, then whatever else
                        g.drawImage(Grass, col*blockSize, row*blockSize, null);
                        if(maps[lev.getLevel()].get(mapRow, mapCol) == 'K') //# is wall
                            g.drawImage(Key, col*blockSize+blockSize/2, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'S') //# is wall
                            g.drawImage(Sword, col*blockSize-blockSize/2, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'T') //# is wall
                            g.drawImage(Teacher, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'D') //# is wall
                            g.drawImage(Passdoor1, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'd') //# is wall
                            g.drawImage(Passdoor1, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'W') //# is wall
                            g.drawImage(Wall, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'w') //# is wall
                            g.drawImage(Smashwall, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'L') //# is wall
                            g.drawImage(Lockdoor, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'm') //# is wall
                            g.drawImage(BleedingSoldier, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'H') //# is wall
                            g.drawImage(Herb, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'M') //# is wall
                            g.drawImage(Soldier, col*blockSize, row*blockSize, null);
                        //else if(maps[lev.getLevel()].get(mapRow, mapCol) == 's') //# is wall
                         //   g.drawImage(Shield, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'G') //# is wall
                            g.drawImage(GreenKey, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'R') //# is wall
                            g.drawImage(Stranger, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'r') //# is wall
                            g.drawImage(redKey, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'A') //# is wall
                            g.drawImage(Armor, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'k') //# is wall
                            g.drawImage(dragon, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'o') //# is wall
                            g.drawImage(dragonbody, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'Z') //# is wall
                            g.drawImage(superSword, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'B') //# is wall
                            g.drawImage(Passdoor1, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'O') //# is wall
                            g.drawImage(BossDoor, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'g') //# is wall
                            g.drawImage(Paper, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'I') //# is wall
                            g.drawImage(Person, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'i') //# is wall
                            g.drawImage(BlackKey, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'u') //# is wall
                            g.drawImage(Lock, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'U') //# is wall
                            g.drawImage(Dragon1, col*blockSize, row*blockSize, null);
                        else if(maps[lev.getLevel()].get(mapRow, mapCol) == 'C') //# is wall
                            g.drawImage(Finish, col*blockSize, row*blockSize, null);
                    }
                }
            }
            g.drawImage(Knight, displayWidth/2*blockSize, displayHeight/2*blockSize, null);
            
            hasSword = false;
            hasKey = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Shield)
                {
                    hasShield = true;
                }
                if(myItems.get(i) instanceof Sword)
                {
                    hasSword = true;
                }
                if(myItems.get(i) instanceof Armor)
                {
                    hasArmor = true;
                }
                if(myItems.get(i) instanceof Sword2)
                {
                    hassuperSword = true;
                }
                
            }
            if(hasSword)
                g.drawImage(Sword, displayWidth/2*blockSize, displayHeight/2*blockSize, null);
            if(hasArmor)
                g.drawImage(Armor, displayWidth/2*blockSize+10, displayHeight/2*blockSize+15, null);
            if(hasShield)
                g.drawImage(Shield, displayWidth/2*blockSize, displayHeight/2*blockSize, null);
            if(hassuperSword)
                g.drawImage(superSword, displayWidth/2*blockSize, displayHeight/2*blockSize, null);
            
            if(lev.getLevel() == 0)
	            g.drawString("Level : 1" ,150 , 20);
	        if(lev.getLevel() == 1)
	            g.drawString("Level : 2" ,150 , 20);
	        if(lev.getLevel() == 2)
	            g.drawString("Level : 3" ,150 , 20);
	        if(lev.getLevel() == 3)
	            g.drawString("Level : Boss",150 , 20);
	            
            for(int i=0;i<myItems.size();i++)
                {
                //Font font2 = new Font("Arial",Font.PLAIN, 50);
                g.setColor(Color.red);
                g.drawString(myItems.get(i).toString(), 50, 10 + 30*i);
                }
            
            if(dialog.length() != 0)
            { 
                font1 = new Font("Arial",Font.PLAIN, 20);
                g.setColor(Color.black);
                g.fillRect(100, 450, 800,600);
                g.setColor(Color.white);
                g.fillRect(130, 480, 800,600);
                g.drawLine(100,450,130,480);
                if(dialogColor.equals("green"))
                {
                    g.setColor(Color.green);
                }
                else if(dialogColor.equals("blue"))
                {
                    g.setColor(Color.blue);
                }
                else if(dialogColor.equals("red"))
                {
                    g.setColor(Color.red);
                }
                else if(dialogColor.equals("yellow"))
                {
                    g.setColor(Color.yellow);
                }
                //drawText(String text, Font font, Graphics g, int width, int startX, int startY)
		    
                DrawString.drawText(dialog, font1, g, 800, 150, 500);
                dialog = "";
            }
        }
	
	        
	
	
	
	}

	//play the music
	public void playsound()
	{
	    if(lev.getLevel() == 0 && gameplayState.equals("playing"))
        {
            try
            {
                URL level0 = this.getClass().getClassLoader().getResource("Level2.wav");
                clip1 = AudioSystem.getClip();
                clip1.open(AudioSystem.getAudioInputStream(level0));
                clip1.start();
            }
                catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
        if(lev.getLevel() == 2 && gameplayState.equals("playing"))
        {
            clip1.stop();
            try
            {
                URL level2 = this.getClass().getClassLoader().getResource("Level0.wav");
                clip1 = AudioSystem.getClip();
                clip1.open(AudioSystem.getAudioInputStream(level2));
                clip1.start();
            }
                catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
        if(lev.getLevel() == 3 && gameplayState.equals("playing"))
        {
            clip1.stop();
            try
            {
                URL level3 = this.getClass().getClassLoader().getResource("FinalMusic.wav");
                clip1 = AudioSystem.getClip();
                clip1.open(AudioSystem.getAudioInputStream(level3));
                clip1.start();
            }
                catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
        if(lev.getLevel() == 4 && gameplayState.equals("playing"))
        {
            clip1.stop();
            try
            {
                URL level4 = this.getClass().getClassLoader().getResource("Finish.wav");
                clip1 = AudioSystem.getClip();
                clip1.open(AudioSystem.getAudioInputStream(level4));
                clip1.start();
            }
                catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
        }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == startButton )
        {
            System.out.println("Clicked the start button");
            startButton.setVisible(false);
            introButton.setVisible(false);
            exitButton.setVisible(false);
            exitintroButton.setVisible(false);
            MenuButton.setVisible(true);
            //SkipButton.setVisible(true);
            gameplayState = "playing";
            myItems = new ArrayList<Item>();
            this.playsound();
			repaint();
			requestFocus();
			clip.stop();
		}
		else if( e.getSource() == exitButton )
        {
            System.exit(0);
		}
		else if( e.getSource() == introButton )
        {
            startButton.setVisible(false);
            introButton.setVisible(false);
            exitButton.setVisible(false);
            gameplayState = "introduction";
            exitintroButton.setVisible(true);
		}
		else if( e.getSource() == exitintroButton )
        {
            startButton.setVisible(true);
            introButton.setVisible(true);
            exitButton.setVisible(true);
            exitintroButton.setVisible(false);
            gameplayState = "menu";
          
		}
		else if( e.getSource() == MenuButton )
        {
            startButton.setVisible(true);
            introButton.setVisible(true);
            exitButton.setVisible(true);
            MenuButton.setVisible(false);
            //SkipButton.setVisible(false);
            gameplayState = "menu";
            clip.start();
            clip1.stop();
            repaint();
        }
        /*else if( e.getSource() == SkipButton )
        {
            //g.setColor(Color.black);
            //g.fillRect(100, 450, 800,600);
            //g.setColor(Color.white);
            //g.fillRect(130, 480, 800,600);
            g.drawLine(100,450,130,480);
            Font font = new Font("Arial",Font.PLAIN, 25);
		    Color red = new Color(255,0,0);
		    g.setColor(red);

		    text1 = "Please Press key 'P'!";
		    //drawText(String text, Font font, Graphics g, int width, int startX, int startY)
		    DrawString.drawText(text1, font, g, 200, 100, 120);
        }*/
	}

    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY TYPED: ");
        System.out.println("Typed: " + e.getKeyChar());
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
        //text1 = "";
        System.out.println("Pressed: " + e.getKeyChar());
        if(e.getKeyChar() == 's' && playerRow < maps[lev.getLevel()].getHeight()-1 && maps[lev.getLevel()].get(playerRow+1, playerCol) != '#'  )
            playerRow++;
        else if(e.getKeyChar() == 'w' && playerRow > 0 && maps[lev.getLevel()].get(playerRow-1, playerCol) != '#')
            playerRow--;
        else if(e.getKeyChar() == 'a' && playerCol > 0 && maps[lev.getLevel()].get(playerRow, playerCol-1) != '#')
            playerCol--;
        else if(e.getKeyChar() == 'd' && playerCol < maps[lev.getLevel()].getHeight()-1 && maps[lev.getLevel()].get(playerRow, playerCol+1) != '#')
            playerCol++;
        else if(e.getKeyChar() == 'p' && gameplayState.equals("playing"))
        {
            lev.addLevel();
            this.playsound();
            if(lev.getLevel() == 1)
            {
                myItems.add(new Key());
                myItems.add(new Sword());
                teacherHasSword = true;
            }
            else if(lev.getLevel() == 2)
            {
                myItems.add(new GreenKey());
                myItems.add(new Shield());
            }
            else if(lev.getLevel() == 3)
            {
                myItems.add(new RedKey());
                for(int i=0;i<myItems.size();i++)
                {
                    if(myItems.get(i) instanceof Sword)
                        myItems.remove(i);
                }
            
                myItems.add(new Sword2());
                myItems.add(new Armor());
            }
            playerRow = maps[lev.getLevel()].getPlayerStartRow();
            playerCol = maps[lev.getLevel()].getPlayerStartCol();
        }            
        boolean backup = false;
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'K')
        {
            //hasKey = true;
            myItems.add(new Key());
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Key.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [Blue Key]!";
                dialogColor = "blue";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'C')
        {
            backup = true;
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'I')
        {
            //hasKey = true;
            backup = true;
            dialogColor = "green";
            dialog = "Hey, young man. You need the Black Key to enter that dragon's room. Good Luck!";
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == '@')
        {
            backup = true;
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'S')
        {
            //hasSword = true;
            myItems.add(new Sword());
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Sword.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [TEACHER'S SWORD]!";
                dialogColor = "blue";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'U')
        {
            hassuperSword = false;
           for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Sword2)
                {
                    hassuperSword = true;
                }
            }
            if(hassuperSword)
            {
                lev.addLevel();
                this.playsound();
                playerRow = maps[lev.getLevel()].getPlayerStartRow();
                playerCol = maps[lev.getLevel()].getPlayerStartCol();
                dialog = "Game finished";
                dialogColor = "yellow";
                //accv
            }
            maps[lev.getLevel()].set(playerRow, playerCol, 'o');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'g')
        {
            myItems.add(new Paper());
            dialogColor = "red";
            dialog = "If anyone find this note,then that means I may already dead. I'm going to kill that Dragon because I don't want this country to keep suffering like this. I leave this note for letting people remember me, and be careful!";
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'i')
        {
            myItems.add(new Key4());
            dialogColor = "blue";
            dialog = "You found a Black Key!";
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Key.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [Green Key]!";
                dialogColor = "blue";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'Z')
        {
            //hasSword = true;
            myItems.add(new Sword2());
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Sword.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [Dragonbreath Slicer]!";
                dialogColor = "yellow";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'u')
        {
           hasKey4 = false;
           for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Key4)
                {
                    hasKey4 = true;
                }
            }
           if(hasKey4)
           {
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("OpenLock.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                 }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
                maps[lev.getLevel()].set(playerRow, playerCol, '_');
                dialog = "Dragon: You are the Second Challenger! [ROAR] Ignorant human never learn their lesson!";
                dialogColor = "red";
           
           }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'O')
        {
            hasKey = false;
            hasKey2 = false;
            hasKey3 = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Key)
                {
                    hasKey = true;
                }
                if(myItems.get(i) instanceof GreenKey)
                {
                    hasKey2 = true;
                }
                if(myItems.get(i) instanceof RedKey)
                {
                    hasKey3 = true;
                }
             }
             if(hasKey && hasKey2 && hasKey3)
             {
                dialogColor = "blue";
                dialog = "You opened a lock!";
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("OpenLock.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                 }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
                maps[lev.getLevel()].set(playerRow, playerCol, '_');
            }
            else
            {
                backup = true;
                dialogColor = "red";
                dialog = "You need Red Key, Blue Key and Green Key to open this lock!";
            }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'D')
        {
            boolean hasKey = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Key)
                {
                    hasKey = true;
                }
            }
            if(hasKey)
            {
                lev.addLevel();
                this.playsound();
                playerRow = maps[lev.getLevel()].getPlayerStartRow();
                playerCol = maps[lev.getLevel()].getPlayerStartCol();
                dialog = "Next Level!";
                dialogColor = "blue";
            }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'd')
        {
            hasKey2 = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof GreenKey)
                {
                    hasKey2 = true;
                }
            }
            if(hasKey2)
            {
                lev.addLevel();
                this.playsound();
                playerRow = maps[lev.getLevel()].getPlayerStartRow();
                playerCol = maps[lev.getLevel()].getPlayerStartCol();
                dialog = "Next Level!";
                dialogColor = "blue";
            }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'B')
        {
            hasKey3 = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof RedKey)
                {
                    hasKey3 = true;
                }
            }
            if(hasKey3)
            {
                lev.addLevel();
                this.playsound();
                playerRow = maps[lev.getLevel()].getPlayerStartRow();
                playerCol = maps[lev.getLevel()].getPlayerStartCol();
                dialog = "Boss Level!";
                dialogColor = "red";
            }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'T')
        {
            boolean hasSword = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Sword)
                {
                    hasSword = true;
                }
            }
            if(hasSword)
            {
                teacherHasSword = true;
                dialog = "Great job! You have to have the pass key in turn to leave here, this sword will help you, good luck!";
                dialogColor = "green";
            }
            else
            {
                dialogColor = "green";
                dialog = "You are the only hope of this country. it's time to save this country, but you have to find my sword first, and come back to see me after you find my sword! ";
            }
            backup = true;
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'W')
        {
            if(!teacherHasSword)
            {
                backup = true;
                dialogColor = "blue";
                dialog = "You need to bring the sword to the teacher!";
            }
            else
            {
                maps[lev.getLevel()].set(playerRow, playerCol, 'w');
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("StoneBreaking.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                    dialogColor = "red";
                    dialog = "CRUNCH!";
                 }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
            }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'L')
        {
            boolean hasKey = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Key)
                {
                    hasKey = true;
                }
            }        
            if(!hasKey)
                backup = true;
            else
            {
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("OpenLock.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                 }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
                maps[lev.getLevel()].set(playerRow, playerCol, '_');
            }
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'G')
        {
            //hasKey = true;
            myItems.add(new GreenKey());
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Key.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [Green Key]!";
                dialogColor = "blue";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'm')
        {
            dialogColor = "green";
            herbmission = true;
            backup = true;
            /*for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Herb)
                {
                    HerbNumber++;
                }
            }        */
            if(HerbNumber != 2)
            {
                //backup = true;
                dialog = "I'm hurt! Could you help me to find 2 [Cryptic Plant]? Please help me!!";
            }
            else
            {
                //backup = true;
                for(int i=0;i<myItems.size();i++)
                {
                    if(myItems.get(i) instanceof Herb)
                    {
                        myItems.remove(i);
                        i--;
                    }
                }
                maps[lev.getLevel()].set(playerRow, playerCol, 'M');
                dialog = "Thank you so much for saving my life! My name is James, and I just came from the battlefront. The situation is getting worth, we can't hurt that dragon by the normal weapon!";
            }
            //System.out.println(HerbNumber);
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'R')
        {
            //int times = 0;
            hassuperSword = false;
            hasKey3 = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof RedKey)
                {
                    hasKey3 = true;
                }
                if(myItems.get(i) instanceof Sword2)
                {
                    hassuperSword = true;
                }
            }        
            times++;
            backup = true;
            dialogColor = "green";
            if(!hasKey3)
            {
                if(times == 1)
                    dialog = "hello, young man. I've been staying here for a long time, and I've saw a lot of young man like you want to save their country, but none of them made that.";
                else if(times == 2)
                {
                    dialog = "You are the strongest man I ever seen before, I decide to give you a hand. Go to the South place, there is a place there called 'Warrior Road', if you can go through that place alive, then I will give you more information and help!"; 
                    maps[lev.getLevel()].set(27, 21, '_');
                }
            }
            else
            {
                if(time == 0)
                {
                    dialog = "Here you are! You are the first one who can go through that road and come back to me! You are the man I've always been waiting for! I'm going to tell you some really important information, listen carefully!";
                    time++;
                }
                else if(time == 1)
                {
                    dialog = "That evil dragon has a huge castle, you have to have all [redKey] [greenKey] and [blueKey] to get in. As you know, the normal weapon can't even hurt that dragon, But I know there is a sword called  [Dragonbreath Slicer] can kill that evil dragon, and that sword is somewhere here, Go find that and come back to see me.";
                    maps[lev.getLevel()].set(18, 0, '_');
                    maps[lev.getLevel()].set(18, 1, '_');
                }
            }
            if(hassuperSword)
                {
                    dialog = "great job!!! You are a brave young man, this country is waiting for you to protect, Go Kill That Evil Dragon!!!";
                    maps[lev.getLevel()].set(2, 19, '_');
                
                }
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'r')
        {
            //backup = true;
            //hasKey3 = true;
            myItems.add(new RedKey());
            try
            {
                URL newGameSound = this.getClass().getClassLoader().getResource("Key.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(newGameSound));
                clip.start();
                dialog = "You found the [Red Key]!";
                dialogColor = "blue";
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
         
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'A')
        {
            //backup = true;
            myItems.add(new Armor());
            dialog = "You found the [Armor]!";
            dialogColor = "blue";
            maps[lev.getLevel()].set(playerRow, playerCol, '_');
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'k')
        {
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Sword2)
                {
                hasSword = true;
                }
            }
            if(hasSword && hasShield)
            {
                maps[lev.getLevel()].set(playerRow, playerCol, 'o');
                total++;
                
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("dragon.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                    dialog = "You killed a dragon!";
                    dialogColor = "red";
                }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
            }
            else if(!hasSword || !hasShield)
            {
                backup = true;
                dialog = "You need a sword and a shield to kill this dragon!";
                dialogColor = "red";
            }
            //System.out.println(total);
            if(total == 9)
            {
                maps[lev.getLevel()].set(28, 8, '_');
                maps[lev.getLevel()].set(27, 8, '_');
                maps[lev.getLevel()].set(26, 8, '_');
            }
            else if(total == 17)
            {
                try
                {
                    URL newGameSound = this.getClass().getClassLoader().getResource("SwordCrash.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(newGameSound));
                    clip.start();
                    //dialog = "You killed a dragon!";
                    //dialogColor = "red";
                }
                catch (Exception exc)
                {
                    exc.printStackTrace(System.out);
                }
                
                
                //maps[lev.getLevel()].set(4, 1, 'o');
                for(int i=0;i<myItems.size();i++)
                {
                    if(myItems.get(i) instanceof Sword)
                    {
                        myItems.remove(i);
                        i--;
                    }
                }
                dialog = "Your Sword has broken!!!";
                
                hasSword = false;
            }
        //hassuperKey = false;
        /*for(int i=0;i<myItems.size();i++)
        {
            if(myItems.get(i) instanceof Sword1)
            {
                hasSword = true;
            }
        }*/
        
        
        
        }
        else if(maps[lev.getLevel()].get(playerRow, playerCol) == 'M')
        {
            hasShield = false;
            for(int i=0;i<myItems.size();i++)
            {
                if(myItems.get(i) instanceof Shield)
                {
                    hasShield = true;
                }
            }
            backup = true;
            if(!hasShield)
            {
                myItems.add(new Shield());
                dialog = "I have a shield with me, but I don't think I can use it anymore, I wish you can accept this little gift from me! By the way, you have to find the Green Key to leave this place!";
                maps[lev.getLevel()].set(19, 13, '_');
                maps[lev.getLevel()].set(19, 12, '_');
                maps[lev.getLevel()].set(19, 11, '_');
            }
            else if(hasShield)
                //maps[lev.getLevel()].set(19, 13, '_');
                //maps[lev.getLevel()].set(19, 12, '_');
                //maps[lev.getLevel()].set(19, 11, '_');
                dialog = "Go find the [Green Key] and leave this place. You got my best wish!";
        }
        if(maps[lev.getLevel()].get(playerRow, playerCol) == 'H')
        {  
            dialogColor = "blue";
            if(!herbmission)
            {
                backup = true;
                dialog = "Failed to get!";
            }
            else
            {
                myItems.add(new Herb());
                dialog = "You found the [cryptic plant]!";
                HerbNumber++;
                maps[lev.getLevel()].set(playerRow, playerCol, '_');
            }
         }
        if(backup)
        {
            if(e.getKeyChar() == 's')
                playerRow--;
            else if(e.getKeyChar() == 'w')
                playerRow++;
            else if(e.getKeyChar() == 'a')
                playerCol++;
            else if(e.getKeyChar() == 'd')
                playerCol--;
        }

        repaint();
        System.out.println("Pressed: " + e.getKeyChar() + " on " + maps[lev.getLevel()].get(playerRow, playerCol));
        
        }
    
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }
    
    /*public void animate()
    {
        while(true)
        {
            //wait for .01 second
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
 
             
            for(int i=0; i<rainArray.length; i++)
            {
                rainArray[i].move();
            }
			
			 for(int i=0; i<SnowArray.length; i++)
            {
                SnowArray[i].move();
            }
			
			
			if(count == 500)
			{
				season = 2;
			}
			else if(count == 1000)
			{
				season = 3;
			}
			else if(count == 1500)
			{
				season = 4;
			}
            //repaint the graphics drawn
            repaint();
        }
 
    }*/

}












