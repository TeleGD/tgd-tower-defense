package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends Menu{

	public static int ID = -3;

	public MainMenu(){
		super.setTitrePrincipal("MULTIGAME 2 DESIGN");
		super.setTitreSecondaire("Menu Principal");
		//super.setItems(World1.GAME_NAME,World2.GAME_NAME,World3.GAME_NAME,"Scores", "Quitter");
		super.setItems("Tower Defense", "Quitter");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			games.towerDefense.World.reset();
			game.enterState(games.towerDefense.World.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 1:
			System.out.println("exit");
			System.exit(0);
			break;
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
