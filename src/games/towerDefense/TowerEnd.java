package games.towerDefense;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TowerEnd extends BasicGameState{

	private int ID;
	public int wave;

	public TowerEnd(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		// TODO Auto-generated method stub

	}

	public void changeWave(int waveN){
		wave = waveN;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		// TODO Auto-generated method stub
		arg2.drawString("GAME OVER", arg0.getWidth()/2 - 100, arg0.getHeight()/2);
		arg2.drawString("Wave : "+wave, arg0.getWidth()/2 - 100, arg0.getHeight()/2 + 32);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			((World) arg1.getState(3 /* World */)).setState(0);
			arg1.enterState(1 /* Choice */, new FadeOutTransition(), new FadeInTransition());
		}
	}

}
