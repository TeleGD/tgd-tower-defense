package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tower {
	private double x,y;
	private double damage;
	private Enemy enemy;
	private double range;
	private Image sprite;
	private Image niveau1,niveau2,niveau3,niveau4,niveau5;
	private double attackSpeed;
	private double timer;
	
	public double getX() {
		return x;                             
	}
	
	public double getY() {
		return y;
	}
	
	public Tower(double x,double y,double damage,double attackSpeed,double range) {
		this.x=x;
		this.y=y;
		this.damage=damage;
		this.sprite=niveau1;
			
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(sprite, (float)x, (float)y);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		timer-=delta;
		if (ChooseEnemy() && this.timer==0) {
			World.projectiles.add(new Projectile(x,y,enemy,damage));
			timer=attackSpeed;                 // exemple si cadence=0,5 sec, attackSpeed=500 (delta=nb de ms entre 2 frames)
		}
	}
	
	private boolean ChooseEnemy() {            // renvoie vrai si un ennemi est à portée
		for (Enemy e : World.enemies) {        // cherche dans la liste des ennemis triée par ordre d'appartition
			if (Math.sqrt(Math.pow(this.x-this.y,2)+Math.pow(Enemy.getX()-Enemy.getY(),2))<this.range) {
				this.enemy = e;
				return true;
			}
		}
	}
	
	public void upgrade(int n) {
		if (n==2) {
			this.damage+=1;
			this.sprite=niveau2;
		}
		if (n==3) {
			this.damage+=1;
			this.sprite=niveau3;
			this.attackSpeed+=1;
		}
		if (n==4) {
			this.damage+=1;
			this.sprite=niveau4;
		}
		if (n==4) {
			this.damage+=1;
			this.sprite=niveau5;
			this.attackSpeed+=1;
		}
	}
}
