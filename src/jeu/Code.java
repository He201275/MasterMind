package jeu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Code {
	
	/*
    Le code secret.
    Clé  = Une valeur du code
    Valeur = Une collection des indices pour laquelle cette valeur existe
 */
	
	HashMap<Integer, HashSet<Integer>> combinaison = new HashMap<Integer, HashSet<Integer>>();
	
	public Code(int maxValeurCode, int minValeurCode, int longueurCode, boolean duplicationAutorise){
		
		combinaison = generationCode(maxValeurCode, minValeurCode, longueurCode, duplicationAutorise);
	}
	
	/**
     * Génération du code secret
     */
    public HashMap generationCode(int maxValeurCode, int minValeurCode, int longueurCode, boolean duplicationAutorise  ) {
        if (!duplicationAutorise) {
            int TailleRangeCode = maxValeurCode - minValeurCode; //Détermine la longueur min du code
            while (TailleRangeCode < longueurCode) {
                Scanner input3 = new Scanner(System.in);
                System.out.println("L'écart des valeurs doit être plus grand que la longueur du code! La duplication des valeurs n'est pas autorisé.Veuillez introduire une longueur de code > au range des valeurs !");
                longueurCode = input3.nextInt();
            }
           
        }

        HashMap<Integer, HashSet<Integer>> tempCode = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < longueurCode; i++) {
            int nombre = ThreadLocalRandom.current().nextInt(minValeurCode, maxValeurCode + 1);
            if (!duplicationAutorise) {
                // Recalcule le nombre pour la position actuelle si il existe déjà afin d'éviter la duplication
                while (tempCode.containsKey(nombre)) {
                    nombre = ThreadLocalRandom.current().nextInt(minValeurCode, maxValeurCode + 1);
                }
            }

            HashSet<Integer> indices = tempCode.getOrDefault(nombre, new HashSet<>());
            indices.add(i);
            tempCode.put(nombre, indices);
        }
    return tempCode;
    }

	public HashMap<Integer, HashSet<Integer>> getCode() {
		return this.combinaison;
	}
    

}


