package snake;

import general.ui.Button;
import general.utils.FontUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Arrays;

public class World extends BasicGameState {

    public MenuMulti menu;
    public static int longueur=1280;
    public static int hauteur=720;
    public static int ID=1;
    private float widthBandeau = 280;

    private static ArrayList<Snake> snakes;

    private TrueTypeFont font = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,20);
    private Button replay;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        menu = new MenuMulti();
        menu.init(container, game);

        snakes = new ArrayList<Snake>();

        replay = new Button(container,World.longueur - widthBandeau+20, World.hauteur-100,widthBandeau-40,40);
        replay.setText("RETOUR AU MENU");
        replay.setBackgroundColor(Color.black);
        replay.setBackgroundColorEntered(Color.white);
        replay.setTextColor(Color.white);
        replay.setTextColorEntered(Color.black);
        replay.setCornerRadius(25);

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
        g.resetFont();

        for(int i=0;i<snakes.size();i++){
            g.setColor(snakes.get(i).couleur);
            g.drawString(snakes.get(i).nom+" : "+snakes.get(i).score,World.longueur-widthBandeau+20,100+30*i);
        }

        replay.render(container, game, g);
        menu.render(container, game, g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        menu.update(container, game, delta);
        replay.update(container, game,delta);

        for(int i=0;i<snakes.size();i++){
            snakes.get(i).GScore(1);
            snakes.get(i).update(container, game,delta);

            for(int j = i+1;j<snakes.size();j++){

                if(collide(snakes.get(i).body.get(0),snakes.get(j))){
                    snakes.remove(i);
                    i--;
                }

            }
        }

    }

    private boolean collide(Point point, Snake snake) {
        for(int i=0;i<snake.body.size();i++)
        {
            if(snake.body.get(i).x==point.x && snake.body.get(i).y==point.y){
                if(i==0)snakes.remove(snake);
                return true;
            }
        }
        return false;
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

    }

    public static void setSnakes(Snake[] snake){
        snakes = new ArrayList<Snake>(Arrays.asList(snake));
    }

    public static void dead(Snake snake){
        snakes.remove(snake);
    }
}
