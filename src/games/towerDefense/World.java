package games.towerDefense;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class World extends BasicGameState {

	private int ID;
	private int state;

	public ArrayList<Enemy> enemies, tempEnemies;
	public ArrayList<Projectile> projectiles, tempProjectiles;
	public ArrayList<Tower> towers, tempTowers;
	private Player player;

	//A VIRER
	private Level l;
	private Tower t;
	private ChooseTower c;


	int ligne, colonne;
	private int incomeDelay;
	private Enemy e;
	private Projectile p;

	int ab;
	int or;
	Input input;
	boolean atarashi;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		/* Méthode exécutée environ 60 fois par seconde */
		l.render(container,game,g);
		c.render(container, game, g);
		player.render(container, game, g);
		for(Tower t : towers){
			t.render(container, game, g);
		}
		for(Enemy e : enemies){
			e.render(container,game,g);
		}
		for(Projectile p : projectiles){
			p.render(container,game,g);
		}
		g.drawString("Vagues : "+l.getnVague(), 1150, 656);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		l.update(container, game, delta);
		player.update(container, game, delta);
		tempTowers.clear();
		tempTowers.addAll(towers);
		for(Tower t: tempTowers){
			t.update(container, game, delta);
		}

		tempEnemies.clear();
		tempEnemies.addAll(enemies);
		for(Enemy e : tempEnemies){
			e.update(container, game, delta);
		}

		tempProjectiles.clear();
		tempProjectiles.addAll(projectiles);
		for(Projectile p : tempProjectiles){
			p.update(container, game, delta);
		}


		if(input.isMousePressed(0)){
			changeMouse();
			atarashi = c.update(ab, or);

			if(ab > 0 && ab < 1280 && or > 0 && or < 616 && atarashi == true){
				getTile(ab, or);

				if(l.getCase(ligne, colonne) == 1){
					Tower newtour = new Tower(this,32*colonne, 32*ligne, c.choose);
					if(!(newtour.existeDeja(towers))){
						if(player.getGold() - c.choose * 50 >= 0){
							towers.add(newtour);
							player.earnGold(-(c.choose*50));
						}
					}
				}
			}
		}

		incomeDelay -= delta;
		if(incomeDelay <=0){
			incomeDelay = 1000;
			player.earnGold(5);
		}

		if(player.getLives() == 0) {
			((TowerEnd) game.getState(4 /* TowerEnd */)).changeWave(l.getnVague());
			game.enterState(4 /* TowerEnd */);
		}

	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		towers = new ArrayList<Tower>();
		tempTowers = new ArrayList<Tower>();
		tempEnemies = new ArrayList<Enemy>();
		tempProjectiles = new ArrayList<Projectile>();
		player = new Player(20,200);
		l = new Level(this, player);
		c = new ChooseTower(player);
		ab = 0;
		or = 0;
		input = container.getInput();
		atarashi = false;
		incomeDelay = 1000;
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public void changeMouse(){
		ab = input.getMouseX();
		or = input.getMouseY();
	}

	public void getTile (int X, int Y) {
		// Indique les coordonnée ligne colonne dans un tableau de 2 éléments
		ligne = Y / 32;
		colonne = X /32;

	}
}
