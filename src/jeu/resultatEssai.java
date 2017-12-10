package jeu;

/**
 * Classe utilisé pour s'occuper des résultats lié à la vérification des checks d'Essai
 */
public class resultatEssai {
    private String _valeurScore;
    private boolean _perfectEssai;
    private String _message;

    /**
     *Constructeur par défaut.
     */
    public resultatEssai() {
        _valeurScore = "";
        _perfectEssai = false;
        _message = "";
    }

    /**
     * Constructeur pour paramétrer uniquement un message.
     * @param message Message à inclure dans le résultat
     */
    public resultatEssai(String message) {
        this();
        _message = message;
    }

    /**
     * Constructeur pour paramétrer le score et si l'Essai courant est parfait
     * @param valeurScore un String score de pluses et minuses
     * @param essaiParfait si l'Essai est parfait ou non
     */
    public resultatEssai(String scoreValue, boolean perfectEssai) {
        this();
        _valeurScore = scoreValue;
        _perfectEssai = perfectEssai;
    }

    /**
     * Constructeur pour toutes les valeurs.
     * @param scoreValue un String score de pluses et minuses
     * @param perfectEssai si l'Essai est parfait ou non
     * @param message Message à inclure dans le résultat
     */
    public resultatEssai(String scoreValue, boolean perfectEssai, String message) {
        this(scoreValue, perfectEssai);
        _message = message;
    }

    /**
     * Get this resultats score string
     * @return un string Score de pluses et minuses
     */
    public String getScore(String Essai) {
        return _valeurScore;
    }

    /**
     * Get this resultats perfect Essai value
     * @return si l'Essai est parfait ou non 
     */
    public boolean isPerfectEssai() {
        return _perfectEssai;
    }

    /**
     * Get le message de resultat
     * @return Un message inclus avec le résultat
     */
    public String getMessage() {
        return _message;
    }
    
    /**
     * Evalue l'exactitude de l'essai soumis 
     * @param Essai le string soumis par le joueur
     * @return le resultat de l'évaluation de l'essai
    */
    public String EssaiSoumis(String Essai) {

        if (Essai.length() != longueurCode) {
            String errorMessage = String.format("l'essai doit avoir %d valeurs!", longueurCode);
            return errorMessage;
        }

        try {
            return getScore(Essai);
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format("Essai invalide : %s", e.getMessage());
            return errorMessage;
        }
       
    } 
}
