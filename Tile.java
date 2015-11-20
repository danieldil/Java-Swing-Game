// Border Run tiles Object

import java.awt.Color;
import java.awt.Rectangle;

public class Tile {
public int x;
public int y;
public final int WIDTH = 50;
public final int HEIGHT = 50;
public Color color;
public Rectangle R;
boolean Sight= true;
public Tile(int x, int y)
{
this.x = x;
this.y = y;
R = new Rectangle(x,y,this.WIDTH,this.HEIGHT);
}
}