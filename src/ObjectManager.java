import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship rocket;
ArrayList<Projectile> laser = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
public ObjectManager(Rocketship rocket) {
	this.rocket = rocket;
}
void addProjectile(Projectile laser) {
	this.laser.add(laser);
}
void addAlien() {
	Random random = new Random();
	aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
}
void update() {
	for(Alien s:aliens) {
		s.update();
		if(s.y>LeagueInvaders.HEIGHT) {
			s.isActive = false;
		}
	}
	for(Projectile s:laser) {
		s.update();
		if(s.y<0) {
			s.isActive = false;
		}
	}
}

void draw(Graphics g) {
	rocket.draw(g);
	for(Alien s:aliens) {
	s.draw(g);
	}
}

void purgeObjects() {
	for( int i = 0; i<aliens.size();i++) {
		if(aliens.get(i).isActive = false) {
			aliens.remove(i);
		}
	}
	
	for( int i = 0; i<laser.size();i++) {
		if(laser.get(i).isActive = false) {
			laser.remove(i);
		}
	}
}

void checkCollision() {
//	for( int i = 0; i<aliens.size();i++) {
//		if(aliens.get(i).collisionBox.intersects(laser.collisionBox))
//		for( int j = 0; j<laser.size();j++) {
//			
//		}
//	}
	//continue Collision Management
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
addAlien();
}
}
