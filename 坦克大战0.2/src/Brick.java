import java.awt.Color;
import java.awt.Graphics;
public class Brick extends Arm{
	public static int SPAN=13;
	public  Brick(int x,int y,Camp camp) {
		super(x,y,camp);
		this.width=10;
		this.height=10;
		switch (camp) {
		case BRICK:
			color=new Color(255,119,89);
			break;
		case BLUE:
			color=new Color(195, 195,195);
			break;
		case RED:
			color=new Color(255, 215, 205);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect(x, y, width, height);
	}
}
