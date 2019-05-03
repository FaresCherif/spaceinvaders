package fr.unilim.iut.spaceinvader;
import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class Vaisseau extends Sprite {
		
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	 public Missile tirerUnMissile(Dimension dimension, int vitesseMissile) {
	
		 if (this.longueur()< dimension.longueur()){
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		 }
				
				 
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimension);
		return new Missile(dimension, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);
		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		
		return new Position(abscisseOrigineMissile,ordonneeeOrigineMissile);
	}
	 
}