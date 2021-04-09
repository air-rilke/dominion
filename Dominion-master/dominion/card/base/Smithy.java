package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Forgeron (Smithy)
 * 
 * +3 Cartes.
 */
public class Smithy extends ActionCard {

	public Smithy() {
		// Construction de la carte avec ses caractérisques
		super("Smithy", 4);
	}

	@Override
	public void play(Player p) {
		// +3 Carte => pioche 3 carte
		for (int i = 0; i < 3; i++) {
			p.drawToHand();
		}
	}
}