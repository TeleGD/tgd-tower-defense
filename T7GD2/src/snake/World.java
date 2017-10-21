package snake;

import general.ui.Button;
import general.ui.TGDComponent;
import general.utils.FontUtils;
import menus.MainMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.*;

public class World extends BasicGameState {

    public static int nbcasesh=72;
    public static int nbcasesl=128;
    public static MenuMulti menu;
    public static int longueur=1280;
    public static int hauteur=720;
    public static int ID=1;
    private float widthBandeau = 280;
    private static ArrayList<Bonus> bonus;

    private static ArrayList<Snake> snakes;

    private TrueTypeFont font = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,20);
    private Button replay,backMenu;
    private static Music soundMusicBackground;

    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
        menu = new MenuMulti();
        menu.init(container, game);
        bonus = new ArrayList<Bonus>();

        snakes = new ArrayList<Snake>();

        replay = new Button(container,World.longueur - widthBandeau+20, World.hauteur-150,widthBandeau-40,40);
        replay.setText("REJOUER");
        replay.setBackgroundColor(Color.black);
        replay.setBackgroundColorEntered(Color.white);
        replay.setTextColor(Color.white);
        replay.setTextColorEntered(Color.black);
        replay.setCornerRadius(25);
        replay.setOnClickListener(new TGDComponent.OnClickListener() {
            @Override
            public void onClick(TGDComponent componenent) {
                if(soundMusicBackground!=null)soundMusicBackground.stop();
                reset();
            }
        });

        backMenu = new Button(container,World.longueur - widthBandeau+20, World.hauteur-100,widthBandeau-40,40);
        backMenu.setText("RETOUR AU MENU");
        backMenu.setBackgroundColor(Color.black);
        backMenu.setBackgroundColorEntered(Color.white);
        backMenu.setTextColor(Color.white);
        backMenu.setTextColorEntered(Color.black);
        backMenu.setCornerRadius(25);
        backMenu.setOnClickListener(new TGDComponent.OnClickListener() {
            @Override
            public void onClick(TGDComponent componenent) {
                if(soundMusicBackground!=null)soundMusicBackground.stop();
                game.enterState(MainMenu.ID,new FadeOutTransition(),new FadeInTransition());
            }
        });
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {


        for(int i=0;i<snakes.size();i++){
            snakes.get(i).render(container, game, g);
            g.setColor(Color.black);
        }

        g.setColor(new Color(150,150,150));
        g.fillRect(World.longueur-widthBandeau+2,0,widthBandeau,World.hauteur);
        g.setColor(new Color(170,170,170));
        g.fillRect(World.longueur-widthBandeau+4,0,widthBandeau,World.hauteur);
        g.setColor(new Color(200,200,200));
        g.fillRect(World.longueur-widthBandeau+6,0,widthBandeau,World.hauteur);

        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("SNAKE 3000 ! ",World.longueur-widthBandeau+20,20);

        g.setColor(new Color(150,150,150));
        g.fillRect(World.longueur-widthBandeau+6,60,widthBandeau,5);
        g.resetFont();

        for(int i=0;i<snakes.size();i++){
            g.setColor(snakes.get(i).couleur);
            g.drawString(snakes.get(i).nom+" : "+snakes.get(i).score,World.longueur-widthBandeau+20,100+50*i+20);
        }

        for(int i=0;i<bonus.size();i++){
            bonus.get(i).render(container, game, g);
        }

        replay.render(container, game, g);
        backMenu.render(container, game, g);
        menu.render(container, game, g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        menu.update(container, game, delta);
        replay.update(container, game,delta);
        backMenu.update(container, game,delta);
        Collections.sort(snakes, new Comparator<Snake>() {
            @Override
            public int compare(Snake s1, Snake s2) {
                if(s1.score>s2.score)return -1;
                else if(s1.score<s2.score)return 1;
                return 0;
            }
        });

        for(int i=0;i<snakes.size();i++){
            snakes.get(i).GScore(1);
            snakes.get(i).update(container, game,delta);

            for (Iterator<Bonus> it = bonus.iterator();it.hasNext();) {
                Bonus b = it.next();
                if (!snakes.get(i).mort){
                if (b.isInBonus(snakes.get(i).body.get(0))) {
                    b.applyBonus(snakes.get(i));
                    it.remove();
                	}
                }
            }

            for(int j = 0;j<snakes.size();j++){

                if(j!=i){
                    if(!snakes.get(i).mort){
                        if(collide(snakes.get(i).body.get(0),snakes.get(j))){
                            snakes.get(i).meurt();
                        }
                    }

                }


            }
        }
        addBonus();
    }

    private boolean collide(Point point, Snake snake) {
        for(int i=0;i<snake.body.size();i++)
        {
            if(snake.body.get(i).x==point.x && snake.body.get(i).y==point.y){
                if(i==0)snakes.get(i).meurt();
                return true;
            }
        }
        return false;
    }
    private void addBonus(){
        Random r =  new Random();
        if(r.nextFloat() >= 0.99){
            bonus.add(Bonus.RandomBonus(new Point(r.nextInt(nbcasesl)-28,r.nextInt(nbcasesh))));
        }
    }



    @Override
    public int getID() {
        return 1;
    }

    public void keyReleased(int key, char c){
        for(int i=0;i<snakes.size();i++){
            snakes.get(i).keyReleased(key,c);
        }
    }

    public void keyPressed(int key, char c){
        for(int i=0;i<snakes.size();i++){
            snakes.get(i).keyPressed(key,c);
        }
    }

    public static void reset() {
        snakes = new ArrayList<Snake>();
        menu.enleve=false;

    }

    public static void setSnakes(Snake[] snake){
        snakes = new ArrayList<Snake>(Arrays.asList(snake));

        try {
            soundMusicBackground=new Music("sounds/snake/hymne_russe.ogg");
            soundMusicBackground.play(1, 0.3f);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void dead(Snake snake){
        //snakes.remove(snake);
    }
}
