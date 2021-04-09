package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main. Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {

	public Moneylender() {
		// Construction de la carte avec ses caractérisques
		super("Moneylender", 4);
	}

	@Override
	public void play(Player p) {
		// On parcour la liste des cartes Treasure en main de {@code p}
		for (Card c : p.getTreasureCards()) {
			// Si on trouvre une carte copper
			if (c.getName().equals("Copper")) {
				// On l'écarte
				p.trashCard(c.getName(), "hand");
				// On ajoute 3 d'argent
				p.incrementMoney(3);
				// On sort de la boucle
				break;
			}
		}
	}
}