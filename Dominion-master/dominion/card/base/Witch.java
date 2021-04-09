package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes. Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	public Witch() {
		// Construction de la carte avec ses caractérisques
		super("Witch", 5);
	}

	@Override
	public void play(Player p) {
		// On pioche 1 carte 2x
		for (int i = 0; i < 2; i++) {
			p.drawToHand();
		}
		// Les adversaires reçoivent une carte Malédiction
		for (Player o : p.otherPlayers()) {
			// On verifiqu'ils ne pocede pas de carte Action Reaction Moat
			// Et qu'il souhaite la jouer
			// Si ce n'est pas le cas
			if (!this.otherPlayerGotReactionMoat(o)) {
				// {@code o} gagne une carte curse
				o.gain(o.getGame().getFromSupply("Curse"));
				// Que l'on retire de la réserve
				o.getGame().removeFromSupply("Curse");
			}
		}
	}
}