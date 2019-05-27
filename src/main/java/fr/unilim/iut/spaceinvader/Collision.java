package fr.unilim.iut.spaceinvader;

public class Collision {
	
	private boolean collision;
	
	public Collision() {
		this.collision=false;
	}


	public boolean detecterCollision(Sprite sprtite1,Sprite sprite2) {
		 if(sprtite1.origine.abscisse()>=sprite2.origine.abscisse()&&sprtite1.origine.abscisse()<=sprite2.origine.abscisse()+sprite2.dimension.hauteur() ) {
			 if(sprtite1.origine.ordonnee()>=sprite2.origine.ordonnee()&&sprtite1.origine.ordonnee()<=sprite2.origine.ordonnee()+sprite2.dimension.hauteur()){
			 return true;
			 } 
		}
		 if(sprtite1.ordonneeLaPlusBasse()>sprite2.ordonneeLaPlusBasse()&&sprtite1.ordonneeLaPlusHaute()<sprite2.ordonneeLaPlusHaute()) {
			 if(sprtite1.abscisseLaPlusADroite()>=sprite2.abscisseLaPlusAGauche()&&sprtite1.abscisseLaPlusAGauche()<=sprite2.abscisseLaPlusAGauche()){
				 return true;
			 }

	}
		 return false;
	}


	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}
