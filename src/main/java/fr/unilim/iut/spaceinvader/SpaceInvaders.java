package fr.unilim.iut.spaceinvader;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Constante;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.Direction;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	public Vaisseau vaisseau;
	private Missile missile;
	private Constante constante;
	private Envahisseur envahisseur;
	private Direction directionParDefault=Direction.DROITE;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
				marque = Constante.MARQUE_MISSILE;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}
	
	public boolean aUnMissile() {
		return missile != null;
	}
	
	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = Constante.ENVAHISSEUR_LONGUEUR;
		int hauteurVaisseau = constante.ENVAHISSEUR_HAUTEUR;

		Dimension dimensionEnvahisseur=new Dimension(longueurVaisseau,hauteurVaisseau);
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimensionEnvahisseur, position, vitesse);
	}
	
	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		
		 if (commandeUser.tir && !this.aUnMissile()) {
	           tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),Constante.MISSILE_VITESSE);
	      }
		 
		 if(null!=missile) {
			 this.deplacerMissile();
		 }
		 
		 if(null!=envahisseur) {
			 this.deplacerEnvahisseur(directionParDefault);
			 if(envahisseur.abscisseLaPlusADroite()>=constante.ESPACEJEU_LONGUEUR) {
				 if(directionParDefault==Direction.DROITE) {
					 directionParDefault=Direction.GAUCHE;
				 }
			 }
			 if(envahisseur.abscisseLaPlusAGauche()<=0) {
				 if(directionParDefault==Direction.GAUCHE) {
					 directionParDefault=Direction.DROITE;
				 }
			 }
		 }
	}

	@Override
	public boolean etreFini() {
		return false;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	
	public Missile recupererMissile() {
		return this.missile;
	}
	
	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		
		Position positionEnvahisseur = new Position(this.longueur/2,this.hauteur/5);
		Dimension dimensionEnvahiseur = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauEnvahisseur(dimensionEnvahiseur, positionEnvahisseur, Constante.VAISSEAU_VITESSE);
	 }

	 public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
			
		   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
     }

	 public void deplacerMissile() {
		 	missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		 	if(missile.ordonneeLaPlusBasse()<0) {
		 		missile=null;
		 	}
	 }
	 
	 public void deplacerEnvahisseur(Direction direction) {
		 if(envahisseur.abscisseLaPlusADroite()<constante.ESPACEJEU_LONGUEUR) {
			 System.out.println(envahisseur.abscisseLaPlusADroite());	
			 envahisseur.deplacerHorizontalementVers(direction);
		 	}
		 else{
			 envahisseur.positionner(constante.ESPACEJEU_LONGUEUR-envahisseur.dimension.longueur(), envahisseur.origine.ordonnee()+5);
		 }
		 
		 if(envahisseur.abscisseLaPlusAGauche()<=0) {
			 envahisseur.positionner(0, envahisseur.origine.ordonnee()+5);
		 }
		 
		 
	 }
	  
	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}
	
	
}
