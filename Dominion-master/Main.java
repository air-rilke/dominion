import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

/**
 * Classe pour l'exécution d'une partie de Dominion
 */
class Main {
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le
		// tableau)
		String[] playerNames = new String[] { "Marco", "Polo" };
		// Prépare les piles "royaume" de la réserve (hors cartes communes)
		List<CardList> kingdomStacks = new ArrayList<CardList>();
		// Ajouter un bloc pour chaque carte royaume à utiliser

		// Ajout de 10 cartes Adventurer
		// kingdomStacks.add(new CardList(Adventurer.class, 10));
		// Ajout de 10 cartes Bureaucrat
		// kingdomStacks.add(new CardList(Bureaucrat.class, 10));
		// Ajout de 10 cartes Cellar
		kingdomStacks.add(new CardList(Cellar.class, 10));
		// Ajout de 10 cartes Chancellor
		// kingdomStacks.add(new CardList(Chancellor.class, 10));
		// Ajout de 10 cartes Chapel
		// kingdomStacks.add(new CardList(Chapel.class, 10));
		// Ajout de 10 cartes CouncilRoom
		// kingdomStacks.add(new CardList(CouncilRoom.class, 10));
		// Ajout de 10 cartes Feast
		// kingdomStacks.add(new CardList(Feast.class, 10));
		// Ajout de 10 cartes Festival
		// kingdomStacks.add(new CardList(Festival.class, 10));
		// Ajout de 10 cartes Gardens
		// kingdomStacks.add(new CardList(Gardens.class, 10));
		// Ajout de 10 cartes Laboratory
		// kingdomStacks.add(new CardList(Laboratory.class, 10));
		// Ajout de 10 cartes Library
		// kingdomStacks.add(new CardList(Library.class, 10));
		// Ajout de 10 cartes Market
		kingdomStacks.add(new CardList(Market.class, 10));
		// Ajout de 10 cartes Militita
		kingdomStacks.add(new CardList(Militia.class, 10));
		// Ajout de 10 cartes Mine
		kingdomStacks.add(new CardList(Mine.class, 10));
		// Ajout de 10 cartes Moat
		kingdomStacks.add(new CardList(Moat.class, 10));
		// Ajout de 10 cartes Moneylender
		// kingdomStacks.add(new CardList(Moneylender.class, 10));
		// Ajout de 10 cartes Remodel
		kingdomStacks.add(new CardList(Remodel.class, 10));
		// Ajout de 10 cartes Smithy
		kingdomStacks.add(new CardList(Smithy.class, 10));
		// Ajout de 10 cartes Spy
		// kingdomStacks.add(new CardList(Spy.class, 10));
		// Ajout de 10 cartes Thief
		kingdomStacks.add(new CardList(Thief.class, 10));
		// Ajout de 10 cartes ThroneRoom
		// kingdomStacks.add(new CardList(ThroneRoom.class, 10));
		// Ajout de 10 cartes Village
		kingdomStacks.add(new CardList(Village.class, 10));
		// Ajout de 10 cartes Witch
		// kingdomStacks.add(new CardList(Witch.class, 10));
		// Ajout de 10 cartes Woodcutter
		kingdomStacks.add(new CardList(Woodcutter.class, 10));
		// Ajout de 10 cartes Workshop
		kingdomStacks.add(new CardList(Workshop.class, 10));

		// Instancie et exécute une partie
		Game g = new Game(playerNames, kingdomStacks);
		System.out.println("Lancement de la partie...");
		g.run();
	}
}