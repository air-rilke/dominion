package dominion.card;

import java.util.*;
import dominion.Player;

/**
 * Les cartes Réaction Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {
	public ReactionCard(String name, int cost) {
		// Utilisation du constructeur de ActionCard
		super(name, cost);
	}

	public List<CardType> getTypes() {
		// On récupère la liste des types(Action) de ActionCard dans une
		// nouvelle liste
		List<CardType> types = super.getTypes();
		// On ajoute le type Réaction a cette liste
		types.add(CardType.Reaction);
		// On retourne cette liste
		return types;
	}

	/**
	 * Demande à un joueur qui pocede une reaction card s'il souhaite la jouer
	 * 
	 * @param p
	 *            joueur qui pocede la carte
	 * @return true s'il souhaite la jouer, sinon false
	 */
	public abstract boolean reaction(Player p);

}