package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces. Tous vos adversaires défaussent leurs cartes de façon à n'avoir que
 * 3 cartes en main.
 */
public class Militia extends AttackCard {

	public Militia() {
		// Construction de la carte avec ses caractérisques
		super("Militia", 4);
	}

	@Override
	public void play(Player p) {
		// +2 Pièces
		p.incrementMoney(2);
		// On parcours les adversaire
		for (Player op : p.otherPlayers()) {
			// Si le joueur n'as pas de carte Moat
			if (!this.otherPlayerGotReactionMoat(op)) {
				// Tant que le joueur a plus de trois carte en mais
				while (op.cardsInHand().size() > 3) {
					// On lui demande quelle carte defausser
					String inputc = op.chooseCard(
							"Choisissez une carte à défausser.",
							op.cardsInHand(), false);
					// On defausse son choix
					op.discardCard(inputc);
				}
			}
		}
	}
}