package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte. +1 Action. Tous les joueurs (vous aussi) dévoilent la première
 * carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est
 * défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {

	public Spy() {
		// Construction de la carte avec ses caractérisques
		super("Spy", 4);
	}

	@Override
	public void play(Player p) {
		// On crée une liste de choix
		List<String> choicesYN = Arrays.asList("y", "n");
		// +1 Actions
		p.incrementActions(1);
		// +1 Carte => pioche 1 carte
		p.drawToHand();
		// Le joueur jouant la carte dévoile la premiere carte de son deck
		Card pioche = p.drawCard();
		// Si le joueur à bien piocher une carte
		if (pioche != null) {
			// Il choisi s'il la defausse ou non
			String input = p.choose(
					"Voulez vous defaussé la carte " + pioche.getName()
							+ "de votre main ? (y/n)", choicesYN, true);
			// Si oui
			if (input.equals("y")) {
				// On la met dans la defausse
				p.discardCard(pioche);
			} else {
				// On la remet en haut de la pioche
				p.putOnTopDraw(pioche);
			}
		}
		// On parcours ses adversaire
		for (Player op : p.otherPlayers()) {
			// Si le joueur n'as pas de carte Action Réaction Moat
			if (!this.otherPlayerGotReactionMoat(op)) {
				// On dévoile la premiere carte du deck de {@code op}
				pioche = op.drawCard();
				// Le joueur {@code p} choisi s'il cette carte est defaussé ou
				// non
				// Si le joueur à bien piocher une carte
				if (pioche != null) {
					// Il choisi s'il la defausse ou non
					String input = p.choose("Voulez vous defaussé la carte "
							+ pioche.getName() + "de la main de" + op.getName()
							+ " ? (y/n)", choicesYN, true);
					// Si oui
					if (input.equals("y")) {
						// On la met dans la defausse
						op.discardCard(pioche);
					} else {
						// On la remet en haut de la pioche
						op.putOnTopDraw(pioche);
					}
				}
			}
		}
	}
}