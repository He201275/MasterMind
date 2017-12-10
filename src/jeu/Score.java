package jeu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Score extends Partie {
	
	public String getScore(String Essai, Code code) {
		
        int scorePluses = 0;
        int scoreMinuses = 0;
        boolean perfectEssai = true;

        // Suis le nombre de correspondance pour chaque valeur, afin de prévenir le double marquage
        HashMap<Integer, Integer> correspondance = new HashMap<>();
        for (int i = 0; i < Essai.length(); i++) {
            char valeurEssai = Essai.charAt(i); // contient la valeur à la position i de l'essai soumis par le joueur
            int nombre = Character.getNumericValue(valeurEssai); //converti le char qui représente un chiffre en sa valeur numérique
            if (isValidEssainombre(nombre)) {
               
                int nombreMatchCount = correspondance.getOrDefault(nombre, 0);  // nombre de fois que le nombre a été testé
                int actualnombreCount = code.getCode().getOrDefault(nombre, new HashSet<>()).size(); // nombre de fois que le nombre est dans le code sur base du size du HashSet des indices du nombre
                if (actualnombreCount > 0 && nombreMatchCount == actualnombreCount) {
                    perfectEssai = false;
                    continue;
                }

                // Verifie la position de l'essai soumis et m-a-j le score en conséquence
                switch (checkValeurEssai(nombre, i)) {
                    case '+':
                        scorePluses++;
                        correspondance.put(nombre, correspondance.getOrDefault(nombre, 0) + 1); 
                        break;
                    case '-':
                        scoreMinuses++;
                        correspondance.put(nombre, correspondance.getOrDefault(nombre, 0) + 1);
                        // Fallthrough intentional
                    default:
                        perfectEssai = false;
                }
            } else {
                throw new IllegalArgumentException(String.format("Les valeurs de l'essai doivent être comprises entre %d et %d!", minValeurCode, maxValeurCode));
            }
        }

        return getScoreString(scorePluses, scoreMinuses,perfectEssai);

}
	
	/**
     * Vérifie si le nombre donné est une valeur valide du code.(dans la limite 0-9)
     * @param nombre le nombre a valider
     * @return True si le nombre est valide
     */
    public boolean isValidCodenombre(int nombre) {
        return nombre >= 0 && nombre <= 9;
    }

    /**
     * Checks if a given nombre is a valid Essai value in the context of the current game. (in the configured range)
     * @param nombre The nombre to validate
     * @return True if the nombre is valid
     */
    public boolean isValidEssainombre(int nombre) {
        return nombre >= minValeurCode && nombre <= maxValeurCode;
    }
    
    /**
     * Vérifie si le nombre donné est une valeur valide du code.(dans la limite 0-9)
     * @param nombre le nombre a valider
     * @return True si le nombre est valide
     */
    public char checkValeurEssai(int nombre, int index) {
        char score = '\0';

        if (code.getCode().containsKey(nombre)) { 
            HashSet<Integer> indices = code.getCode(nombre); // renvoie la valeur à laquelle la clé spécifiée est liée ou null
            if (indices.contains(index)) { 
                score = '+';
               
            } else {
                score = '-';
            }
        }

        return score;
    }

    /**
     * Construit un string composé de pluses et minuses
     * @param pluses nombre de pluses à inclure dans le  string
     * @param minuses nombre de minuses à inclure dans le string
     * @return Un string score composé de '+' et '-' 
     */
    public String getScoreString(int pluses, int minuses) {
    	int longueurResultat = pluses + minuses;
        char[] score = new char[longueurResultat]; 
        Arrays.fill(score, 0, pluses, '+');
        Arrays.fill(score, pluses, score.length, '-');
        return new String(score);
    } 
}
