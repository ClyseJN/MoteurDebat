package projetDebat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Cette classe represente un graphe
 * 
 * @author Clyse NZE
 *
 */

public class GrapheInitial {

	static HashMap <String, ArrayList<String>> grapheInitial;
	
	/**
	 * Constructeur qui initialise un objet Graphe Initial
	 * @param grapheInitial
	 */
	public GrapheInitial(HashMap <String, ArrayList<String>> grapheInitial) {
		GrapheInitial.grapheInitial = grapheInitial;
	}
	
	/**
	 * Permet d'acc�der � un argument du graphe initial
	 * @return un argument
	 */
	public HashMap <String, ArrayList<String>> getGrapheInitial(){
		return grapheInitial;
	}
	
	/**
	 * Ajoute un argument au graphe initial
	 * @param arg  l'argument � ajouter
	 */
	public void ajouterArgumentGraphe(String arg) {
		grapheInitial.put(arg, new ArrayList<String>());
	}
	
	/**
	 * Ajouter une contradiction entre deux arguments
	 * @param arg1 l'argument qui contredit
	 * @param arg2 l'argument contredit
	 */
	public void ajouterContradictionGraphe(String arg1, String arg2) {
			grapheInitial.get(arg1).add(arg2);
	}
	
	/**
	 * Cette m�thode permet d'afficher le graphe initial :
	 * la liste des arguments et leurs contradictions
	 */
	public static void afficherArgContradiction() {
        System.out.println("Voici votre graphe initial :");
        for (Entry<String, ArrayList<String>> entry : grapheInitial.entrySet()) {
        	String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    
    }
	
	/**
     * Methode qui lit un fichier texte et trie les arguments et les contradiction
     * 
     * @throws FileNotFoundException 
     *
     */
    public void fileManager(String fichier) throws FileNotFoundException {      
    	
    	File file = new File(fichier);    
    	FileReader fr = null;
    	
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			System.out.println("Le fichier est introuvable ! ");
			e1.printStackTrace();
		}  
		BufferedReader br = new BufferedReader(fr);  
		String line;
		try {
			while((line = br.readLine()) != null) {
				if(line.startsWith("argument")) {
					String arg = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					ajouterArgumentGraphe(arg);
				}
				if (line.startsWith("contradiction")) {
					String arg1 = line.substring(line.indexOf("(") +1 , line.indexOf(","));
			      	String arg2 = line.substring(line.indexOf(",") +1 , line.indexOf(")"));
			      	ajouterContradictionGraphe(arg1, arg2);
				}
			         
			}
		} catch (IOException e) {
			System.out.println("Le fichier ne respecte pas le mod�le d'un graphe pour un "
					+ "fichier texte");
			e.printStackTrace();
		}
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		    
		System.out.println("Voici vos arguments et contradictions :");
		afficherArgContradiction();
    }
	
}
