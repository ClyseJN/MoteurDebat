package projetDebat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Cette classe représente la listes des solutions admissibles et
 * des solutions préférées. C'est un classe fille de GrapheInitial 
 * 
 * @author Clyse NZE
 *
 */
public class Solution extends GrapheInitial{
	
	private ArrayList<String> solution = new ArrayList<String>();
	private ArrayList<String> solutionAffichee = new ArrayList<String>();
	private ArrayList<ArrayList<String>> solAdmissible;
	private ArrayList<ArrayList<String>> solPreferee = new ArrayList<>();
	private int comptAdm = 0;
	private int comptPrf = 0;
	
	/**
	 * Constructeur de la classe
	 * 
	 * @param solution
	 */
	public Solution(ArrayList<String> solution, ArrayList<ArrayList<String>> solAdmissible, ArrayList<ArrayList<String>> solPreferee,
			ArrayList<String> solutionAffichee, int comptAdm, int comptPrf) {
		super(grapheInitial);
		this.solAdmissible = solAdmissible;
		this.comptAdm = comptAdm;
		this.comptPrf = comptPrf;
	}

	/*
	 * Constructeur de la classe surcharg�e
	 * 
	 * @param solAdmissible
	 */
	public Solution(ArrayList<ArrayList<String>> solAdmissible) {
		super(grapheInitial);
		this.solAdmissible = solAdmissible;
	}

	public ArrayList<ArrayList<String>> getSolAdmissible() {
		return solAdmissible;
	}

	
	/**
	 * Permet d'acc�der � la solution
	 * @return un argument
	 */
	public ArrayList<String> getSolution() {
		return solution;
	}
	
	/**
	 * Permet d'acc�der � la solution pr�f�r�e
	 * @return la liste des solutions pr�f�r�es
	 */
	public ArrayList<ArrayList<String>> getSolPreferee() {
		return solPreferee;
	}
	
	/**
	 * Permet d'acc�der � la solution affich�e 
	 * @return  la solution affich�e
	 */
	public ArrayList<String> getSolutionAffichee() {
		return solutionAffichee;
	}
	
	/**
	 * Permet de savoir si l'utilisateur a choisit l'option 1 du menu
	 * @return le compteur de l'option 1 du menu
	 */
	public int getComptAdm() {
		return comptAdm;
	}
	
	/**
	 * Permet de savoir si l'utilisateur a choisit l'option 2 du menu
	 * @return le compteur de l'option 2 du menu
	 */
	public int getComptPrf() {
		return comptPrf;
	}

	/**
	 * Permet d'incrementer le compteur de l'option 1
	 */
	public void incrementerComptAdm() {
		comptAdm++;
	}
	
	/**
	 * Permet d'incrementer le compteur de l'option 2
	 */
	public void incrementerComptPrf() {
		comptPrf++;
	}
	
	/**
	 * Permet d'ajouter un argument � la liste des solutions
	 * @param sol
	 */
	public void ajouterSolution(String sol) {
		if(grapheInitial.containsKey(sol)) {
				solution.add(sol);
		}
	}
	
	/**
	 * Permet de retirer un argument � la liste des solutions
	 * @param sol
	 */
	public void retirerSolution(String sol) {
		solution.remove(sol);
	}
	
	/**
	 * Methode qui permet de voir si un argument est contredit par un autre argument dans le graphe
	 * @param arg
	 * @return
	 */
	public boolean existeValeur(String arg) {
		boolean b=true;
		for(String key : grapheInitial.keySet()) {
			if(grapheInitial.get(key).contains(arg) && !grapheInitial.get(arg).contains(key)) {
				return b=false;
			}
		}
		return b;
	}
	
	/**
	 * M�thode qui permet de voir si un argument se d�fend lui m�me contre tous ceux qui le contredisent
	 * @param arg
	 * @return
	 */
	public boolean seDefendLuiMeme(String arg1, String arg2) {
		boolean b=true;
		if(!grapheInitial.get(arg1).contains(arg2)
				&& !grapheInitial.get(arg1).isEmpty()) {
			for(String k : grapheInitial.keySet()) { 
				if(grapheInitial.get(k).contains(arg2)) {
					return b=false;
				}
			}
		}
		else if(grapheInitial.get(arg1).contains(arg2)
				&& grapheInitial.get(arg2).contains(arg1)) {
			if(!existeValeur(arg2)) {
				return b=false;
			}
			
		}
		else if(grapheInitial.get(arg1).isEmpty()) {
			return b=false;
		}
		return b;
	}
	
	/**
	 * M�thode qui permet de voir si un argument se d�fend lui m�me contre tous ceux qui le contredisent
	 * @param arg
	 * @return
	 */
	public boolean seDefendLuiMeme(String arg) {
		boolean b=true;
		for(String key : grapheInitial.keySet()) {
			if(grapheInitial.get(key).contains(arg) && !grapheInitial.get(arg).contains(key)) {
				return b=false;
			}
		}
		return b;
	}
	
