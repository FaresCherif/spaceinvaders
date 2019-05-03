package fr.unilim.iut.spaceinvader;

import fr.unilim.iut.spaceinvaders.utils.Dimension;
import org.junit.Test;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.Position;


public class VaisseauTest {
	
	  @Test(expected = MissileException.class)
		public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
			Vaisseau vaisseau = new Vaisseau(new Dimension(5,2),new Position(5,9), 1);
			vaisseau.tirerUnMissile(new Dimension(7,2),1);
		}
	    

}
