package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 * 
 * +1 Carte. +1 Action. +1 Achat. +1 Pièce.
 */
public class Market extends ActionCard {

	public Market() {
		// Construction de la carte avec ses caractérisques
		super("Market", 5);
	}

	@Override
	public void play(Player p) {
		// +1 Action
		p.incrementActions(1);
		// +1 Achat
		p.incrementBuys(1);
		// +1 Pièce
		p.incrementMoney(1);
		// +1 Carte = pioche 1 carte
		p.drawToHand();
	}
}