	/**
	 * Methode qui permet de savoir si l'arg1 d�fend l'arg2
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public boolean ilLeDefend(String arg1, String arg2) {
		boolean b = false;
		int i = 0;
		for(Entry<String, ArrayList<String>> entry : grapheInitial.entrySet()) {
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			if(key == arg1) {
				while(i < value.size()) {
					for(String k : grapheInitial.keySet()) {
						if(k == value.get(i)) {
							if(grapheInitial.get(k).contains(arg2)) {
								return b=true;
							}
						}
					}
				}
			}
		}
		return b;
	}
	
	/**
	 * M�thode qui permet de v�rifier si deux arguments se contredisent
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public boolean neSeContredisentPas(String arg1, String arg2) {
		boolean b = true;
		if(grapheInitial.get(arg1).contains(arg2) && grapheInitial.get(arg2).contains(arg1)){
			return b=false;
		}
		else if(grapheInitial.get(arg1).contains(arg2) || grapheInitial.get(arg2).contains(arg1)) {
			return b=false;
		}
		return b;
	}

	/**
	 * Permet de verifier si une solution de grande taille est admissible
	 * @param solution
	 * @return boolean
	 */
	public boolean solutionAdm(ArrayList<String> solution) {
		boolean b = true;
		for(int y = 0; y < solution.size(); y++) {
			for(String k : grapheInitial.keySet()) {
				if(k != solution.get(y) && solution.contains(k)) {
					if(!neSeContredisentPas(k, solution.get(y))) { 
						return b=false;
					}
				}
			}
		}
		return b;
	}
	
	
	/**
	 * Permet de verifier si une solution de taille 1 est admissible
	 * @param solution
	 * @return boolean
	 */
	public boolean solutionAdmTaille1(ArrayList<String> solution) {
		boolean b = true;
		int i = 0;
		for(int y = 0; y < solution.size(); y++) {
			for(Entry<String, ArrayList<String>> entry : grapheInitial.entrySet()) {
	            ArrayList<String> value = entry.getValue();
	            while(i < value.size()) {
	            	if(!grapheInitial.get(solution.get(y)).isEmpty() ) {
	            		if(!seDefendLuiMeme(grapheInitial.get(solution.get(y)).get(y), solution.get(y))) {
	            			return b=false;
	            		}
	            	}
	            	else if(grapheInitial.get(solution.get(y)).isEmpty()){
	            		if(!existeValeur(solution.get(y)))
	            			return b=false;		            			
	            	}
	            	i++;
	            }
			}
		}
		return b;
	}
	
	/**
	 * Permet de v�rifier si une solution potentielle est bel et bien une solution admissible
	 * 
	 * @param arguments l'essemble des arguments potentienllement solution admissible
	 * @return true si la solution est admissible, sinon false.
	 */
	public boolean solutionPotentielleAdmissible() {
		boolean b = true;
		if(solution.size() > 1) {
			if(!solutionAdm(solution)) {
				return b=false;
			}
		}
		else if(solution.size() == 1) {
			if(!solutionAdmTaille1(solution)) {
				return b=false;
			}
		}	
		return b;
	}
	
	/**
	 * Permet d'ajouter les solutions admissibles de taille 1 dans la liste
	 * solAdmissible 
	 */
	public void ajouterPremsSol() {
		//solution = new ArrayList<>();
		for(String key : grapheInitial.keySet()) {
			solution = new ArrayList<>();
			solution.add(key);
			if(seDefendLuiMeme(key)) {
				solAdmissible.add(solution);			
			}
		}
	}
	
	/**
	 * Permet d'ajouter la solution vide de taille 1 dans la liste
	 * solAdmissible 
	 */
	public void ajouterSolVide() {
		ArrayList<String> sol1 = new ArrayList<String>();
		sol1.add("ensemble vide");
		solAdmissible.add(sol1);
	}
	
	/**
	 * Permet d'ajouter les solutions admissibles de grande taille dans la liste
	 * solAdmissible 
	 */
	public void chercherSolutionAdmissible() {
		ajouterSolVide();
		ajouterPremsSol();

		for(String key : grapheInitial.keySet()) {
			solution = new ArrayList<>();
			ajouterSolution(key);
			for(Entry<String, ArrayList<String>> entry : grapheInitial.entrySet()) {
				if(key != entry.getKey()) {
					if(!solution.contains(entry.getKey())) {
						key = entry.getKey();
						ajouterSolution(key);
						
						if(solutionAdm(solution)) {
							solAdmissible.add(solution);
						}
					}solAdmissible.add(solution);
					
					
					
				}	
			}
		}
		supprimerDoublons();
		}
	
	/**
	 * Permet de supprimer les doublons dans solAdmissible
	 */
	public void supprimerDoublons() {
		for(int i = 0; i < solAdmissible.size()-1; i++) {
			int y = 0;
			while(y < solAdmissible.size()) {
				if(solAdmissible.get(i) == solAdmissible.get(y) && y != i) {
					solAdmissible.remove(y);
				}
				y++;
			}		
		}
	}
	
