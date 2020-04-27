import javax.swing.JFrame;

public class LeagueInvaders {
	GamePanel gameGame;
	JFrame gameFrame;
	static final int WIDTH= 500;
	static final int HEIGHT=800;
public static void main(String[] args) {
	LeagueInvaders invade = new LeagueInvaders();
	invade.setup();
}

 public LeagueInvaders(){
	gameFrame = new JFrame();
	gameGame = new GamePanel();
	 
 }
 
 void setup() {
	 gameFrame.add(gameGame);
	 gameFrame.addKeyListener(gameGame);
	 gameFrame.setSize(WIDTH, HEIGHT);
	 gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 gameFrame.setVisible(true);
 }
}
