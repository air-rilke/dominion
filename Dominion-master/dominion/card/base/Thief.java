package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils
 * dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces
 * cartes Trésor écartées, recevez celles de votre choix. Les autres cartes
 * dévoilées sont défaussées.
 */
public class Thief extends AttackCard {

	public Thief() {
		// Construction de la carte avec ses caractérisques
		super("Thief", 4);
	}

	@Override
	public void play(Player p) {
		// On initialise une liste de cartes à écarter
		CardList toTrash = new CardList();
		// On parcourt la liste des adversaires
		for (Player op : p.otherPlayers()) {
			// Si le joueur n'as pas de carte Moat
			if (!this.otherPlayerGotReactionMoat(op)) {
				// On dévoile les 2 premières cartes de sa pioche
				// On cré une liste de cartes contennant les 2 premières cartes
				// de sa pioche
				CardList couple = new CardList();
				// à laquelle on ajoute les cartes
				for (int i = 0; i < 2; i++) {
					// On stocke la carte dans une variable temporaire
					Card tmpC = op.drawCard();
					// Si une carte est bien piochée
					if (tmpC != null) {
						// On l'ajoute a {@code couple}
						couple.add(tmpC);
					} else {
						// Sinon on indique que rien n'est pioché
						System.err
								.println("Il n'y à plus de carte à piocher !");
					}
				}
				// On l'affiche
				System.out.println("Liste de cartes : " + couple.toString());
				// On parcourt couple
				for (int i = 0; i < couple.size(); i++) {
					// On discard toute carte qui n'est pas Trésor
					if (!couple.get(i).getTypes().contains(CardType.Treasure)) {
						op.gain(couple.remove(i));
					}
				}
				// Si couple est vide
				if (couple.isEmpty()) {
					// On affiche un message
					System.out
							.println("Il n'y a pas de TreasureCard à voler !");
				} else {
					// Si couple ne contient qu'un élement, il sera ajouté
					// automatiquement
					// S'il en contien 2, on en choisit 1
					String inputt = p.chooseCard("Choisissez une carte",
							couple, false);
					System.err.println(inputt);
					// On l'ajoute à la liste
					toTrash.add(couple.getCard(inputt));
					// On l'nlève de couple
					couple.remove(inputt);
					// Et on ajoute l'autre à la défausse de l'adversaire
					if (!couple.isEmpty())
						op.gain(couple.get(0));
				}
			}
		}
		// Parmi la liste des cartes à écarter, on choisit celle qu'on veut
		// recevoir
		// On cré un booléen indiquant si le joueur souhaite continuer à
		// recevoir des cartes
		boolean choice = true;
		// Tant que la liste n'est pas vide et que l'on souhaite continuer à
		// recevoir des cartes
		while (!toTrash.isEmpty() && choice == true) {
			String inputc = p.chooseCard("Choisissez une carte à recevoir",
					toTrash, true);
			// Si le joueur n'a pas choisit une carte de la liste
			if (inputc.equals("")) {
				// On quite la boucle
				choice = false;
			} else {
				// Sinon, la carte est reçue
				p.gain(toTrash.getCard(inputc));
				// Et retirée de la liste toTrash
				toTrash.remove(inputc);
			}
		}
		// On écarte le reste
		while (!toTrash.isEmpty()) {
			 p.getGame().trash(toTrash.remove(0));
		}

	}
}
