import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
abstract class Arm {
	public int x,y,width=30,height=30,stepx,stepy;
	public Color color;
	int speed=3;
	public Random random=new Random();
	Direction direction=Direction.D;
	Direction[] directionValues=Direction.values();
	List<Rectangle> rectangles=new ArrayList<Rectangle>();
	Rectangle selfRectangle;
	public Camp camp=null;
	Direction oldDirection;
	public Arm() {
		
	}
	
	public Arm(int x, int y,Camp camp) {
		this.x = x;
		this.y = y;
		this.camp=camp;
		stepx=x;
		stepy=y;
	}
	
	public void draw(Graphics graphics )
	{
		graphics.setColor(color);
		graphics.fillOval(x, y, width, height);
		move();
		
	}
	
	public void move() {
		switch (direction) {
		case U:
			y-=speed;
			break;
		case RU:
			x+=speed;
			y-=speed;
			break;
		case R:
			x+=speed;
			break;
		case RD:
			x+=speed;
			y+=speed;
			break;
		case D:
			y+=speed;
			break;
		case LD:
			x-=speed;
			y+=speed;
			break;
		case L:
			x-=speed;
			break;
		case LU:
			x-=speed;
			y-=speed;
			break;
		case STOP:
		default:
			break;
		}
		selfRectangle=new Rectangle(x, y, width, height);
	}
	
	public void setRectangle(List<Arm> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Arm arms=tanks.get(i);
			if(this!=arms)
			{
					rectangles.add(new Rectangle(arms.x,arms.y,arms.width, arms.height));	
			}
		}
	}	
	public boolean isPeng() {
		for(int i=0;i<rectangles.size();i++) {
			Rectangle r1=rectangles.get(i);
			if(selfRectangle.intersects(r1))
			return true;
		}
		return false;
		
	}
	public boolean isFire() {
		return false;
	}
}
