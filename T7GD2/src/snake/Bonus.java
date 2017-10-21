package snake;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Bonus {
	
	public Point pt;
	
	public static enum bonusType {bGrandis,bRetrecis,bRapide,bLent,bMort,bInverseBonus,bInverseMalus};
	
	public bonusType type;
	public Image imageBonus;
	public int rayon;
	
	public static Bonus RandomBonus(Point pt){
		Random r = new Random();
		double b = r.nextFloat();
		bonusType bonus;
		if(b < 0.4)
			bonus = bonusType.bGrandis;
		else if(b < 0.6)
			bonus = bonusType.bRetrecis;
		else if(b < 0.7)
			bonus = bonusType.bRapide;
		else if(b < 0.8)
			bonus = bonusType.bLent;
		else if(b < 0.9)
			bonus = bonusType.bInverseBonus;
		else if(b < 0.95)
			bonus = bonusType.bInverseMalus;
		else
			bonus = bonusType.bMort;
		
		return new Bonus(pt,bonus,r.nextInt(4)+1);
	}
	
	public Bonus(Point pt,int numBonus,int rayon){
		this(pt,bonusType.values()[numBonus],rayon);
	}
	
	public Bonus(Point pt,bonusType bonus,int rayon){
		this.pt=pt;
		this.type = bonus;
		
		try{
			this.imageBonus = new Image(imagePath());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		this.rayon = rayon;
	}
	
	public void applyBonus(Snake s){
		switch(this.type){
		case bGrandis:
			s.grandir();
		break;
		case bRetrecis:
			s.retrecir();
		break;
		case bRapide:
			s.plusRapide();
		break;
		case bLent:
			s.plusLent();
		break;
		case bMort:
			World.dead(s);
			
		break;
		case bInverseBonus:
			
		break;
		}
	}
	
	private String imagePath(){
		String path = "images/snake/";
		switch(type){
		case bGrandis:
			path+="Grand";
		break;
		case bRetrecis:
			path+="Petit";
		break;
		case bRapide:
			path+="Lapin";
		break;
		case bLent:
			path+="Tortue";
		break;
		case bMort:
			path+="Remi";
		break;
		case bInverseBonus:
			path+="InverseBonus";
		break;
		case bInverseMalus:
			path+="InverseMalus";
		break;
		}
		
		return path+".png";
	}
	
	public Boolean isInBonus(Point p){
		return(this.pt.x-p.x <= rayon && p.x-this.pt.x <= rayon && this.pt.y - p.y <= rayon && p.y-this.pt.y  <= rayon);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		imageBonus.draw(pt.x*10-10*rayon,pt.y*10-10*rayon,10+20*rayon,10+20*rayon);
	}
	
}
