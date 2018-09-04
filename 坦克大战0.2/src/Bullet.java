import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
public class Bullet extends Arm{
	List<Arm> tanks=new ArrayList<Arm>();
	static int bulletWidth=10,bulletHeight=10;
	public Bullet(Arm arm) {
		super(arm.x+arm.width/2-bulletWidth/2,arm.y+arm.height/2-bulletHeight/2,arm.camp);
		width=bulletWidth;
		height=bulletHeight;
		
		if (arm.direction!=Direction.STOP) {
			this.direction = arm.direction;
		}else {
			this.direction=arm.oldDirection;
		}
		this.speed=10;
		switch (camp) {
		case RED:
			color=Color.RED;
			break;
		case BLUE:
			color=Color.BLUE;
			break;
		case INVINCIBLE:
			color=Color.YELLOW;
			break;
		default:
			break;
		}
		
	}	
	@Override
	public void move() {
		super.move();
		
	}
	
	@Override
	public void setRectangle(List<Arm> tanks) {
		this.tanks=tanks;
	}
	
	public Arm isHit() {
		if (tanks.size()>0) {
			for (int i = 0; i < tanks.size(); i++) {
				Arm tank=tanks.get(i);
				if(camp!=tank.camp&&tank.camp!=Camp.INVINCIBLE&&selfRectangle.intersects(new Rectangle(tank.x, tank.y, tank.width, tank.height))) {
					return tank;
				}
			} 
		}
		return null;
	}
	public boolean isOut() {
		if(x<=0||x>=770||y<=0||y>=570) {
			return true;
		}
		return false;
	}
}
