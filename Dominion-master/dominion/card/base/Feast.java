package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte. Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {

	public Feast() {
		// Construction de la carte avec ses caractérisques
		super("Feast", 4);
	}

	@Override
	public void play(Player p) {
		// La carte est écartée
		p.trashCard(this.getName(), "inPlay");
		// On reçoit une carte coûtant jusqu'a 5 Pièces :
		// On cré la liste des cartes disponibles
		CardList available = new CardList();
		for (Card c : p.getGame().availableSupplyCards()) {
			if (c.getCost() <= 5) {
				available.add(c);
			}
		}
		// On demande au joueur d'en choisir une
		String inputc = p.chooseCard("Quelle carte souhaitez-vous recevoir?",
				available, true);
		// On reçoit cette carte
		p.gain(inputc);
	}
}