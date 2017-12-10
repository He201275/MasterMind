package jeu;

import java.util.HashMap;
import java.util.HashSet;



/**
 *  Mastermind
 * Le joueur a un nombre d'essai limité pour trouver un code secret d'une certaine longueur crée par l'ordinateur
 */
public class Mastermind {
    protected boolean duplicationAutorise;
    protected int nombreEssai;
    protected int longueurCode;
    protected int minValeurCode;
    protected int maxValeurCode;
    protected int EssaiActuel;

    /*
    Le code secret.
    Clé  = Une valeur du code
    Valeur = Une collection des indices pour laquelle cette valeur existe
 */
    protected Code code;

    /**
     * Constructeur par défaut. Par défaut: duplication = false, longueur code = 4, Essais = 10, limite valeur = (1,6)
     * @param  
     */
    public Mastermind() {
        longueurCode = 4;
        minValeurCode = 1;
        maxValeurCode = 6;
        nombreEssai = 10;
        duplicationAutorise = false;
        EssaiActuel = 1;
        code = new Code(maxValeurCode, minValeurCode, longueurCode, duplicationAutorise);
    }

    /**
     * Constructeur pour paramétrer toutes les valeurs. Par de valeurs par défaut
     * @param nombreEssai nombre d'essai donné au joueur
     * @param longueurCode Longueur du code secret généré 
     * @param minValeurCode Valeur min des valeurs du code (inclusive)
     * @param maxCodeValue Valeur max des valeurs du code (inclusive)
     *  * @param duplicationAutorise Si la duplication est autorisé ou non
     */
    public Mastermind(int nombreEssai, int longueurCode, int minValeurCode, int maxValeurCode, int duplicationAutorise) {
        this.nombreEssai = nombreEssai;
        if (nombreEssai < 1) {
            throw new IllegalArgumentException("Au moins 1 essai est requis!");
        }
    	this.longueurCode= longueurCode ;
        if (!isValidCodenombre(minValeurCode) || !isValidCodenombre(maxValeurCode)) {
            throw new IllegalArgumentException("Les valeurs du code secret doivent être comprises entre 0 et 9!");
        }
        this.minValeurCode = minValeurCode;
        this.maxValeurCode = maxValeurCode;
        if (duplicationAutorise == 1) this.duplicationAutorise = true;
        else this.duplicationAutorise = false;
        this.EssaiActuel = 1;
    }
    
    /**
     * Vérifie si le nombre donné est une valeur valide du code.(dans la limite 0-9)
     * @param nombre le nombre a valider
     * @return True si le nombre est valide
     */
    public boolean isValidCodenombre(int nombre) {
        return nombre >= 0 && nombre <= 9;
    }
    
    
    
}
   
   

    