	/**
	 * Permet de recuperer une solution admissible en fonction du compteur
	 * comptAdm
	 */
	public void recupererSolAdmAffichee() {
		chercherSolutionAdmissible();
		
		if(comptAdm <= solAdmissible.size()) {
			if(!solutionAffichee.isEmpty()) {
				solutionAffichee.clear();
			}
			for(int i =0; i < solAdmissible.size(); i++) {
				if(comptAdm == i) {
					solutionAffichee.addAll(solAdmissible.get(i));
				}
			}
			incrementerComptAdm();
		}
		else if(comptAdm > solAdmissible.size()) {
			int compter = comptAdm;
			while(compter > solAdmissible.size()) {
				compter = compter - solAdmissible.size();
			}
			if(!solutionAffichee.isEmpty()) {
				solutionAffichee.clear();
			}
			for(int i =0; i < solAdmissible.size(); i++) {
				if(compter == i) {
					solutionAffichee.addAll(solAdmissible.get(i));
				}
			}
			incrementerComptAdm();
		}
	}
	
	/**
	 * Permet d'afficher une solution admissible
	 */
	public void afficherSolutionAdm() {
		recupererSolAdmAffichee();
		System.out.println();
		System.out.println("Voici une solution admissible :");
		for(int i =0; i < solutionAffichee.size(); i++) {
			System.out.print(solutionAffichee.get(i));
			if(i < solutionAffichee.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}
	
	/**
	 * Permet de recuperer une solution pr�f�r�e en fonction du compteur
	 * comptPrf
	 */
	public void recupererSolPrfAffichee() {
		solPref();
		
		if(comptPrf <= solPreferee.size()) {
			if(!solutionAffichee.isEmpty()) {
				solutionAffichee.clear();
			}
			for(int i =0; i < solPreferee.size(); i++) {
				if(comptPrf == i) {
					solutionAffichee.addAll(solPreferee.get(i));
				}
			}
			incrementerComptPrf();
		}
		else if(comptPrf > solPreferee.size()) {
			int compter = comptPrf;
			while(compter >= solPreferee.size()) {
				compter = compter - solPreferee.size();
			}
			if(!solutionAffichee.isEmpty()) {
				solutionAffichee.clear();
			}
			for(int i =0; i < solPreferee.size(); i++) {
				if(compter == i) {
					solutionAffichee.addAll(solPreferee.get(i));
				}
			}
			incrementerComptPrf();
		}
		//afficherSolutionPrf();
	}
	
	/**
	 * Permet d'afficher une solution pr�f�r�e
	 */
	public void afficherSolutionPrf() {
		recupererSolPrfAffichee();
		System.out.println();
		System.out.println("Voici une solution pr�f�r�e :");
		for(int i =0; i < solutionAffichee.size(); i++) {
			System.out.print(solutionAffichee.get(i));
			if(i < solutionAffichee.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}
	
	/**
	 * Permet de verifier si souslist est contenu dans list
	 * @param souslist
	 * @param list
	 * @return boolean
	 */
	public boolean sousListe(ArrayList<String> souslist, ArrayList<String> list) {
		  if (souslist.size() > list.size()) {
		    return false;
		  }
		  for (int i = 0; i <= list.size() - souslist.size(); i++) {
		    if (list.subList(i, i + souslist.size()).equals(souslist)) {
		      return true;
		    }
		  }
		  return false;
		}
	
	
	/**
	 * Permet de v�rifier si une liste dans solAdmissible est contenu
	 * dans une autre liste dans solAdmissible
	 * @param list
	 * @return
	 */
	public boolean NestPascontenu(ArrayList<String> list) {
		boolean b = true;
			for ( ArrayList<String> subLst : solAdmissible) {
				if(list != subLst) {
					if(sousListe(list,subLst)) {
						b=false;
						break;
					
					}
				}
			}
		return b;
		
	}
	
	/**
	 * Permet d'ajouter les solution pr�f�r�e dans solPreferee
	 */
	public void solPref() {
		chercherSolutionAdmissible();
		solPreferee = new ArrayList<>();
		for (int i = 0; i < solAdmissible.size(); i++) {
			if(NestPascontenu(solAdmissible.get(i))) {
				solPreferee.add(solAdmissible.get(i));
			}
		}
		if(solPreferee.size() > 1) {
			solPreferee.remove(0);
		}
		
	}
	
	 /**
     * Methode qui creer un nouveau fichier texte qui affiche la derni�re
     * solution affich�e
     * 
     *@param fichier
     */
    public void sauvegarder(String fichier){
    	try(PrintWriter output  = new PrintWriter (new BufferedWriter(new FileWriter(fichier)))) {
    		for (int i = 0; i < solutionAffichee.size(); i++) {
    			output.print(solutionAffichee.get(i));
    			if(i < solutionAffichee.size()-1) {
    				output.print(",");
    			}
    		}
    		System.out.println("Le fichier \"" + fichier + "\" a bien ete enregistre.");
    	}catch (IOException erreur) {
    		System.err.println(erreur.getMessage());
    		System.exit(1);
    	}
     }
}