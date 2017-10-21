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
	private int[] nextPos;
	private int[] currentPos;
	private boolean destructed;
	private int life;
	private int attack;
	private int direction; // haut=1 -> sens horaire +1
	private Shape shape;
	private double speed;
	private Image sprite;
	private Level map;
	private int cx;
	private int cy;
	
	public Enemy(int[] pos) {
		this.x= 32*pos[1];
		this.y= 720-32*pos[0];
		this.currentPos=pos;
		this.nextPos=pos;
		this.speed = 0.5;
		this.destructed=false;
		this.life=10;
		this.attack=1;
		this.direction=0;
		this.sprite=sprite;
		this.cx=x;
		this.cy=y;
		
		World.enemies.add(this);
		
		shape=new Rectangle((float)(x),(float)(y),(float) 32, (float) 32);
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite, (float)x, (float)y);
	}
	

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if ( this.x >= this.cx+32 && this.direction==2 ) {
			this.x = this.cx+32;
			calcNextPos();
		}
		else if ( this.x <= this.cx-32 && this.direction==4 ) {
			this.x = this.cx-32;
			calcNextPos();
		}
		else if ( this.y >= this.cy+32 && this.direction==3 ) {
			this.y = this.cy+32;
			calcNextPos();
		}
		else if ( this.y <= this.cy-32 && this.direction==1 ) {
			this.y = this.cy-32;
			calcNextPos();
		}	
			
		move(delta);
	}

	public void calcNextPos() {
		
		this.cx = this.x;
		this.cy = this.y;
		
		if ( map.getCase(this.nextPos[0]+1,this.nextPos[1])==0 && (this.nextPos[0]+1 != this.currentPos[0] || nextPos[1] != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[0] += 1;
			this.direction=1;
		}
		else if ( map.getCase(this.nextPos[0]-1,this.nextPos[1])==0 && (this.nextPos[0]-1 != this.currentPos[0] || nextPos[1] != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[0] -= 1;
			this.direction=3;
		}
		else if ( map.getCase(this.nextPos[0],this.nextPos[1]+1)==0 && (this.nextPos[0] != this.currentPos[0] || nextPos[1]+1 != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[1] += 1;
			this.direction=2;
		}
		else if ( map.getCase(this.nextPos[0],this.nextPos[1]-1)==0 && (this.nextPos[0] != this.currentPos[0] || nextPos[1]-1 != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[1] -= 1;
			this.direction=4;
		}
		if ( map.getCase(nextPos[0],nextPos[1]) == 3) {
			// giveDamage(attack);
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
		}
	}
	

	public void takeDamage(int damage) {
		this.life -= damage;
		if (this.life<=0) {
			this.destructed = true;
		}
	}
	
	public boolean isDestructed() {
		return this.destructed;
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
