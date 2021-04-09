package test;

import java.util.*;

import dominion.card.*;
import dominion.card.base.*;
import dominion.card.common.*;

public class TestExtra extends Test {

	private static IOGame defaultGame() {
		String []playerNames = new String[]{"Toto", "Titi", "Tutu"};
		List<CardList> kingdomStacks = new ArrayList<>();
		kingdomStacks.add(makeStack(Cellar.class, 10));
		kingdomStacks.add(makeStack(Chapel.class, 10));
		kingdomStacks.add(makeStack(Moat.class, 10));
		kingdomStacks.add(makeStack(Village.class, 10));
		kingdomStacks.add(makeStack(Woodcutter.class, 10));
		kingdomStacks.add(makeStack(Militia.class, 10));
		kingdomStacks.add(makeStack(ThroneRoom.class, 10));
		kingdomStacks.add(makeStack(Mine.class, 10));
		kingdomStacks.add(makeStack(Festival.class, 10));
		kingdomStacks.add(makeStack(Witch.class, 10));
		return new IOGame(playerNames, kingdomStacks);
	}

	private static void testInitialGameState(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		CardList totalCards = p0.player.totalCards();
		t.check(totalCards.size() == 10);
		for (Card c: totalCards) {
			t.check(c instanceof Copper || c instanceof Estate);
		}
	}

	private static void testStartTurn(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.player.endTurn();
		t.check(p0.getMoney() == 0);
		t.check(p0.getActions() == 0);
		t.check(p0.getBuys() == 0);
		t.check(p0.hand.size() == 5);
		for (Card c: p0.hand) {
			t.check(c.getName().equals("Copper") || c.getName().equals("Estate"));
		}
		p0.player.startTurn();
		t.check(p0.getMoney() == 0);
		t.check(p0.getActions() == 1);
		t.check(p0.getBuys() == 1);
	}

