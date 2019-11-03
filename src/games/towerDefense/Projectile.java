package games.towerDefense;

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

	private double x,y,speedX,speedY, damage;
	private float width, height, radius;
	private int dirX, dirY, type, angle;
	private Enemy target;
	private Shape collisionBox;
	private boolean alreadyDead;
	private Image sprite;

	public Projectile (double x, double y, Enemy target, double damage){
		this.x = x;
		this.y = y;
		width = 9;
		height = 16;
		this.damage = damage;
		this.target = target;
		type = 1;
		radius = 0;
		//A ADAPTER A LA VITESSE DES ENNEMIS
		this.speedX = 0.6;
		this.speedY = 0.6;
		alreadyDead = false;
		this.collisionBox = new Rectangle((float)x,(float)y,width,height);
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
		 * 1 : Normal
		 * 2 : Powerful but slow
		 * 3 : AOE
		 */
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.target = target;
		alreadyDead = false;
		this.type = type;
		this.radius = 0;
		switch(type){
		default:
			width = 9;
			height = 16;
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
		case 3:
			width = 16;
			height = 16;
			//A ADAPTER A LA VITESSE DES ENNEMIS
			this.speedX = 0.6;
			this.speedY = 0.6;
			this.radius = 64;
			try {
				sprite = new Image("images/towerDefense/Bomb.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			width = 9;
			height = 16;
			//A ADAPTER A LA VITESSE DES ENNEMIS
			this.speedX = 0.6;
			this.speedY = 0.6;
			try {
				sprite = new Image("images/towerDefense/Bolt.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

		this.collisionBox = new Rectangle((float)x,(float)y,width,height);
		faceTarget();
	}

	public Projectile(double x, double y, Enemy target, double damage, float radius){
		this.x = x;
		this.y = y;
		this.damage = damage;
		alreadyDead = false;
		this.type = 2;
		this.radius = radius;
		width = 16;
		height = 16;
		this.target = target;
		//A ADAPTER A LA VITESSE DES ENNEMIS
		this.speedX = 0.6;
		this.speedY = 0.6;
		try {
			sprite = new Image("images/towerDefense/Bomb.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.collisionBox = new Rectangle((float)x,(float)y,width,height);
		faceTarget();
	}


	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta,2);
		if(alreadyDead) die();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float) x,(float) y);
		//g.setColor(Color.cyan);
		//g.fillRect(collisionBox.getX(), collisionBox.getY(), collisionBox.getWidth(), collisionBox.getHeight());
	}

	public void die(){
		World.projectiles.remove(this);
	}

	public float distanceToTarget(Enemy enn){
		float targetX = (float) target.getX();
		float targetY = (float) target.getY();
		float ennX = (float) enn.getX();
		float ennY = (float) enn.getY();
		return (float) Math.sqrt((ennX-targetX)*(ennX-targetX)+(ennY-targetY)*(ennY-targetY));
	}

	public void checkForCollision(){
		for(Enemy e : World.enemies){
			if(collisionBox.intersects(e.getShape())){
				switch(type){
				default:
					e.takeDamage((int)damage);
					break;
				case 3:
					//AOE
					for(Enemy enn:World.tempEnemies){
						if(distanceToTarget(enn)<=radius){
							enn.takeDamage((int) damage);
						}
					}
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
		int previousAngle = angle;
		int dirnum = 3*dirY + dirX;
		switch(dirnum){//WARNING : the angles are measured in anti-trigonometric order
		case -4://Up-left
			angle = 135;
			break;
		case -3://Up
			angle = 180;
			break;
		case -2://Up-right
			angle = -135;
			break;
		case -1://Left
			angle = 90;
			break;
		case 0://Collision case
			previousAngle = 0;
			angle = 0;
			break;
		case 1://Right
			angle = -90;
			break;
		case 2://Down-left
			angle = 45;
			break;
		case 3://Down
			angle = 0;
			break;
		case 4://Down-right
			angle = -45;
			break;
		}
		sprite.setRotation(angle);
		int deltaAngle = previousAngle - angle;
		collisionBox.transform(Transform.createRotateTransform(deltaAngle));
	}

	public void move(int dt){
	//Equivalent to move(dt,2)
		if(target != null){
			faceTarget();
		}else{
			alreadyDead = true;
		}
		x += dirX*speedX*dt/2;
		y += dirY*speedY*dt/2;
		collisionBox.setLocation((float)x, (float)y);
		if(target != null){
			faceTarget();
		}else{
			alreadyDead = true;
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
			}else{
				alreadyDead = true;
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
