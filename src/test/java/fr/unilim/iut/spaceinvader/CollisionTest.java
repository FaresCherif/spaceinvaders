package fr.unilim.iut.spaceinvader;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class CollisionTest {
	
	 private SpaceInvaders spaceinvaders;
	 private Collision collision;
	 
	 @Before
	 	public void initialiser() {
		 collision=new Collision();
	 }
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Dessous_Milieu() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(4,4),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	    
	 @Test
	    public void test_MissileToucheEnvahisseur_Dessous_Droite() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(5,4),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Dessous_Gauche() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(3,4),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Milieu_Gauche() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(3,5),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Milieu_Droite() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(5 ,5),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Haut_Droite() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(5 ,6),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
	 
	 @Test
	    public void test_MissileToucheEnvahisseur_Haut_Gauche() {
		 
		 Missile missile=new Missile(new Dimension(1,1),new Position(3 ,6),2);
		 Envahisseur envahisseur=new Envahisseur(new Dimension(3,3),new Position(3,4),2);
			
		   
	    assertEquals(true,collision.detecterCollision(missile, envahisseur));
		}
}
