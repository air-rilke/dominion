package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes. +1 Achat. Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {

	public CouncilRoom() {
		// Construction de la carte avec ses caractérisques
		super("Council Room", 5);
	}

	@Override
	public void play(Player p) {
		// On pioche 4x 1 carte
		for (int i = 0; i < 4; i++) {
			p.drawToHand();
		}
		// +1 Achat
		p.incrementBuys(1);
		// Les adversaires piochent une carte
		for (Player o : p.otherPlayers()) {
			o.drawToHand();
		}
	}
}