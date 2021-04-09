package dominion.card;

import java.util.*;

import dominion.Player;

/**
 * Les cartes Attaque Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {

	public AttackCard(String name, int cost) {
		// Utilisation du constructeur de ActionCard
		super(name, cost);
	}

	public List<CardType> getTypes() {
		// on récupère la liste des types(Action) de ActionCard dans une
		// nouvelle liste
		List<CardType> types = super.getTypes();
		// on ajoute le type Attack a cette liste
		types.add(CardType.Attack);
		// on retourne cette liste
		return types;
	}

	/**
	 * Controle si le joueur à des cartes Action Reaction et s'il souhaite en
	 * jouer une
	 * 
	 * @param p
	 *            Le joueur qui pocède ou non des cartes Action Réaction
	 * @return true si le joueur a des cartes Action / Réaction, false s'il n'en
	 *         a pas ou ne souhaite pas les jouer.
	 */
	public boolean otherPlayerGotReactionMoat(Player p) {
		// On parcours les carte du joueur {@code p}
		// Pour trouver une carte Action Reaction
		for (Card c : p.getActionCards()) {
			// Si c'est le cas
			if (c.getTypes().contains(CardType.Reaction)) {
				// Et on active la {@code reaction}
				return ((ReactionCard) c).reaction(p);
			}
		}
		// On retourne false s'il n'en possede pas
		return false;
	}
}