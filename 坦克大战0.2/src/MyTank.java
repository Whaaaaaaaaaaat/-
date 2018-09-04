import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MyTank extends Arm {
	boolean isFire=false;
	public MyTank(int x,int y) {
		super(x,y,Camp.RED );
		color=Color.RED;
		direction=Direction.U;
	}
	
	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
		graphics.setColor(Color.WHITE);
		int x1=x+width/2,y1=y+height/2;
		Direction d;
		if(direction==Direction.STOP)
			d=oldDirection;
		else {
			d=direction;
		}
		switch (d) {
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
	
	@Override
	public void move() {
		super.move();
		if(x>=770||x<=0||y>=570||y<=0||isPeng()) {
			x=stepx;
			y=stepy;
		}
		stepx=x;
		stepy=y;
	}
	
	public void KeyMonitor(KeyEvent e) {
		switch(e.getKeyChar()) {
		case '8':
			oldDirection=direction;
			direction=Direction.U;
			break;
		case '9':
			oldDirection=direction;
			direction=Direction.RU;
			break;
		case '6':
			oldDirection=direction;
			direction=Direction.R;
			break;
		case '3':
			oldDirection=direction;
			direction=Direction.RD;
			break;
		case '2':
			oldDirection=direction;
			direction=Direction.D;
			break;
		case '1':
			oldDirection=direction;
			direction=Direction.LD;
			break;
		case '4':
			oldDirection=direction;
			direction=Direction.L;
			break;
		case '7':
			oldDirection=direction;
			direction=Direction.LU;
			break;
		case '5':
			if(direction!=Direction.STOP)
			{
				oldDirection=direction;
				direction=Direction.STOP;
			}else {
				direction=oldDirection;
			}
			
				break;
		case ' ':
			isFire=true;
			break;
		case '0':
			if (camp==Camp.RED) {
				camp = Camp.INVINCIBLE;
				color = Color.YELLOW;
			}else {
				camp=Camp.RED;
				color=Color.RED;
			}
			break;
/*		case 's':
			speed/=2;
			break;	*/
		default:
			break;
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
