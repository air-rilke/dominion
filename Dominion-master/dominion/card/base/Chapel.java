package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {

	public Chapel() {
		// Construction de la carte avec ses caractérisques
		super("Chapel", 2);
	}

	@Override
	public void play(Player p) {
		// Compteur des cartes défaussées
		int cpt = 0;
		// Boolean pour quitter la boucle
		boolean quit = false;
		// Tant qu'il nous reste des cartes a défausser (on en a pas encore
		// défaussé 4) et
		// qu'il nous reste des cartes en main
		while (!p.cardsInHand().isEmpty() && cpt < 4 && !quit) {
			// On demande au joueur quelles cartes en main il veut defausser
			String inputc = p.chooseCard("Choisissez une carte à écarter.",
					p.cardsInHand(), true);
			// Si je joueur ne passe pas
			if (!inputc.equals("")) {
				// On écarte la carte
				p.trashCard(inputc, "hand");
				// On incrémente le compteur de cartes
				cpt++;
			} else {
				// Si le joueur passe on quitte la boucle
				quit = true;
			}
		}
	}
}