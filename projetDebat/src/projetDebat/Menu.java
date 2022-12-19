package projetDebat;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Cette classe represente un menu
 * 
 * @author Clyse NZE
 */
public class Menu {
	
	static String fichier;
	static int choix;
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	
	//map qui correspond au graphe
	static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	static GrapheInitial graphe = new GrapheInitial(map);
	
	//liste qui correspond � la solution
	static ArrayList<ArrayList<String>> solAdmissible= new ArrayList<ArrayList<String>>();
	static Solution solution = new Solution(solAdmissible);
	
   
	/**
	 * Cette m�thode permet d'afficher le menu principal
	 */
	public static void affichageMenu() {
		System.out.println("----------------------- Voici le menu -----------------------");
		System.out.println("1) chercher une solution admissible;");
		System.out.println("2) chercher une solution pr�f�r�e;");
		System.out.println("3) sauvegarder la solution;");
		System.out.println("4) fin.");
	}
	
	/**
	 * Cette m�thode permet d'afficher les arguments du graphe recupere et leurs contradictions
	 */
	public static void afficherArgContradiction() {
		System.out.println();
        System.out.println("Voici vos arguments et les contradictions :");
        for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }
	
	/**
	 * M�thode qui permet de retourner le menu et d'executer les commandes
	 */
	public static void getMenuFichier() {
		do {
			affichageMenu();
			choix = sc1.nextInt();
			
			switch(choix) {
			case 1 :
				solution.afficherSolutionAdm();
				break;
		
			case 2 :
				solution.afficherSolutionPrf();
				break;
		
			case 3 :
				if(solution.getComptAdm() == 0 && solution.getComptPrf() == 0) {
					System.out.println("Pour sauvegarder une solution, il faut que l�option 1"
							+ " ou l�option 2 ait \n�t� choisie au moins une fois avant !");
				}
				else {
					System.out.println("Saisissez le chemin de votre fichier pour sauvegarder la solution :");
					fichier = sc2.next();
					solution.sauvegarder(fichier);
				}
				break;
			
			case 4 :
				System.out.println("Fin du programme");
				break;
			
			default :
				System.out.println("Attention votre entr�e n'est pas valide");
			}
		
		}while(choix != 4);
		sc1.close();
		sc2.close();
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entrez le chemin vers votre fichier :");
		String fichier = sc.nextLine();
		graphe.fileManager(fichier);
		
		System.out.println(); 
		
		getMenuFichier();
		
		sc.close();
	}

}
