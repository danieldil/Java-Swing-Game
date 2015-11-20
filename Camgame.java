// Border run Main

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Camgame extends JComponent implements ActionListener,  KeyListener{
int     LocationX               = 0,
        LocationY               = 0;
int     locationXofShirt1;
int     locationYofShirt1;                  //the y coordinate of security 1
int     dy                      = 0;        //the y coordinate of security 2
int     dy3                     = 459;      //the change in the security 1
int     dy1                     = 0;        //the change in the security 2
int     dy2                     = 0;
int     endguysize              = 20;

int     lives                   = 3;
boolean	canLoseLives			= true;
static String name;
public static Image img;
public static Image heart3;
public static Image heart2;
public static Image heart1;
public static Image bg;
public boolean Move = true,
Tshirt1 = true,
Tshirt2 = true,
win = false;
String  other;
static boolean called = true;
//set color of character
static Color CamColor = Color.blue;
//set color of background tiles
static Color rcolor = Color.LIGHT_GRAY;
//  static Color gate1Color = Color.red;
//  static Color gate2Color = Color.green;
//create and instantiate the Rectangle for the character
Rectangle CamRect = new Rectangle();
//create all the tiles reference variables into an array object in a 2d array
static Tile tiles[][] = new Tile[24][12];

//start MAIN
public static void main(String[] args){
	//initialize speech    
    
voce.SpeechInterface.init("C:\\Users\\Daniel\\SkyDrive\\myEclipseFiles\\voce\\lib\\", true, false, "", "");

//make a jframe reference variable and call constructor with "pong game"
//as the string and instantiate the object
JFrame window = new JFrame("Pong Game");
//make a reference variable called game and call the constructor of camgame to instantiate it
Camgame game = new Camgame();
//add game to the jframe (note game is a jcomponent because it extends it)"
window.add(game);
//call the pack function of jcomponenet
window.pack();
//set the default close operation to exit on close
window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
window.setLocationRelativeTo(null);
//set it as visible
window.setVisible(true);    
//add a keylistener to this jcomponent and send the parameter game
window.addKeyListener(game); 
ImageIcon i = new ImageIcon("C:/Users/Daniel/Desktop/diran.png");
img = i.getImage();
ImageIcon i1 = new ImageIcon("C:/Users/Daniel/Desktop/HEARTS/1heart.png");
heart1 = i1.getImage();
ImageIcon i2 = new ImageIcon("C:/Users/Daniel/Desktop/HEARTS/2hearts.png");
heart2 = i2.getImage();
ImageIcon i3 = new ImageIcon("C:/Users/Daniel/Desktop/HEARTS/3hearts.png");
heart3 = i3.getImage();
ImageIcon i4 = new ImageIcon("C:/Users/Daniel/Desktop/maze.jpg");
bg = i4.getImage();

name =JOptionPane.showInputDialog("What is your name?");


//make a timer instance and send these two arguements
Timer t = new Timer (1, game);
t.start();


}   
//make a function that returns a dimension as the size
public Dimension getPreferredSize()
{
return new Dimension(1200,600);
}
@Override
public void paintComponent (Graphics g)
{
    dy = dy +dy1;
    if(dy <= 0)
        dy1 = 1;
    if(dy >= 450)
        dy1 = -1;
    dy3 = dy3 + dy2;
    if(dy3 <= 0)
        dy2 = 2;
    if(dy3 >= 450)
        dy2 = -2;
/*
 make 2 for loops so the 2 dimesions can be accessed. 
 first instantiate the object sending the parameters of count times 50, becasue the characters are 50x50
 set each color to rcolor
 draw each one
 set each sight to false    
 */
    for (int i = 0; i < 24; i++) 
    {
        for (int j = 0; j < 12; j++) 
        {
            tiles[i][j] = new Tile(i*50,j*50);
            tiles[i][j].color = rcolor;
            g.setColor(tiles[i][j].color);
            g.fillRect(i*50, j*50, tiles[0][0].HEIGHT, tiles[0][0].WIDTH);
            tiles[i][j].Sight = false;
        }
    }
  //draw the background
    g.drawImage(bg, 0,0,null);
    //draw the hearts
    if (lives == 3)
    {

        g.drawImage(heart3, 50,350,null);
    }
    else if (lives == 2)
    {

        g.drawImage(heart2, 50,350,null);
    }
    else if (lives ==1)
    {

        g.drawImage(heart1, 50,350,null);
    }
    else
    {
        System.exit(0);
    }


//now draw the bad guy on top of the background
//set the color for the graphics
g.setColor(Color.red);
//draw the bad guy
g.fillRect(500, 0, 50, 700);
g.setColor(Color.green);
g.fillRect(800, 0, 50, 700);
//make a rectangle for the bad guys
Rectangle badGuy = new Rectangle(500,0,50,700);
Rectangle badGuy1 = new Rectangle(800,0,50,700);
//MAKE SECURITY GUARD
Rectangle security1 = new Rectangle(500,dy,50,200);
g.setColor(Color.black);
g.fillRect(500,dy,50,200);
if(CamRect.intersects(security1))
{loseLife();}
//MAKE SECURITY GUARD 2
Rectangle security2 = new Rectangle(800,dy3,50,200);
g.setColor(Color.black);
g.fillRect(800,dy3,50,200);
if(CamRect.intersects(security2))
{loseLife();}
//set the size of the rectangle
//badGuy.setBounds(500,0,100,600);
//now set the color of the character
g.setColor(CamColor);
//draw the character at the current location
//g.drawImage(img, LocationX, LocationY, null)
g.fillRect(LocationX,LocationY,38,38);
g.drawImage(img, LocationX - 25,LocationY - 33,null);

//make the rectangle of the current location of the character
CamRect.setBounds(LocationX,LocationY,50,50);
canLoseLives = true;


//if the character intersects the background of dimension 0,5 set the color of the character to red
//and set the tshirt1 boolean to false
if(Tshirt1 == true)
if(CamRect.intersects(tiles[5][5].R))
{
CamColor = Color.red;
Tshirt1 = false;
voce.SpeechInterface.synthesize("Congratulations ."+name+" on Achieving the Red Key");
}
if(CamRect.intersects(badGuy))
{
	if(CamColor == Color.red)
	{}
	else
	{
		loseLife();}
	}
	g.setColor(Color.red);
//while the tshirt1 is true draw the red circle tshirt1 becomes false when the 
if(Tshirt1)
	g.fillOval(tiles[5][5].x,tiles[5][5].y,tiles[0][5].WIDTH,tiles[0][5].HEIGHT);
//T SHIRT BLUE
if(Tshirt2==true)
if(CamRect.intersects(tiles[14][10].R))
{
	CamColor = Color.green;
	Tshirt2 = false;
	voce.SpeechInterface.synthesize("Congratulations ."+name+"on Achieving the GREEN Key");
}
g.setColor(Color.green);
if(Tshirt2)
	g.fillOval(tiles[14][10].x,tiles[14][10].y,tiles[10][10].WIDTH,tiles[10][10].HEIGHT);
if(CamRect.intersects(badGuy1))
{
	if(CamColor == Color.green)
{}
else
{loseLife();}
}

g.setColor(Color.yellow);
g.fillOval(1100, 100, endguysize, endguysize);
Rectangle oval = new Rectangle(1100,100,endguysize,endguysize);
if(CamRect.intersects(oval))
{   
win = true;
JFrame frame = new JFrame("JOptionPane showMessageDialog example");
JOptionPane.showMessageDialog(frame, "You Have Won! Click Okay");}
}
public void actionPerformed(ActionEvent e)
{
	if(!win)
repaint(); 

}

public void keyPressed(KeyEvent e){
int keyCode = e.getKeyCode();
if (keyCode == e.VK_UP )
{
if(LocationY >= 0)
    {
        LocationY -= 17;
    }
}
else if (keyCode == e.VK_DOWN )
{
if(LocationY <= 550)
    {
        LocationY += 17;
        
    }
//Move = false;
//&& Move == true
}
else if (keyCode == e.VK_LEFT )
{
    if(LocationX >= 0)
LocationX -= 17;
//Move = false;
//&& Move == true
}
else if  (keyCode == e.VK_RIGHT )
{
    if(LocationX <= 1145)
LocationX += 17;
//&& Move == true
//Move = false;
}
}
public void keyTyped(KeyEvent e){
}
public void keyReleased(KeyEvent e){
Move = true;
}

public void loseLife()                          //when character is killed, run this function
{
	if (canLoseLives)
	{
	lives--;
    canLoseLives = false;
    LocationX = 0;
    LocationY = 0;
    Tshirt1 = true;
    Tshirt2 = true;
    CamColor = Color.blue;
	voce.SpeechInterface.synthesize("Ouch better luck next time!."+"now you have"+ lives +"lives");
	}
    

}

}