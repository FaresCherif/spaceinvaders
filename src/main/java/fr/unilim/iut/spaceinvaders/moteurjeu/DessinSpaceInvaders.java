package fr.unilim.iut.spaceinvaders.moteurjeu;

import java.awt.image.BufferedImage;
import java.awt.Color;
import fr.unilim.iut.spaceinvader.SpaceInvaders;
import java.awt.Graphics;


public class DessinSpaceInvaders implements DessinJeu {
	
	private SpaceInvaders jeu;
	
	public void dessiner2(Graphics image) {
		image.fillRect(jeu.vaisseau.abscisseLaPlusAGauche(),jeu.vaisseau.ordonneeLaPlusHaute(),  jeu.vaisseau.dimension.longueur(), jeu.vaisseau.dimension.hauteur());
	}

	@Override
	public void dessiner(BufferedImage image) {
		//image.fillRect(jeu.vaisseau.abscisseLaPlusAGauche(),jeu.vaisseau.ordonneeLaPlusHaute(),  jeu.vaisseau.dimension.longueur(), jeu.vaisseau.dimension.hauteur());
	}

}
