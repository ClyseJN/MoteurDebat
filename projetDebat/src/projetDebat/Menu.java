package projetDebat;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Scanner;

public class Menu {

	static int choix;
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	static String argumentNom;

	//map qui correspond au graphe
	static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	//map qui correspond � la solution
	static ArrayList<String> s = new ArrayList<String>();

	static GrapheInitial g = new GrapheInitial(map);
	static Solution solution = new Solution(s);




	/**
	 * Cette m�thode permet d'afficher le premier menu  
	 */
	public static void getMenuContradiction() {
		GrapheInitial.afficherArgContradiction();
		

		do {
			System.out.println();
			System.out.println("----------------------- Voici le premier menu -----------------------");
			System.out.println();
			System.out.println("1) Ajouter une contradiction");
			System.out.println("2) Fin");

			choix = sc1.nextInt();


			switch (choix) {
			case 1: System.out.println("Entre quels arguments voulez-vous ajouter une contradiction ? :");
					String arg1 = sc1.next();
					String arg2 = sc1.next();
					System.out.println();
		
					if(map.containsKey(arg1) && map.containsKey(arg2)) {
						g.ajouterContradictionGraphe(arg1, arg2);
						System.out.println("La contradiction entre " + arg1 + " et " + arg2 + " a �t� ajout�e.");
					}
					else if (!map.containsKey(arg1) && !map.containsKey(arg2)){
						System.out.println("Ces arguments n'existent pas. ");
					}
					else if(!map.containsKey(arg1)) {
						System.out.println("L'arguments "+ arg1+" n'existe pas. ");
					}
					else {
						System.out.println("L'arguments "+ arg2+" n'existe pas. ");
					}
			
			break;
			case 2: GrapheInitial.afficherArgContradiction();
					getMenuSolution();
					sc1.close();
			
			break;
			default: 
					System.out.println("Vous devez choisir entre 1 et 2 !");
			}
			
		}while(choix != 2);

	}



	/**
	 * Cette m�thode permet d'afficher le deuxi�me menu  
	 */
	public static void getMenuSolution() {
		do {
			System.out.println();
			System.out.println("----------------------- Voici le second menu -----------------------");
			System.out.println("1) Ajouter un argument à ma solution");
			System.out.println("2) Retirer un argument solution");
			System.out.println("3) Verifier la solution");
			System.out.println("4) Fin");

			choix = sc2.nextInt();

			switch(choix) {
			case 1: System.out.println("Quel est le nom de l'argument que vous voulez ajouter � votre solution ? (exemple : A3 )");
					argumentNom = sc2.next();
					if(map.containsKey(argumentNom)) { //Verifie si l'argument est bien dans le graphe
						if(s.contains(argumentNom)) { //Verifie si il n'a pas d�ja �t� ajout� � la solution
							System.out.println("L'argument " + argumentNom + " a déjà été ajoutée à votre solution !");
							solution.afficherSolution();
		
						}
						else {
							solution.ajouterSolution(argumentNom);
							solution.afficherSolution();
							getMenuSolution();
						}
					}
					else {
						System.out.println("Cet argument n'existe pas");
						GrapheInitial.afficherArgContradiction();
					}
			
			break;
			case 2: System.out.println("Quel est le nom de l'argument que vous voulez retirer de votre solution ? (exemple : A3 )");
					argumentNom = sc2.next();
					if(s.contains(argumentNom)) { //Verifie si l'argument est bien dans la solution
						solution.retirerSolution(argumentNom);
						System.out.println("L'argument " + argumentNom + " a été retiré.");
					}
					else {
						System.out.println("L'argument " + argumentNom + " n'était déjà pas dans votre solution !");
					}
					solution.afficherSolution();
			
			break;
			case 3: if(!solution.grapheAdmissible()) {
						System.out.println("Cette solution n'est pas admissible !");
					}
					else {
						System.out.println("Cette solution est admissible.");
					}
					solution.afficherSolution();
			
			break;
			case 4: System.out.println();
					System.out.println("============= RESULTAT =============");
					solution.afficherSolution();
					if(!solution.grapheAdmissible()) {
						System.out.println(" Cette solution n'est pas admissible !");
					}
					else {
						System.out.println(" Cette solution est admissible.");
					}
					sc2.close();
				
			break;
			default: System.out.println("Vous devez choisir entre 1, 2, 3 ou 4 !");
			
			}
		
		}while(choix != 4);

	}


	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		int nbrArg;

		System.out.println("Bonjour, combien d'arguments voulez-vous ?");
		nbrArg = sc.nextInt();
		System.out.println();

		for(int i = 1; i <= nbrArg; i++) {
			String nom="A"+i;
			g.ajouterArgumentGraphe(nom);
		}

		if(nbrArg < 2) {
			System.out.println("Vous ne pouvez pas encore ajouter de contradictions, car vous avez moins de deux arguments.");
			getMenuSolution();
		}
		else {
			getMenuContradiction();
		}

		sc.close();

	}

}
