package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Village
 * 
 * +1 Carte. +2 Actions.
 */
public class Village extends ActionCard {

	public Village() {
		// Construction de la carte avec ses caractérisques
		super("Village", 3);
	}

	@Override
	public void play(Player p) {
		// +2 Actions
		p.incrementActions(2);
		// +1 Carte => pioche 1 carte
		p.drawToHand();
	}
}