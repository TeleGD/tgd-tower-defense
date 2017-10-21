package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Projectile {

	private double x,y;
	private double damage;
	private Enemy target;
	private Shape collisionBox;

	public Projectile (double x, double y, Enemy target, double damage){
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.target = target;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.orange);
		g.fillRect((float)x,(float) y,(float) 8.0,(float) 8.0);
	}
	
	public void checkForCollision(){
		/*for(Enemy e : World.enemies){
			
		}*/
	}
	
}
