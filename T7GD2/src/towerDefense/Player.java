package towerDefense;

public class Player {
	private int wave;
	private int gold;
	private int totalGold;
	private int lives;
	private int boughtTowers;
	
	public Player(int lives, int startGold) {
		this.lives=lives;
		this.gold= startGold;
		this.totalGold = startGold;
		boughtTowers = 0;
		wave = 1;
		
	}
	
	
	public void buyTower(int price) {
		// Vérifier si on a l'argent puis la retirer
		
		boughtTowers+=1;
	}
	
	
	public void earnGold(int piece) {
		this.gold += piece;
		totalGold += piece;
	}
	
	
	public void damaged(int damage) {
		lives -= damage;
		if (lives <= 0) {
			endGame();
		}
	}
	
	
	public void endGame() {
		// Pop-up : or total, vagues, nb de tours construites/upgrades, 
	}
	
}
