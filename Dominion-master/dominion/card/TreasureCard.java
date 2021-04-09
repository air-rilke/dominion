package dominion.card;

import java.util.*;
import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {
	public TreasureCard(String name, int cost) {
		// Utilisation du constructeur de Card
		super(name, cost);
	}
	
	public abstract int treasureValue();

	@Override
	public List<CardType> getTypes() {
		// On récupère la liste des types(vide) de Card dans une nouvelle liste
		List<CardType> types = super.getTypes();
		// On ajoute le type Treasure à cette liste
		types.add(CardType.Treasure);
		// On retourne cette liste
		return types;
	}
	
	@Override
	public void play(Player p) {
        p.incrementMoney(this.treasureValue());
    }
}