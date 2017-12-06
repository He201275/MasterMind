package P1;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
	public static int[] Configuration(){
		Scanner input2 = new Scanner(System.in);
		int[] parametres = new int[5];
			
		System.out.println("Entrez le nombre d'essai");
		parametres[0] = input2.nextInt();
		
		System.out.println("Entrez la longueur du code");
		parametres[1] = input2.nextInt();
		
		System.out.println("Entrez la valeur minimum");
		parametres[2] = input2.nextInt();
		
		System.out.println("Entrez la valeur maximum");
		parametres[3] = input2.nextInt();
		
		System.out.println("Autoriser la duplication? (Oui = 1 // Non = 0)");
		parametres[4] = input2.nextInt();
		
		return parametres;
	}
   
    public static void main(String[] args) {
    
    	
    	System.out.println("Voulez-vous configurer les paramètres de votre partie? y/n");
    		Scanner input = new Scanner(System.in);
    			String choix = input.nextLine();
    				
    				if (choix.equalsIgnoreCase("y")){
    					int[] parametres = Configuration();
    					int nombreEssai = parametres[0];
    					int longueurCode = parametres[1];
    					int minValeurCode = parametres[2];
    					int maxValeurCode = parametres[3];
    					int duplicationAutorisee = parametres[4];
    					Mastermind game = new Mastermind(nombreEssai, longueurCode, minValeurCode, maxValeurCode, duplicationAutorisee);
    					game.play();
    					
    		
    	}
    				
    	else {
    		Mastermind game = new Mastermind();
    		game.play();
    	}
    }
}
