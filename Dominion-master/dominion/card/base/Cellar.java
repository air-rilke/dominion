package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action. Défaussez autant de cartes que vous voulez. +1 Carte par carte
 * défaussée.
 */
public class Cellar extends ActionCard {

	public Cellar() {
		// Construction de la carte avec ses caractérisques
		super("Cellar", 2);
	}

	@Override
	public void play(Player p) {
		// +1 Action
		p.incrementActions(1);
		// On va pouvoir défausser autant de carte qu'on voudra, dans la limite
		// de la main
		// On va garder ce chiffre dans une variable
		int cptDef = 0;
		// Tant que le joueur à des cartes en main
		while (!p.cardsInHand().isEmpty()) {
			// On lui demande laquelle il veut defausser
			String inputc = p.chooseCard("Choisissez une carte à défausser.",
					p.cardsInHand(), true);
			// S'il ne passe pas la question
			if (!inputc.equals("")) {
				// On defausse la carte en question
				p.discardCard(inputc);
				// On incrémente le compteur de carte defaussé
				cptDef++;
			} else {
				// On quitte la boucle si le joueur passe la question
				break;
			}
		}
		// On pioche une carte pour chaque carte défaussée
		for (int i = 0; i < cptDef; i++) {
			p.drawToHand();
		}
	}
}