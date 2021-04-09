package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {

	public Workshop() {
		// Construction de la carte avec ses caractérisques
		super("Workshop", 3);
	}

	@Override
	public void play(Player p) {
		// On cré la liste des carte que l'on peut recevoir
		CardList list = new CardList();
		// On parcourt la liste des cartes disponibles {@code supplyStacks}
		for (Card c : p.getGame().availableSupplyCards()) {
			// Si la carte es de coût inférieur ou égal à 4
			if (c.getCost() <= 4) {
				// On l'ajoute a la liste
				list.add(c);
			}
		}
		// On propose au joueur d'en choisir une
		String inputc = p.chooseCard("Choisissez une carte à recevoir : ",
				list, true);
		// il la reçoit dans la defausse}
		p.gain(inputc);
	}
}