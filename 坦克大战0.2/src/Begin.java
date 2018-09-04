import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author 雨
 *
 */


public class Begin extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH=810;
	public static final int HEIGHT=641;
	
	List<Arm> tanks=new ArrayList<Arm>();
	List<Bullet> bullets=new ArrayList<Bullet>();
	MyTank myTank=new MyTank(403,570);
	Thread moveThread;
	JPanel battleField=new JPanel() {
		private static final long serialVersionUID = 1L;
		@Override
		public void paint(Graphics graphics) {
			super.paint(graphics);
			for (int i = 0; i < tanks.size(); i++) {
				Arm arm=tanks.get(i);
				if(arm.isFire()) {
					bullets.add(new Bullet(arm));
				}
				arm.draw(graphics);
			}
			if(bullets.size()!=0) {
				for (int j = 0; j < bullets.size(); j++) {
					Bullet bullet = bullets.get(j);
					if(bullet.isOut()) {
						bullets.remove(bullet);
					}
					else {
						bullet.draw(graphics);
						Arm tank=bullet.isHit();
						if(tank!=null) {
							bullets.remove(bullet);
							tanks.remove(tank);
						}
					}
				}
			}
			
			setRectangle(tanks);
			graphics.drawString("bullets:"+bullets.size(), 0, 10);
			graphics.drawString("bricks:"+(tanks.size()-10), 0, 30);
		}
	};
	//构造方法
	public Begin ()
	{
		tanks.add(myTank);
		for (int x = 100; x <WIDTH-100 ; x+=Brick.SPAN) {
			for (int y = 300; y < HEIGHT-300; y+=Brick.SPAN) {
				tanks.add(new Brick(x, y,Camp.BRICK));
			}
		}
		for (int y =0;y<Brick.SPAN*5 ; y+=Brick.SPAN) {
			for (int x = 400-Brick.SPAN*3;x<430+Brick.SPAN*3; x+=Brick.SPAN) {
				if((y < Brick.SPAN*3&&(x<400-Brick.SPAN||x>=430+Brick.SPAN))||(y>=Brick.SPAN*3&&(x<400||x>=433)))
				{
					tanks.add(new Brick(x, y, Camp.BLUE));
				}
				if(y < Brick.SPAN*3&&x>=400-Brick.SPAN) {
					continue;
				}
				if(y>=40&&x>=400) {
					continue;
				}
			}
		}
		
		setSize(WIDTH,HEIGHT);
		getContentPane().setSize(new Dimension(100, 100));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		battleField.setBackground(Color.black);
		battleField.setSize(500,500);
		add(battleField,"Center");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				try {
					moveThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		battleField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				myTank.KeyMonitor(e);
			}
		});		
		setVisible(true);
		battleField.setFocusable(true);
		battleField.requestFocus();
		moveThread=new Thread(new moveThread());
		moveThread.start();
	}
	
	public void setRectangle(List<Arm> tanks) {
		for(int i=0;i<this.tanks.size();i++) {
			Arm a=this.tanks.get(i);
			a.rectangles.clear();
			a.setRectangle(tanks);
		}
		if (bullets.size()!=0) {
			for (int j = 0; j < this.bullets.size(); j++) {
				Arm b = this.bullets.get(j);
				b.rectangles.clear();
				b.setRectangle(tanks);
			} 
		}
	}

	
	
	
	private class moveThread implements Runnable{
		int tankCount=0;
		int t=0;
		@Override
		public void run() {
			
			while (true) {
				
				while(t%30==0) {
					Arm tank=new Tank(403, 0);
					tanks.add(tank);
					t=1;
					tankCount++;
				}
				
				repaint();
				if (tankCount<10) {
					t++;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			
		}
		
	}	
	public static void main(String[] args) {
		new Begin();
	}
}
