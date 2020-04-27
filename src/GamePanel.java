import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    public GamePanel() {
    	
    	   titleFont = new Font("Arial", Font.PLAIN, 48);
     	  otherMessages = new Font("Arial", Font.PLAIN, 12);
     	  rocketX = 250;
     	  rocketY = 700;
     	  widthAKAHeight = 50;
     	  rocket = new Rocketship(rocketX,rocketY,widthAKAHeight,widthAKAHeight);
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
		 g.drawString("Press SPACE for instructions", 40, 350);
	 }
	 
	void  drawGameState(Graphics g) { 
		
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setColor(Color.BLUE);
		rocket.draw(g);
	}
	
	void drawEndState(Graphics g)  {  
		 g.setColor(Color.RED);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setFont(titleFont);
		 g.setColor(Color.BLACK);
		 g.drawString("GAME OVER", 40, 100);
		 g.drawString("BUCKAROO", 40, 150);
		 g.setFont(otherMessages);
		 g.drawString("You killed _ enimies (f in the chat)", 40, 300);
		 g.drawString("Press ENTER to restart", 40, 350);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("action");
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
		    } else {
		        currentState++;
		    }
		}   
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("UP");
		    if(rocket.y>=10) {
		    rocket.up();}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("DOWN");
		    if(rocket.y<LeagueInvaders.HEIGHT-10) {
		    rocket.down();}
		    //note:this does not work redo
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		    if(rocket.x>=10) {
		    rocket.left();}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		    if(rocket.x<LeagueInvaders.WIDTH-10) {
		    rocket.right();}
		    //note:this does not work redo
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
