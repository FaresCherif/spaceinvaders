package fr.unilim.iut.spaceinvaders.moteurjeu;

import java.awt.image.BufferedImage;
import java.awt.Color;

import fr.unilim.iut.spaceinvader.Envahisseur;
import fr.unilim.iut.spaceinvader.Missile;
import fr.unilim.iut.spaceinvader.SpaceInvaders;
import fr.unilim.iut.spaceinvader.Vaisseau;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu = spaceInvaders;
	}

	@Override
	public void dessiner(BufferedImage im) {
		if (this.jeu.aUnVaisseau()) {
			Vaisseau vaisseau = this.jeu.recupererVaisseau();
			this.dessinerUnVaisseau(vaisseau, im);
		}
		
		if (this.jeu.aUnMissile()) {
			Missile missile = this.jeu.recupererMissile();
			this.dessinerUnMissile(missile, im);
		}
		
		if(this.jeu.aUnEnvahisseur()) {
			Envahisseur envahisseur= this.jeu.recupererEnvahisseur();
			this.dessinerUnEnvahisseur(envahisseur, im);
		}
	}
	
	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.gray);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(),vaisseau.hauteur());
	}
	
	private void dessinerUnMissile(Missile missile, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.blue);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(),missile.hauteur());
	}
	
	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.red);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(),envahisseur.hauteur());
	}

}