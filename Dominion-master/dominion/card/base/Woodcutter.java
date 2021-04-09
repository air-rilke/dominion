package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bûcheron (Woodcutter)
 * 
 * +1 Achat. +2 Pièces.
 */
public class Woodcutter extends ActionCard {

	public Woodcutter() {
		// Construction de la carte avec ses caractérisques
		super("Woodcutter", 3);
	}

	@Override
	public void play(Player p) {
		// +1 Achat
		p.incrementBuys(1);
		// +2 Pièces
		p.incrementMoney(2);
	}
}