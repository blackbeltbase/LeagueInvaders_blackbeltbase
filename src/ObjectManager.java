import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship rocket;
ArrayList<Projectile> laser = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
int score = 0;
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
int getScore() {
	return score;
}
void update() {

if(rocket.isActive) {
	rocket.update();
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
	}}
	checkCollision();
	purgeObjects();
}

void draw(Graphics g) {
	rocket.draw(g);
	for(Alien s:aliens) {
	s.draw(g);
	}
	for(Projectile s:laser) {
	s.draw(g);
	}
}

void purgeObjects() {
	for( int i = 0; i<aliens.size();i++) {
		if(aliens.get(i).isActive == false) {
			aliens.remove(i);
		}
	}
	
	for( int i = 0; i<laser.size();i++) {
		if(laser.get(i).isActive == false) {
			laser.remove(i);
		}
	}
}

void checkCollision() {
	for( int i = 0; i<aliens.size();i++) {
		for( int j = 0; j<laser.size();j++) {
			if(aliens.get(i).collisionBox.intersects(laser.get(j).collisionBox)) {
				aliens.get(i).isActive = false;
				System.out.println("alien destroyed");
				laser.get(j).isActive = false;
				score++;
			}
		}
	}
	
	for(int i = 0; i<aliens.size(); i++) {
		if(rocket.collisionBox.intersects(aliens.get(i).collisionBox)) {
			rocket.isActive = false;
	System.out.println("rocket destroyed");
		}
	}
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
addAlien();
}
}
