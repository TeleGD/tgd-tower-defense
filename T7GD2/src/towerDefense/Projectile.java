package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Projectile {

	private double x,y,speedX,speedY;
	private float width, height;
	private double damage;
	private int dirX, dirY;
	private Enemy target;
	private Shape collisionBox;
	private boolean alreadyDead;
	private Image sprite;
	private int type;

	public Projectile (double x, double y, Enemy target, double damage){
		this.x = x;
		this.y = y;
		width = 9;
		height = 16;
		this.damage = damage;
		this.target = target;

		//A ADAPTER A LA VITESSE DES ENNEMIS
		this.speedX = 0.6;
		this.speedY = 0.6;
		alreadyDead = false;
		this.collisionBox = new Rectangle((float)x,(float)y,(float)8,(float)8);
		try {
			sprite = new Image("images/towerDefense/Arrow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceTarget();
	}
	
	public Projectile(double x, double y, Enemy target, double damage, int type){
		/* Types :
		 * 0 : normal
		 * 1 : AOE
		 * 2 : slowing
		 */
		this.x = x;
		this.y = y;
		this.damage = damage;
		alreadyDead = false;
		this.type = type;
		
		switch(type){
		default:
			width = 9;
			height = 16;	
			this.target = target;
			//A ADAPTER A LA VITESSE DES ENNEMIS
			this.speedX = 0.6;
			this.speedY = 0.6;
			try {
				sprite = new Image("images/towerDefense/Arrow.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			width = 16;
			height = 16;	
			this.target = target;
			//A ADAPTER A LA VITESSE DES ENNEMIS
			this.speedX = 0.6;
			this.speedY = 0.6;
			try {
				sprite = new Image("images/towerDefense/AOE.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			width = 9;
			height = 16;	
			this.target = target;
			//A ADAPTER A LA VITESSE DES ENNEMIS
			this.speedX = 0.6;
			this.speedY = 0.6;
			try {
				sprite = new Image("images/towerDefense/Arrow.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		this.collisionBox = new Rectangle((float)x,(float)y,(float)width,(float)height);
		faceTarget();
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta,2);
		if(alreadyDead) die();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float) x,(float) y);
	}
	
	public void die(){
		World.projectiles.remove(this);
	}
	
	public void checkForCollision(){
		for(Enemy e : World.enemies){
			if(collisionBox.intersects(e.getShape())){
				switch(type){
				default:
					e.takeDamage((int)damage);
					break;
				case 2:
					//AOE
					break;
				case 3:
					//Slow effect
					break;
				}
				alreadyDead = true;
				return;
			}
		}
	}
	
	public void faceTarget(){
		double tempX = target.getX();
		if(x < tempX){
			dirX = 1;
		}else if(x <= tempX+32){
			dirX = 0;
		}else{
			dirX = -1;
		}
		double tempY = target.getY();
		if(y < tempY){
			dirY = 1;
		}else if(y <= tempY+32){
			dirY = 0;
		}else{
			dirY = -1;
		}
		
		//Sprite orientation :
		int a = 0;
		switch(dirX){
		case -1:
			switch(dirY){
			case -1:
				sprite.setRotation(a+135);
				break;
			case 0:
				sprite.setRotation(a+90);
				break;
			case 1:
				sprite.setRotation(a+45);
				break;
			}
			break;
		case 0:
			switch(dirY){
			case -1:
				sprite.setRotation(a-180);
				break;
			case 0:
				//This should be encountered only in case of collision
				break;
			case 1:
				sprite.setRotation(a+0);
				break;
			}
			break;
		case 1:
			switch(dirY){
			case -1:
				sprite.setRotation(a-135);
				break;
			case 0:
				sprite.setRotation(a-90);
				break;
			case 1:
				sprite.setRotation(a-45);
				break;
			}
			break;
		}
		
	}
	
	public void move(int dt){
	//Equivalent to move(dt,2)	
		if(target != null){
			faceTarget();
		}
		x += dirX*speedX*dt/2;
		y += dirY*speedY*dt/2;
		collisionBox.setLocation((float)x, (float)y);
		if(target != null){
			faceTarget();
		}
		x += dirX*speedX*dt/2;
		y += dirY*speedY*dt/2;
		collisionBox.setLocation((float)x, (float)y);
		if(x > Main.longueur || y > Main.hauteur || x < 0 || y < 0){
			alreadyDead = true;
		}
	}
	
	public void move(int dt, int subdivision){
		if(subdivision < 0){
			subdivision = subdivision*-1;
		}else if (subdivision == 0){
			subdivision = 2;
		}
		for(int i = 0; i < subdivision; i++){
			if(target != null){
				faceTarget();
			}
			x += dirX*speedX*dt/subdivision;
			y += dirY*speedY*dt/subdivision;
			collisionBox.setLocation((float)x, (float)y);
		}
		if(x > Main.longueur || y > Main.hauteur || x < 0 || y < 0){
			alreadyDead = true;
		}
	}
	
}
