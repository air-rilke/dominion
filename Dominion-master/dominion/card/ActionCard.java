package dominion.card;

import java.util.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {

	public ActionCard(String name, int cost) {
		// Utilisation du constructeur de Card
		super(name, cost);
	}

	@Override
	public List<CardType> getTypes() {
		// on récupère la liste des types(vide) de Card dans une nouvelle liste
		List<CardType> types = super.getTypes();
		// on ajoute le type Action à cette liste
		types.add(CardType.Action);
		// on retourne cette liste
		return types;
	}
}