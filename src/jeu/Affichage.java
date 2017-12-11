package jeu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;



public class Affichage extends Mastermind  {
	
	/**
     * Début de boucle du jeu
     */
	public void play() {
    	//public HashMap<Integer,HashSet<Integer>> generationCode();
    	
    	String Essai;
        //generationCode();
        int EssaiActuel = 1;
        System.out.println("Bienvenue dans MasterMind!");
        System.out.printf("Je pense à un code d'une longueur de %d , avec des nombres entre %d et %d.\n", longueurCode, minValeurCode, maxValeurCode);
        System.out.printf("La duplication de valeur est %s autorisé.\n", (duplicationAutorise ? " ": " non "));
        System.out.printf("Saurez-vous trouver le code en  %d essais?\n", nombreEssai);  	
	            
}
    
}