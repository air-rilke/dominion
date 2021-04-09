package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes. +1 Action.
 */
public class Laboratory extends ActionCard {

	public Laboratory() {
		// Construction de la carte avec ses caractérisques
		super("Laboratory", 5);
	}

	@Override
	public void play(Player p) {
		// +2 Cartes = pioche 2 cartes
		for (int i = 0; i < 2; i++) {
			p.drawToHand();
		}
		// +1 Action
		p.incrementActions(1);
	}
}