package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main. Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() {
		// Construction de la carte avec ses caractérisques
		super("Throne Room", 4);
	}

	@Override
	public void play(Player p) {
		// On cré la liste des cartes Actions dans la main du joueur
		CardList action = p.getActionCards();
		// On lui demande d'en choisir une
		String inputc = p.chooseCard(
				"Choisissez une carte à jouer deux fois : ", action, true);
		// On fait appel au play de la carte
		action.getCard(inputc).play(p);
		// On joue la carte normalement
		p.playCard(inputc);
	}
}