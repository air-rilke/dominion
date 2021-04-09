package dominion.card.base;

import java.util.Arrays;
import java.util.List;

import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes. Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler
 * cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {

	public Moat() {
		// Construction de la carte avec ses caractérisques
		super("Moat", 2);
	}

	@Override
	public void play(Player p) {
		// +2 Carte = pioche 2 carte
		for (int i = 0; i < 2; i++) {
			p.drawToHand();
		}
	}

	@Override
	public boolean reaction(Player p) {
		// On demande au joueur s'il souhaite la jouer
		List<String> choices = Arrays.asList("y", "n");
		String input = p.choose("Voulez vous jouer la carte " + this.toString()
				+ " ? (y/n)", choices, true);
		// Si oui
		if (input.equals("y")) {
			// On joue la carte
			System.out.println("Carte " + this.getName()
					+ " devoilée, la carte attack n'as pas d'effet");
			// On retourve vrai
			return true;
		} else {
			// On signale qu'il ne souhaite pas la jouer
			return false;
		}
	}
}