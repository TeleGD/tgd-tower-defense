package games.towerDefense;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Tower {

	private World world;
	private double x,y;
	private double damage;
	private Enemy enemy;
	private double range;
	private Image sprite;
	private double attackSpeed;
	private double timer;
	private int type;
	private int level;
	private float radius;
	private double factor;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getType() {
		return type;
	}

	public Tower(World world, double x,double y,double damage,double attackSpeed,double range,int type) {
		this.world = world;
		this.x=x;
		this.y=y;
		this.damage=damage;
		this.attackSpeed=attackSpeed;
		timer = 0;
		this.type = type;
		this.range=range;
		this.level=1;
		sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level1.png");
	}
	public Tower(World world, double x,double y,int type) {
		this.world = world;
		this.x=x;
		this.y=y;
		this.level=1;
		this.type = type;
		switch (type) {
			case 1:
				this.damage=1;
				this.range=100;
				this.attackSpeed=1000;
				this.factor=1;
				break;

			case 2:
				this.damage=5;
				this.range=200;
				this.attackSpeed=2500;
				this.factor=2;
				break;

			case 3:
				this.damage=3;
				this.range=100;
				this.attackSpeed=2000;
				this.radius=64;
				this.factor=1;
				break;

			case 4:
				this.damage=1;             //damage = damageboost (type4)
				this.range=112;
				this.attackSpeed=1000;
				this.factor=1;
				break;

			case 5:
				this.damage=1;             //damage = slow (type5)
				this.range=150;
				this.attackSpeed=1000;
				this.factor=1;
				break;

			default:
				this.damage=1;
				this.range=100;
				this.attackSpeed=1000;
				this.factor=1;
				break;

		}
		timer = 0;
		sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level1.png");

	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		arg2.drawImage(sprite, (float)x, (float)y);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		timer-=delta;
		boolean c = ChooseEnemy();
		if (c && timer<=0) {
			this.world.projectiles.add(new Projectile(this.world,x,y,enemy,damage,type));
			timer=attackSpeed;                 // exemple si cadence=0,5 sec, attackSpeed=500 (delta=nb de ms entre 2 frames)
		}
	}

	private boolean ChooseEnemy() {// renvoie vrai si un ennemi est à portée
		for (Enemy e : this.world.tempEnemies) {        // cherche dans la liste des ennemis triée par ordre d'appartition
			if (Math.sqrt(Math.pow(this.x-e.getX(),2)+Math.pow(e.getY()-this.y,2))<this.range) {
				this.enemy = e;
				return true;
			}
		}
		return false;
	}

	public void upgrade(int n) {               // n correspond au niveau
		if (n==2) {
			this.level+=1;
			this.range+=25;
			this.damage+=factor;
			this.radius+=16;
			sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level1.png");
		}
		if (n==3) {
			this.level+=1;
			this.range+=25;
			this.damage+=factor;
			this.attackSpeed-=300;
			sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level"+level+".png");
		}
		if (n==4) {
			this.level+=1;
			this.range+=25;
			this.damage+=factor;
			this.radius+=16;
			sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level"+level+".png");
		}
		if (n==5) {
			this.level+=1;
			this.range+=25;
			this.damage+=factor;
			this.attackSpeed-=300;
			sprite = AppLoader.loadPicture("/images/towerDefense/TowerType"+type+"Level"+level+".png");
		}
	}

	public boolean existeDeja(ArrayList<Tower> tour){
		for(int i = 0; i < tour.size(); i++){
			if( this.equals(tour.get(i))){
				return true;
			}
		}
		return false;
	}
}
