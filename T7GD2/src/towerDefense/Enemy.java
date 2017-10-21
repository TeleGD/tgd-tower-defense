package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Enemy {
	
	private int x;
	private int y;
	private int nextPosX;
	private int nextPosY;
	private int currentPosX;
	private int currentPosY;
	private int life;
	private int attack;
	private int direction; // haut=1 -> sens horaire +1
	private Shape shape;
	private double speed;
	private Image sprite;
	private Level map;

	
	public Enemy(int posX, int posY,Level map) {
		this.map=map;
		this.x= (int) map.getX(posY);
		this.y= (int) map.getY(posX);
		this.currentPosX=posX;
		this.currentPosY=posY;
		this.nextPosX=posX;
		this.nextPosY=posY;
		this.speed = 0.15;
		this.life=1;
		this.attack=1;
		this.direction=0;
		try {
			sprite = new Image("images/TowerDefense/enemy1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		World.enemies.add(this);
		
		shape=new Rectangle((float)(x),(float)(y),(float) 32, (float) 32);
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite, (float)x, (float)y);
	}
	

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if ( this.x >= (int) map.getX(nextPosY) && this.direction==2 ) {
			this.x = (int) map.getX(nextPosY);
			calcNextPos(); 
		}
		else if ( this.x <= (int) map.getX(nextPosY) && this.direction==4 ) {
			this.x =  (int) map.getX(nextPosY);
			calcNextPos();
		}
		else if ( this.y >= (int) map.getY(nextPosX) && this.direction==3 ) {
			this.y = (int) map.getY(nextPosX);
			calcNextPos();
		}
		else if ( this.y <= (int) map.getY(nextPosX) && this.direction==1 ) {
			this.y = (int) map.getY(nextPosX);
			calcNextPos();
		}	
		move(delta);
	}

	
	public void calcNextPos() {
		
		if ( (map.getCase(this.nextPosX-1,this.nextPosY)==0 || map.getCase(this.nextPosX-1,this.nextPosY)==3 ) && (this.nextPosX-1 != this.currentPosX || this.nextPosY != this.currentPosY) ) {
			this.currentPosX = this.nextPosX; this.currentPosY = this.nextPosY;
			this.nextPosX -= 1;
			this.direction=1;
		}
		else if ( (map.getCase(this.nextPosX+1,this.nextPosY)==0 || map.getCase(this.nextPosX+1,this.nextPosY)==3 ) && (this.nextPosX+1 != this.currentPosX || this.nextPosY != this.currentPosY) ) {
			this.currentPosX = this.nextPosX; this.currentPosY = this.nextPosY;
			this.nextPosX += 1;
			this.direction=3;
		}
		else if ( (map.getCase(this.nextPosX,this.nextPosY+1)==0 || map.getCase(this.nextPosX,this.nextPosY+1)==3 ) && (this.nextPosX != this.currentPosX || this.nextPosY+1 != this.currentPosY) ) {
			this.currentPosX = this.nextPosX; this.currentPosY = this.nextPosY;
			this.nextPosY += 1;
			this.direction=2;
		}
		else if ( (map.getCase(this.nextPosX,this.nextPosY-1)==0 || map.getCase(this.nextPosX,this.nextPosY-1)==3 ) && (this.nextPosX != this.currentPosX || this.nextPosY-1 != this.currentPosY) ) {
			this.currentPosX = this.nextPosX; this.currentPosY = this.nextPosY;
			this.nextPosY -= 1;
			this.direction=4;
		}
		else {
			this.currentPosX = this.nextPosX; this.currentPosY = this.nextPosY;
		}
		if ( map.getCase(this.currentPosX,this.currentPosY) == 3) {
			// giveDamage(attack);
			World.enemies.remove(this);
		}
	}

	
	public void move(int delta){
		switch (this.direction) {
			case 1 :
				this.y-=speed*delta;
				shape.setY((float) this.y);
				break;
			case 2 :
				this.x+=speed*delta;
				shape.setX((float) this.x);
				break;
			case 3 :
				this.y+=speed*delta;
				shape.setY((float) this.y);
				break;
			case 4 :
				this.x-=speed*delta;
				shape.setX((float) this.x);
				break;
			default :
				calcNextPos();
		}
	}
	

	public void takeDamage(int damage) {
		this.life -= damage;
		if (this.life<=0) {
			World.enemies.remove(this);
		}
	}

	public Shape getShape() {
		return shape;
	}
	
	public double getX() {
		return this.x;
	}


	public double getY() {
		return this.y;
	}
	
}
