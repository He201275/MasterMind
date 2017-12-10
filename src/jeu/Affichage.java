package jeu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;



public void Affichage extends Mastermind  {
	public HashMap<Integer,HashSet<Integer>> generationCode();
	
	String Essai;
	/**
     * Début de boucle du jeu
     */
    public String play() {
        //generationCode();
        int EssaiActuel = 1;

        System.out.println("Bienvenue dans MasterMind!");
        System.out.printf("Je pense à un code d'une longueur de %d , avec des nombres entre %d et %d.\n", longueurCode, minValeurCode, maxValeurCode);
        System.out.printf("La duplication de valeur est %s autorisé.\n", (duplicationAutorise ? " ": " non "));
        System.out.printf("Saurez-vous trouver le code en  %d essais?\n", nombreEssai);
        System.out.println(code);
        

       
        	
	           
	
	             
	            
}
    
}