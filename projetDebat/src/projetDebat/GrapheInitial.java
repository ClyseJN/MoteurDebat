package projetDebat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class GrapheInitial {
	
	protected static HashMap<String, ArrayList<String>> grapheInitial;
	
	/**
	 * Constructeur qui initialise un objet Graphe Initial
	 * 
	 * @param grapheInitial
	 */
	public GrapheInitial(HashMap<String, ArrayList<String>> grapheInitial) {
		GrapheInitial.grapheInitial = grapheInitial;
	}
	
	/**
	 * Ajoute un argument au graphe initial
	 * 
	 * @param arg	l'argument à ajouter
	 */
	public void ajouterArgumentGraphe(String arg) {
		grapheInitial.put(arg, new ArrayList<String>());
	}
	
	/**
	 * Permet d'accéder à un argument du graphe initial
	 * 
	 * @return	un argument
	 */
	public HashMap<String, ArrayList<String>> getGrapheInitial(){
		return grapheInitial;
	}
	
	/**
	 * Ajouter une contradiction entre deux arguments
	 * 
	 * @param arg1	l'argument qui contredit
	 * @param arg2	l'argument contredit
	 */
	public void ajouterContradictionGraphe(String arg1, String arg2) {
		grapheInitial.get(arg1).add(arg2);
	}
	
	/**
	 * Cette méthode permet d'afficher le graphe initial :
	 * la liste des arguments et leurs contradictions
	 */
	public static void afficherArgContradiction() {
		System.out.println("Voici votre graphe initial :");
		for(Entry<String, ArrayList<String>> e : grapheInitial.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
	}
}
