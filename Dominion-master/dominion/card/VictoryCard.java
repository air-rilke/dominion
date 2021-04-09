package dominion.card;

import java.util.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {
	public VictoryCard(String name, int cost) {
		// Utilisation du constructeur de Card
		super(name, cost);
	}

	@Override
	public List<CardType> getTypes() {
		// On récupère la liste des types(vide) de Card dans une nouvelle liste
		List<CardType> types = super.getTypes();
		// On ajoute le type Victory à cette liste
		types.add(CardType.Victory);
		// On retourne cette liste
		return types;
	}

}