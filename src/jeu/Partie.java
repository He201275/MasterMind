package jeu;

import java.util.Scanner;

public class Partie extends Mastermind {
	
	public void Partie(){
		String Essai;
		boolean gagnant = false;
		
    
	while (EssaiActuel <= nombreEssai) {
		 System.out.printf("Essai %d: ", EssaiActuel);
         Scanner input = new Scanner(System.in);
          Essai = input.nextLine();
     	
     	
	// Vérifie l'essai courant, et quitte si la correspondance est parfaite
	resultatEssai resultat = EssaiSoumis(Essai);
    
    if (resultat.isPerfectEssai()) {
        gagnant = true;
        
       break;
    }

    // Output the score, or a message, if either were provided
    if (!resultat.getScore().isEmpty()) {
    	essaiOk = true;
    	
        System.out.println(resultat.getScore());
        
    } //else if (!resultat.getMessage().isEmpty()) {
        //System.out.println(resultat.getMessage());
    //}
}
if(gagnant){
	System.out.println("Vous avez réussi! New play?");
	Scanner input = new Scanner(System.in);
	String newGame = input.nextLine();
	if (newGame.equalsIgnoreCase("y")){
		play();
	} else {
		System.out.println("Fin du game");
		System.exit(0);
	}
}

 EssaiActuel++;        	
}

System.out.println("Vous avez perdu! New play?");
Scanner input = new Scanner(System.in);
String newGame = input.nextLine();
if (newGame.equalsIgnoreCase("y")){

 play();
}
else{
System.out.println("Fin du game");
System.exit(0);


}

//String messageFinJeu = gagnant ? "Vous avez réussi!" : "Vous avez perdu :( Voulez-vous recommencer une partie?";
//System.out.println(messageFinJeu);


}
	

}
