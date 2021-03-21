package games.towerDefense;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Level {

	private World world;
	Player pl;
	int[][] map;
	float width, height;
	int lenX, lenY, nbEnemies,nVague;
	private Image sprite0,sprite1,sprite2,base;
	int spawnX,spawnY;
	double timer;

	public Level(World world, Player p) {
		this.world = world;
		width = 32;
		height = 32;
		lenX = (int)(1280 / width);
		lenY = (int)(616 / height);
		nVague = 0;
		pl = p;
		sprite0 = AppLoader.loadPicture("/images/towerDefense/chemin.png");
		sprite1 = AppLoader.loadPicture("/images/towerDefense/mur.png");
		sprite2 = AppLoader.loadPicture("/images/towerDefense/spawn.png");
		base = AppLoader.loadPicture("/images/towerDefense/Base.png");

		genereMap();
		newVague();

	}

	public int[] genereLine(int element1, int element2, int debut, int fin) {
		//Genere une ligne avec element1 à partir de la case debut jusqu'à la case fin exclu, complète le reste avec element2
		int[] line = new int[lenX];
		for (int i = 0; i < lenX; i++)
		{
			line[i] = element2;
		}

		for (int i = debut; i < fin; i++)
		{
			line[i] = element1;
		}
		return line;
	}

	public void genereMap() {
		// Generer la carte du jeu
		map = new int[lenY][lenX];

		int [] line1 = new int[lenX];
		Arrays.fill(line1, 1);
		map[0] = line1; //Murs du haut

		for (int i=1; i < lenY - 4; i+=4)
		{
			map[i+3] = genereLine(0,1,1,2);
			map[i+2] = genereLine(0,1,1,lenX - 1);
			map[i+1] = genereLine(0,1,lenX - 2,lenX - 1);
			map[i] = genereLine(0,1,1,lenX - 1);
		}

		map[lenY -2] = genereLine(0,1,1,lenX - 1); //avant dernière ligne
		map[1][1] = 2;    //spawn
		spawnX = 1;
		spawnY = 1;
		map[lenY - 2][lenX - 2 ] = 3;  //fin
		map[lenY-1] = line1;	//Murs du bas
	}


	public void newVague() {
		nVague += 1;
		timer = 0;
		nbEnemies = 10 + nVague / 5;
	}


	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		double x,y;

		for ( int i = 0; i < lenY; i++)
		{
			for(int j = 0; j < lenX; j++)
			{
				x = 32*j;
				y = 32*i;
				switch (map[i][j]) {
				case 0 :  g.drawImage(sprite0, (float)x, (float)y);
				break;
				case 1 :  g.drawImage(sprite1, (float)x, (float)y);
				break;
				case 2 :  g.drawImage(sprite2, (float)x, (float)y);
				break;
				case 3 :  g.setColor(Color.blue);g.fillRect((float)(x), (float)(y),width, height);
				break;
				}
			}
		}
		g.drawImage(base, 1205, 520);

	}

	protected void update(GameContainer container, StateBasedGame game, int delta) {
		timer += delta;
		if ((timer > 800) && (nbEnemies > 0)) {
			Enemy e = new Enemy(this.world, spawnX, spawnY,this,nVague,pl);
			nbEnemies -= 1;
			timer = 0;
		}
		else if ((nbEnemies == 0) && (this.world.enemies.isEmpty())) {
			newVague();  // nouvelle vague avec un ennemis de moins qu'avant
			if (nVague % 5 == 0)  {
				nbEnemies = 2;  //Si c'est une vague de boss on ne prévoit que 2 ennemis
			}
		}
	}

	public int getCase(int ligne, int colonne) {
		if (ligne > lenY -1|| colonne > lenX - 1) {
			return -1; //Dépassement de la carte
		} else {
			return map[ligne][colonne];
		}
	}



	public double getX(int colonne) {
		//Donne le x du point en haut à gauche de la case à la colonne 'colonne'
		return 32*colonne;
	}

	public double getY(int ligne) {
		//Donne le y du point en haut à gauche de la case à la ligne 'ligne'
		return (32*ligne);
	}

	public int getnVague() {
		return nVague;
	}
}
