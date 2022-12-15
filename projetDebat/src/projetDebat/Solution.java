package projetDebat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Solution extends GrapheInitial{

	private ArrayList<String> solution;

	/**
	 * Constructeur qui initialise un objet Solution
	 * @param solution
	 */
	public Solution(ArrayList<String> solution) {
		super(grapheInitial);
		this.solution = solution;
	}

	/**
	 * Permet d'acc�der � la solution
	 * @return un argument
	 */
	public ArrayList<String> getSolution() {
		return solution;
	}

	public void ajouterSolution(String sol) {
		if(grapheInitial.containsKey(sol)) {
			solution.add(sol);
		}
	}

	public void retirerSolution(String sol) {
		solution.remove(sol);
	}

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
	 * Permet de v�rifier si une solution potentielle est bel et bien une solution admissible
	 *
	 * @param arguments l'essemble des arguments potentienllement solution admissible
	 * @return true si la solution est admissible, sinon false.
	 */
	public boolean grapheAdmissible() {
		boolean b = true;
		int i = 0;

		for(int y = 0; y < solution.size(); y++) {
			if(solution.size() > 1) {
				for(String k : grapheInitial.keySet()) {
					if(k != solution.get(i) && solution.contains(k)) {
						if(grapheInitial.get(k).contains(solution.get(y)) && grapheInitial.get(solution.get(y)).contains(k)) {
							return b=false;
						}
						else if(grapheInitial.get(k).contains(solution.get(y)) || grapheInitial.get(solution.get(y)).contains(k)) {
							return b=false;
						}

					}
				}
			}

			else if(solution.size() == 1) {
				for(Entry<String, ArrayList<String>> entry : grapheInitial.entrySet()) {
				//	String key = entry.getKey();
					ArrayList<String> value = entry.getValue();
					// if(solution.get(y) == key) {
					while(i < value.size()) {

						if(!grapheInitial.get(solution.get(y)).isEmpty() ) {
							if(!grapheInitial.get(grapheInitial.get(solution.get(y)).get(y)).contains(solution.get(y))
									&& !grapheInitial.get(value.get(i)).isEmpty()) {
								for(String k : grapheInitial.keySet())
									if(grapheInitial.get(k).contains(solution.get(y))) {
										return b=false;
									}

							}
							else if(grapheInitial.get(grapheInitial.get(solution.get(y)).get(y)).contains(solution.get(y))
									&& grapheInitial.get(solution.get(y)).contains(grapheInitial.get(solution.get(y)).get(y))) {
								if(!existeValeur(solution.get(y))) {
									return b=false;
								}

							}
							else if(grapheInitial.get(grapheInitial.get(solution.get(y)).get(y)).isEmpty()) {
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
		}

		return b;
	}


	/**
	 * M�thode qui permet d'afficher la solution entr�e par l'utilisateur :
	 * la liste des arguments et leurs contradictions
	 */
	public void afficherSolution() {
		System.out.println("Voici votre solution :");
		if(solution.size() == 0) {
			System.out.print("E = ");
		}
		else if(solution.size() == 1) {
			System.out.print("E = " + solution.get(0));
		}
		else {
			System.out.print("E = ");
			for(int i = 0; i < solution.size(); i++) {
				System.out.print(solution.get(i)+", ");
			}
		}

	}
}