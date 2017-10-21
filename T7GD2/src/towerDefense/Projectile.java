package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Projectile {

	private double x,y,speedX,speedY;
	private double damage;
	private int dirX, dirY;
	private Enemy target;
	private Shape collisionBox;

	public Projectile (double x, double y, Enemy target, double damage){
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.target = target;
		this.speedX = 0;
		this.speedY = 0;
		this.collisionBox = new Rectangle((float)x,(float)y,(float)8,(float)8);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.orange);
		g.fillRect((float)x,(float) y,(float) 8.0,(float) 8.0);
	}
	
	public void die(){
		World.projectiles.remove(this);
	}
	
	public void checkForCollision(){
		for(Enemy e : World.enemies){
			if(collisionBox.intersects(e.getShape())){
				e.takeDamage((int)damage);
				die();
				return;
			}
		}
	}
	
	public void move(int dt){
		if(target != null){
			
		}else{
			x += speedX*dt;
			y += speedY*dt;
			if(x > Main.longueur || y > Main.hauteur || x < 0 || y < 0){
				die();
			}
		}
	}
	
}
