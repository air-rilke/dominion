package dominion.card.common;

import dominion.*;
import dominion.card.*;

/**
 * Carte Malédiction (Curse)
 * 
 * -1 VP
 */
public class Curse extends CurseCard {
	public Curse() {
		super("Curse", 0);
	}

	public int victoryValue(Player p) {
		return -1;
	}

	@Override
	public void play(Player p) {
		// les cartes malédictions ne se jouent pas. Par conséquent,
		// cette méthode ne fait rien
	}
}