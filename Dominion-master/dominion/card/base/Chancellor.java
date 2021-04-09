package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces. Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

	public Chancellor() {
		// Construction de la carte avec ses caractérisques
		super("Chancellor", 3);
	}

	@Override
	public void play(Player p) {
		// +2 Pièces
		p.incrementMoney(2);
		// Propose au joueur de défausser immédiatement son deck
		List<String> choices = Arrays.asList("y", "n");
		String input = p.choose("Voulez-vous défausser une carte (y/n)",
				choices, false);
		// Si oui, défausse le deck {@code draw}
		if (input.equals("y")) {
			p.discardDraw();
		}
		// Si non, ne fait rien de plus
	}
}