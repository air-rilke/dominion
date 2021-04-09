package dominion.card.common;

import dominion.*;
import dominion.card.*;

/**
 * Carte Duché (Duchy)
 * 
 * 3 VP
 */
public class Duchy extends VictoryCard {
	public Duchy() {
		super("Duchy", 5);
	}

	public int victoryValue(Player p) {
		return 3;
	}

	@Override
	public void play(Player p) {
		// Les cartes État, Duché et Provinces n'ont aucun effet

	}
}