	private static void testPlaySilver(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Silver.class, 1);
		p0.playCard("Silver");
		t.check(p0.getMoney() == 2);
	}

	private static void testPlayCoppers(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.player.endTurn();
		p0.player.startTurn();
		p0.playCard("Copper");
		p0.playCard("Copper");
		t.check(p0.getMoney() == 2);
	}

	private static void testBuyChapel(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		p0.player.endTurn();
		p0.player.startTurn();
		p0.playCard("Copper");
		p0.playCard("Copper");
		p0.player.buyCard("Chapel");
		t.check(p0.getMoney() == 0);
		t.check(p0.getBuys() == 0);
		t.check(hasThisCard(p0.discard, "Chapel"));
	}

	private static void testEndTurn(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.player.endTurn();
		p0.player.startTurn();
		p0.playCard("Copper");
		p0.player.endTurn();
		t.check(p0.getMoney() == 0);
		t.check(p0.getBuys() == 0);
		t.check(p0.getActions() == 0);
	}

	private static GameProxy fullTurnSetup() {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Village.class, 1);
		p0.addToHand(Woodcutter.class, 1);
		p0.addToHand(Smithy.class, 1);
		p0.addToHand(Estate.class, 2);
		p0.addToDraw(Silver.class, 10);
		return g;
	}

	private static void testFullTurn1(Test t) {
		GameProxy g = fullTurnSetup();
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		g.setInput(
				"Festival", // essaie de jouer Festival (mais pas en main)
				"",			// passe
				"Chapel",	// essaie d'acheter Chapel (pas assez de pièces)
				""
		);
		p0.player.playTurn();
		t.check(hasCards(p0.discard,
				"Village",
				"Smithy",
				"Woodcutter",
				"Estate", "Estate"
		));
	}

	private static void testFullTurn2(Test t) {
		GameProxy g = fullTurnSetup();
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		g.setInput(
				"Smithy",	// pioche 3 Gold, passe en phase d'achats
				"Chapel", 	// achète Chapel
				"Chapel"	// pas d'achats disponibles
		);
		p0.player.playTurn();
		t.check(hasCards(p0.discard,
				"Village",
				"Smithy",
				"Woodcutter",
				"Estate", "Estate",
				"Silver", "Silver", "Silver",
				"Chapel"));
	}

	private static void testFullTurn3(Test t) {
		GameProxy g = fullTurnSetup();
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		g.setInput(
				"Village",
				"Smithy",
				"Woodcutter",	// 2 achats, 2 pièces, joue les 4 Silver en main
				"Gold",
				"Festival",		// pas assez d'or
				"Throne Room");
		p0.player.playTurn();
		t.check(hasCards(p0.discard,
				"Village",
				"Smithy",
				"Woodcutter",
				"Estate", "Estate",
				"Silver", "Silver", "Silver", "Silver",
				"Gold",
				"Throne Room"));
	}

	private static void testWitchNoCurse(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		CardList curses = g.getSupplyStack("Curse");
		while (curses.size() > 1) {
			curses.remove(0);
		}
		p0.clear();
		p1.clear();
		p2.clear();
		p1.addToHand(Witch.class, 1);
		p1.playCard("Witch");
		t.check(hasCards(p2.discard, "Curse"));
		t.check(p0.discard.isEmpty());
	}

	private static void testChapelMaxTrash(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Chapel.class, 1);
		p0.addToHand(Estate.class, 5);
		g.setInput("Chapel", "Estate", "Estate", "Estate", "Estate", "Estate", "");
		p0.playCard("Chapel");
		t.check(hasCards(p0.hand, "Estate"));
	}

	private static void testSmithyNoCards(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Smithy.class, 1);
		p0.addToDraw(Estate.class, 1);
		p0.addToDiscard(Copper.class, 1);
		p0.playCard("Smithy");
		t.check(hasCards(p0.hand, "Estate", "Copper"));
	}

	private static void testThroneRoomNoAction(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(ThroneRoom.class, 1);
		p0.addToHand(Silver.class, 2);
		p0.addToDraw(Village.class, 2);
		p0.playCard("Throne Room");
		t.check(hasCards(p0.hand, "Silver", "Silver"));
		t.check(p0.getActions() == 0);
	}

	private static void testMineNoTreasureToBuy(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		g.getSupplyStack("Copper").clear();
		g.getSupplyStack("Silver").clear();
		g.getSupplyStack("Gold").clear();
		p0.clear();
		p0.addToHand(Copper.class, 1);
		p0.addToHand(Silver.class, 1);
		p0.addToHand(Mine.class, 1);
		g.setInput("Silver");
		p0.playCard("Mine");
		t.check(hasCards(p0.hand, "Copper"));
		t.check(p0.discard.isEmpty());
	}

	private static void testMineNoTreasureInHand(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Mine.class, 3);
		p0.playCard("Mine");
		t.check(hasCards(p0.hand, "Mine", "Mine"));
	}

	private static void testLibrarySkipActions(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Library.class, 1);
		p0.addToHand(Copper.class, 1);
		p0.addToDraw(Village.class, 3);
		p0.addToDiscard(Estate.class, 3);
		g.setInput("y", "y", "y", "n", "n", "n");
		p0.playCard("Library");
		t.check(hasCards(p0.hand, "Copper", "Estate", "Estate", "Estate"));
	}

	private static void testAdventurerNoTreasures(Test t) {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		p0.clear();
		p0.addToHand(Adventurer.class, 1);
		p0.addToHand(Gold.class, 2);
		p0.addToDiscard(Silver.class, 1);
		p0.addToDiscard(Estate.class, 5);
		p0.addToDraw(Duchy.class, 5);
		p0.playCard("Adventurer");
		t.check(hasCards(p0.hand, "Gold", "Gold", "Silver"));
		t.check(p0.discard.size() == 10);
	}


	public void run() {
		this.runTest("Etat initial", TestExtra::testInitialGameState);
		this.runTest("Debut de tour", TestExtra::testStartTurn);
		this.runTest("Jouer un Silver", TestExtra::testPlaySilver);
		this.runTest("Jouer des Copper", TestExtra::testPlayCoppers);
		this.runTest("Acheter une Chapel", TestExtra::testBuyChapel);
		this.runTest("Fin du tour", TestExtra::testEndTurn);
		this.runTest("Tour complet 1", TestExtra::testFullTurn1);
		this.runTest("Tour complet 2", TestExtra::testFullTurn2);
		this.runTest("Tour complet 3", TestExtra::testFullTurn3);
		this.runTest("Witch (pas assez de Curse)", TestExtra::testWitchNoCurse);
		this.runTest("Chapel (4 trash max)", TestExtra::testChapelMaxTrash);
		this.runTest("Smithy (pas assez de cartes)", TestExtra::testSmithyNoCards);
		this.runTest("Throne Room (pas d'action)", TestExtra::testThroneRoomNoAction);
		this.runTest("Mine (pas de tresor a gagner)", TestExtra::testMineNoTreasureToBuy);
		this.runTest("Mine (pas de tresor en main)", TestExtra::testMineNoTreasureInHand);
		this.runTest("Library (pas assez de cartes)", TestExtra::testLibrarySkipActions);
		this.runTest("Adventurer (pas assez de tresors)", TestExtra::testAdventurerNoTreasures);
	}

	public static void main(String[] args) {
		TestExtra t = new TestExtra();
		t.run();
		t.showResults();
	}
}