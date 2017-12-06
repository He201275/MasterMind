package P1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  Mastermind
 * Le joueur a un nombre d'essai limité pour trouver un code secret d'une certaine longueur crée par l'ordinateur
 */
public class Mastermind {
    private boolean duplicationAutorise;
    private int nombreEssai;
    private int longueurCode;
    private int minValeurCode;
    private int maxValeurCode;
    private int EssaiActuel;

    /*
    Le code secret.
    Clé  = Une valeur du code
    Valeur = Une collection des indices pour laquelle cette valeur existe
 */
    private HashMap<Integer, HashSet<Integer>> code;

    /**
     * Constructeur par défaut. Par défaut: duplication = false, longueur code = 4, Essais = 10, limite valeur = (1,6)
     */
    public Mastermind() {
        longueurCode = 4;
        minValeurCode = 1;
        maxValeurCode = 6;
        nombreEssai = 10;
        duplicationAutorise = false;
        EssaiActuel = 1;
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
     * Début de boucle du jeu
     */
    public void play() {
        generationCode();

        System.out.println("Bienvenue dans MasterMind!");
        System.out.printf("Je pense à un code d'une longueur de %d , avec des nombres entre %d et %d.\n", longueurCode, minValeurCode, maxValeurCode);
        System.out.printf("La duplication de valeur est %s autorisé.\n", (duplicationAutorise ? " ": " non "));
        System.out.printf("Saurez-vous trouvez le code en  %d essais?\n", nombreEssai);

        boolean gagnant = false;
        Scanner input = new Scanner(System.in);
        while (EssaiActuel < nombreEssai) {
            System.out.printf("Essai %d: ", EssaiActuel);
            String Essai = input.nextLine();

            // Vérifie l'essai courant, et quitte si la correspondance est parfaite
            resultatEssai resultat = EssaiSoumis(Essai);
            if (resultat.isPerfectEssai()) {
                gagnant = true;
                break;
            }

            // Output the score, or a message, if either were provided
            if (!resultat.getScore().isEmpty()) {
                System.out.println(resultat.getScore());
            } else if (!resultat.getMessage().isEmpty()) {
                System.out.println(resultat.getMessage());
            }
        }

        String messageFinJeu = gagnant ? "Vous avez réussi!" : "Vous avez perdu :(";
        System.out.println(messageFinJeu);
    }

    /**
     * Génération du code secret
     */
    private void generationCode() {
        if (!duplicationAutorise) {
            int TailleRangeCode = maxValeurCode - minValeurCode; //Détermine la longueur min du code
            if (TailleRangeCode < longueurCode) {
                throw new RuntimeException("L'écart des valeurs doit être plus grand que la longueur du code! La duplication des valeurs n'est pas autorisé.");
            }
        }

        code = new HashMap<>();
        for (int i = 0; i < longueurCode; i++) {
            int nombre = ThreadLocalRandom.current().nextInt(minValeurCode, maxValeurCode + 1);
            if (!duplicationAutorise) {
                // Recalcule le nombre pour la position actuelle si il existe déjà afin d'éviter la duplication
                while (code.containsKey(nombre)) {
                    nombre = ThreadLocalRandom.current().nextInt(minValeurCode, maxValeurCode + 1);
                }
            }

            HashSet<Integer> indices = code.getOrDefault(nombre, new HashSet<>());
            indices.add(i);
            code.put(nombre, indices);
        }
    }

    /**
     * Evalue l'exactitude de l'essai soumis 
     * @param Essai le string essai soumis par le joueur
     * @return le resultat de l'évaluation de l'essai
    */
    private resultatEssai EssaiSoumis(String Essai) {
        EssaiActuel++;

        if (Essai.length() != longueurCode) {
            String errorMessage = String.format("l'essai doit avoir %d valeurs!", longueurCode);
            return new resultatEssai(errorMessage);
        }

        try {
            return getScore(Essai);
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format("Essai invalide : %s", e.getMessage());
            return new resultatEssai(errorMessage);
        }
    }

    /**
     * Note l'essai soumis
     * @param Essai le string soumis par le joueur
     * @return le resultat de l'essai soumis 
     */
    private resultatEssai getScore(String Essai) {
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
                int actualnombreCount = code.getOrDefault(nombre, new HashSet<>()).size(); // nombre de fois que le nombre est dans le code sur base du size du HashSet des indices du nombre
                if (actualnombreCount > 0 && nombreMatchCount == actualnombreCount) {
                    perfectEssai = false;
                    continue;
                }

                // Verifie la position de l'essai soumis et m-a-j le score en conséquence
                switch (checkValeurEssai(nombre, i)) {
                    case '+':
                        scorePluses++;
                        correspondance.put(nombre, correspondance.getOrDefault(nombre, 0) + 1); //The old value will be replaced by a new one if the key is already on the HashMap object.
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

        return new resultatEssai(getScoreString(scorePluses, scoreMinuses), perfectEssai);
    }

    /**
     * Vérifie si le nombre donné est une valeur valide du code.(dans la limite 0-9)
     * @param nombre le nombre a valider
     * @return True si le nombre est valide
     */
    private boolean isValidCodenombre(int nombre) {
        return nombre >= 0 && nombre <= 9;
    }

    /**
     * Checks if a given nombre is a valid Essai value in the context of the current game. (in the configured range)
     * @param nombre The nombre to validate
     * @return True if the nombre is valid
     */
    private boolean isValidEssainombre(int nombre) {
        return nombre >= minValeurCode && nombre <= maxValeurCode;
    }

    /**
     * Vérifie si le nombre donné est une valeur valide du code.(dans la limite 0-9)
     * @param nombre le nombre a valider
     * @return True si le nombre est valide
     */
    private char checkValeurEssai(int nombre, int index) {
        char score = '\0';

        if (code.containsKey(nombre)) { 
            HashSet<Integer> indices = code.get(nombre); // renvoi la valeur à laquelle la clé spécifié est lié ou null
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
    private String getScoreString(int pluses, int minuses) {
    	int longueurResultat = pluses + minuses;
        char[] score = new char[longueurResultat]; 
        Arrays.fill(score, 0, pluses, '+');
        Arrays.fill(score, pluses, score.length, '-');
        return new String(score);
    }

    /**
     * Classe utilisé pour s'occuper des résultats lié à la vérification des checks d'Essai
     */
    private class resultatEssai {
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
        public String getScore() {
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
    }
}
