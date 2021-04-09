package dominion.card;

import java.util.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {
	public CurseCard(String name, int cost) {
		// Utilisation du constructeur de Card
		super(name, cost);
	}

	@Override
	public List<CardType> getTypes() {
		// On récupère la liste des types(vide) de Card dans une nouvelle liste
		List<CardType> types = super.getTypes();
		// On ajoute le type Curse à cette liste
		types.add(CardType.Curse);
		// On retourne cette liste
		return types;
	}
}