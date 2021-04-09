package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main. Recevez une carte coûtant jusqu'à 2 Pièces
 * de plus que la carte écartée.
 */
public class Remodel extends ActionCard {

	public Remodel() {
		// Construction de la carte avec ses caractérisques
		super("Remodel", 4);
	}

	@Override
	public void play(Player p) {
		// Le joueur choisit une carte de sa main qu'il souhaite écarter
		String inputc = p.chooseCard("Choisissez une carte à écarter",
				p.cardsInHand(), true);
		// On écarte cette carte
		Card card = p.trashCard(inputc, "hand");
		// Si la carte retourné est null
		if (card != null) {
			// On récupère sa valeur
			int val = card.getCost();
			// Le joueur choisit une carte parmis les suplyStack coutant jusqu'à
			// 2
			// Pièces de plus
			// On cré la liste des cartes a recevoir
			CardList list = new CardList();
			// On parcourt la liste des cartes disponibles {@code supplyStacks}
			for (Card c : p.getGame().availableSupplyCards()) {
				// Si la carte es de coût inférieur ou égal au coût de la carte
				// écartée +2
				if (c.getCost() <= val + 2) {
					// On l'ajoute a la liste
					list.add(c);
				}
			}
			// On propose au joueur d'en choisir une
			String inputr = p.chooseCard("Choisissez une carte à recevoir : ",
					list, true);
			// il la reçoit {@code supplyToHand(String cardName)}
			p.gain(inputr);
		} else {
			System.err.println("Votre main est vide !");
		}
	}
}