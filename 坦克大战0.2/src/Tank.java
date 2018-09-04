import java.awt.Color;
import java.awt.Graphics;

public class Tank extends Arm{
	int stepCount=0,maxStep=30,count=33,step=0;
	boolean isFire=false;
	public Tank(int x,int y) {
			super(x,y,Camp.INVINCIBLE);
			color=Color.YELLOW;
	}
	
	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
		graphics.setColor(Color.WHITE);
		int x1=x+width/2,y1=y+height/2;
		switch (direction) {
		case U:
			graphics.drawLine(x1, y1, x1, y1-height/2);
			break;
		case RU:
			graphics.drawLine(x1, y1, x+width, y);
			break;
		case R:
			graphics.drawLine(x1, y1, x+width, y1);
			break;
		case RD:
			graphics.drawLine(x1, y1, x+width, y+height);
			break;
		case D:
			graphics.drawLine(x1, y1, x1, y+height);
			break;
		case LD:
			graphics.drawLine(x1, y1, x, y+height);
			break;
		case L:
			graphics.drawLine(x1, y1, x, y1);
			break;
		case LU:
			graphics.drawLine(x1, y1, x, y);
			break;
		case STOP:
		default:
			break;
		}
	}
	
	public void move() {
		super.move();
		reDirectoin();
		stepx=x;
		stepy=y;
		step++;
		
		if(step>=count) {
			step=0;
			isFire=true;
			count=random.nextInt(50)+10;
		}
	}
	private void reDirectoin() {
		stepCount++;
		boolean b=isPeng();
		if(x>=770||x<=0||y>=570||y<=0||stepCount>=maxStep||b) {
			if(camp==Camp.INVINCIBLE) {
				if(stepCount>=maxStep) {
					camp=Camp.BLUE;
					color=new Color(171,175,178);
				}else {
					return;
				}
			}else {
				x=stepx;
				y=stepy;
				stepCount=0;
				maxStep=random.nextInt(50)+5;
				direction=directionValues[random.nextInt(8)];
			}
			
		}
	}
	
	public boolean isFire() {
		if(isFire) {
			isFire=false;
			return true;
		}
		return false;
	}
	

}
