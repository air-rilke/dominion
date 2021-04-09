package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 * 
 * +2 Actions. +1 Achat. +2 Pièces.
 */
public class Festival extends ActionCard {

	public Festival() {
		// Construction de la carte avec ses caractérisques
		super("Festival", 5);
	}

	@Override
	public void play(Player p) {
		// +2 Action
		p.incrementActions(2);
		// +1 Achat
		p.incrementBuys(1);
		// +2 Pièce
		p.incrementMoney(2);
	}
}