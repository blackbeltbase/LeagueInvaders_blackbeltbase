import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
Font titleFont;
Font otherMessages;
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Timer frameDraw;
    Rocketship rocket;
    int rocketX;
    int rocketY;
    int widthAKAHeight;
    ObjectManager manager;
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    Timer alienSpawn;
    void startGame() {
    	 alienSpawn = new Timer(1000 , manager);
    	    alienSpawn.start();
    }
    public GamePanel() {
    	   titleFont = new Font("Arial", Font.PLAIN, 48);
     	  otherMessages = new Font("Arial", Font.PLAIN, 12);
     	  rocketX = 250;
     	  rocketY = 700;
     	  widthAKAHeight = 50;
     	  rocket = new Rocketship(rocketX,rocketY,widthAKAHeight,widthAKAHeight);
      	manager = new ObjectManager(rocket);
      	if (needImage) {
      	    loadImage ("space.png");
      	}

     	    frameDraw = new Timer(1000/60,this);
     	    frameDraw.start();
    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
		
	}
	 void updateMenuState() {
		 
	 }
	 
	void updateGameState() {  
		manager.update();
		if(rocket.isActive == false) {
			currentState = END;
		}
	}
	
	void updateEndState()  { 
		
	}
	
	 void drawMenuState(Graphics g) { 
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setFont(titleFont);
		 g.setColor(Color.YELLOW);
		 g.drawString("lEaGuE_iNvAdErS", 40, 100);
		 g.setFont(otherMessages);
		 g.drawString("Press ENTER to start", 40, 300);
		 g.drawString("Press SPACE for shooting", 40, 350);
		 g.drawString("ARROW KEYS for moving", 40, 400);
	 }
	 
	void  drawGameState(Graphics g) { 
		
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		 g.setColor(Color.BLUE);
		 manager.draw(g);
	}
	
	void drawEndState(Graphics g)  {  
		 g.setColor(Color.RED);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setFont(titleFont);
		 g.setColor(Color.BLACK);
		 g.drawString("GAME OVER", 40, 100);
		 g.drawString("BUCKAROO", 40, 150);
		 g.setFont(otherMessages);
		 g.drawString("You killed "+manager.getScore()+ " enemies (f in the chat)", 40, 300);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("action");
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else if(currentState== MENU){
		        currentState = GAME;
		    }
		    else if(currentState == GAME) {
		    	currentState  =END;
		    }
		    
		    if(currentState ==GAME) {
		    	startGame();
		    }
		    
		    if(currentState ==END) {
		    	alienSpawn.stop();
		    	rocket = new Rocketship(rocketX, rocketY, widthAKAHeight,widthAKAHeight);
		    	manager = new ObjectManager(rocket);
		    }
		   
		}   
	
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("UP");
		    if(rocket.y>=10) {
		    rocket.up();}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("DOWN");
		    if(rocket.y<LeagueInvaders.HEIGHT-100) {
		    rocket.down();}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		    if(rocket.x>=10) {
		    rocket.left();}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		    if(rocket.x<LeagueInvaders.WIDTH-70) {
		    rocket.right();}

		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME) {
			manager.addProjectile(rocket.getProjectile());
			System.out.println("shot");
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
