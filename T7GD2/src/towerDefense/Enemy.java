package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
//		shape = new Shape();
//		shape.setX((float) this.x);
//		shape.setY((float) this.y);
//		shape.setCenterX((float) this.x+16);
//		shape.setCenterY((float) this.y+16);
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
//		g.setColor(Color.red);
//		g.fillOval((float)(x-rad),(float)(y-rad),(float)(2*rad),(float)(2*rad));
	}
	

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if ( ((float)(x) /32 ) == ((float)(x/32)) ) {
			calcNextPos();
		}
		move(delta);
	}

	public void calcNextPos() {
		if ( getCase(this.nextPos[0]+1,this.nextPos[1])==0 && (this.nextPos[0]+1 != this.currentPos[0] || nextPos[1] != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[0] += 1;
			this.direction=1;
		}
		else if ( getCase(this.nextPos[0]-1,this.nextPos[1])==0 && (this.nextPos[0]-1 != this.currentPos[0] || nextPos[1] != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[0] -= 1;
			this.direction=3;
		}
		else if ( getCase(this.nextPos[0],this.nextPos[1]+1)==0 && (this.nextPos[0] != this.currentPos[0] || nextPos[1]+1 != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[1] += 1;
			this.direction=2;
		}
		else if ( getCase(this.nextPos[0],this.nextPos[1]-1)==0 && (this.nextPos[0] != this.currentPos[0] || nextPos[1]-1 != currentPos[1]) ) {
			this.currentPos[0] = this.nextPos[0]; this.currentPos[1] = this.nextPos[1];
			this.nextPos[1] -= 1;
			this.direction=4;
		}
		if ( getCase(nextPos[0]==nextPos[1]) ) {
			// giveDamage(attack);
		}
	}

	
	public void move(int delta){
		switch (this.direction) {
			case 1 :
				this.y-=speed*delta;
				break;
			case 2 :
				this.x+=speed*delta;
				break;
			case 3 :
				this.y+=speed*delta;
				break;
			case 4 :
				this.x-=speed*delta;
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
