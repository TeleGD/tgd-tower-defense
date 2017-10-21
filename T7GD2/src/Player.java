

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;



public class Player {
	protected double speed;
	float x,y;
	float width,height;
	enum direction {HAUT,DROITE,BAS,GAUCHE};
	direction dir;
	protected boolean ismoving,upPress,downPress,rightPress,leftPress,droitegauche,hautbas;
	protected double speedX,speedY;
	protected Color couleur;
	
	public Player() {
		couleur=Color.green;
		speed=2;
		x = 150;
		y = 150;
		width = 32;
		height = 32;
	}
	
	public Player(double speed){
		couleur = Color.green;
		this.speed = speed;
		x = 150;
		y = 150;
		width = 32;
		height = 32;
	}
	
	public Player(float x, float y) {
		couleur = Color.green;
		speed = 2;
		this.x = x;
		this.y = y;
		width = 32;
		height = 32;
	}
	
	public Player(float x, float y, double speed){
		couleur = Color.green;
		this.speed = speed;
		this.x = x;
		this.y = y;
		width = 32;
		height = 32;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(couleur);
		g.fillRect(x, y, width, height);
	}

	protected void update(GameContainer container, StateBasedGame game, int delta) {
		horizontalMove();
		verticalMove();
		move();
	}
	
	// Les touches*******************************************************
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_Z:
			upPress = false;
			break;

		case Input.KEY_S:
			downPress = false;
			break;

		case Input.KEY_Q:
			leftPress = false;
			break;

		case Input.KEY_D:
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
			case Input.KEY_Z:
				upPress = true;
				break;

			case Input.KEY_S:
				downPress = true;
				break;

			case Input.KEY_Q:
				leftPress = true;
				droitegauche = false;
				break;
			case Input.KEY_D:
				rightPress = true;
				droitegauche = true;
				break;
		}
	}

	protected void move(){
			x+=speedX*speed;
			y+=speedY*speed;
	}
	
	protected void horizontalMove() {
		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > 0) {
				speedX = -0.5;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 1280 - width) {

				speedX = 0.5;
			}
		}
	}
	
	protected void verticalMove() {
		speedY = 0;
		if ((upPress && !downPress) || (upPress && downPress && !hautbas)) {
			if (y > 0) {
				speedY = -0.5;
			}

		}
		if ((!upPress && downPress) || (upPress && downPress && hautbas)) {
			if (y < 720 - width) {

				speedY = 0.5;
			}
		}
	}
}
