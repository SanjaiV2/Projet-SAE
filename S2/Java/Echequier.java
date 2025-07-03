import java.util.ArrayList;

public class Echequier {
    private ArrayList<Pion> blancs;
    private ArrayList<Pion> noirs;

    public Echequier() {
        blancs = new ArrayList<>();
        noirs = new ArrayList<>();
        initialiser();
    }

    /**
     * retourne la arrayList des pions blanc
     */
    public ArrayList<Pion> getPionBlancs() {
        return this.blancs;
    }

    /**
     * retourne la arrayList des pions noir
     */
    public ArrayList<Pion> getPionNoirs() {
        return this.blancs;
    }

    /**
     * remplace l'arraylist des blanc avec l'array en paramètre
     */
    public void setPionBlancs(ArrayList<Pion> nvBLancs) {
        this.blancs = nvBLancs;
    }

    /**
     * remplace l'arraylist des noirs avec l'array en paramètre
     */
    public void setPionNoirs(ArrayList<Pion> nvNoirs) {
        this.noirs = nvNoirs;
    }

    private void initialiser() {
        // voici les pions blancs
        blancs.add(new Tour(1, 1));
        blancs.add(new Tour(8, 1));
        blancs.add(new Cavalier(2, 1));
        blancs.add(new Cavalier(7, 1));
        blancs.add(new Fou(3, 1));
        blancs.add(new Fou(6, 1));
        blancs.add(new Dame(4, 1));
        blancs.add(new Roi(5, 1));
        for (int i = 1; i <= 8; i++) {
            blancs.add(new Soldat(i, 2));
        }

        // Définir la couleur des pions blancs
        for (Pion p : blancs) {
            p.setCouleur("blancs");
        }
        // les pion noir
        noirs.add(new Tour(1, 8));
        noirs.add(new Tour(8, 8));
        noirs.add(new Cavalier(2, 8));
        noirs.add(new Cavalier(7, 8));
        noirs.add(new Fou(3, 8));
        noirs.add(new Fou(6, 8));
        noirs.add(new Dame(4, 8));
        noirs.add(new Roi(5, 8));
        for (int i = 1; i <= 8; i++) {
            noirs.add(new Soldat(i, 7));
        }
        // Définir la couleur des pions noirs
        for (Pion p : noirs) {
            p.setCouleur("noirs");
        }

    }

    private Pion[][] lePlateau() {
        Pion[][] plateau = new Pion[10][10];
        // on va prendre tout les pion de l'arraylist de pion et le mettre dans un
        // plateau
        for (Pion p : blancs) {
            // on place au coordoné du pion p le pion p qui est declaré a chaque début de
            // boucle
            plateau[p.getLigne()][p.getColonne()] = p;
        }

        for (Pion p : noirs) {
            // on fait la même pour les pions noirs
            plateau[p.getLigne()][p.getColonne()] = p;
        }
        // a cette instant plateau comporte tout les pion mis sur le plateau
        return plateau;// il manque plus qu'a le retourner
    }

    /**
     * la methode existePion verifie si le pion mis en paramètre existe dans l'une
     * des arrayList
     */
    public Boolean existePion(Pion cepion) {
        for (Pion p : blancs) {
            if (p.getColonne() == cepion.getColonne() &&
                    p.getLigne() == cepion.getLigne())
                return true;
        }
        for (Pion p : noirs) {
            if (p.getColonne() == cepion.getColonne() &&
                    p.getLigne() == cepion.getLigne())
                return true;
        }
        return false;

    }

        /**
     * la methode existePion verifie si le pion mis en paramètre existe dans l'une
     * des arrayList
     */
    public Boolean existePionSurCase(int li,int col) {
        for (Pion p : blancs) {
            if (p.getColonne() == col &&
                    p.getLigne() == li)
                return true;
        }
        for (Pion p : noirs) {
            if (p.getColonne() == col &&
                    p.getLigne() == li)
                return true;
        }
        return false;

    }


    private ArrayList<Pion> pionsDeMemeCouleur(Pion p) {
        try{
            if (p.getCouleur().equals("blancs")) {
                return blancs;
            }
            if (p.getCouleur().equals("noirs")) {
                return noirs;
            }
            throw new IllegalArgumentException("Couleur de pion inconnue : " + p.getCouleur());
        }catch (IllegalArgumentException e){
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    /**
     * la methode getIndice retourne l'indice du pion mis en paramètre dans la liste
     * des pions de même couleur
     */
    public int getIndice(Pion p) {
        ArrayList<Pion> couleur = this.pionsDeMemeCouleur(p);
        int i = 0;
        for (Pion pion : couleur) {

            if (pion.getColonne() == p.getColonne() &&
                    pion.getLigne() == p.getLigne()) {
                return i;
            } else {
                i += 1;
            }
        }
        return -1;
    }

    /**
     * la methode trouvePion retourne le pion place sur l'echequier avec les
     * coordoné mise en parametre(ligne,colonne)
     */
    public Pion trouvePion(int li, int cl) {
        for (Pion p : blancs) {
            if (li == p.getLigne() &&
                    cl == p.getColonne()) {
                return p;
            }
        }
        for (Pion p : noirs) {
            if (li == p.getLigne() &&
                    cl == p.getColonne()) {
                return p;
            }
        }
        return null;
    }

    /**
     * la methode suppPion supprime la pion mis en parametre
     */
    public void suppPion(Pion p) {
        int i = getIndice(p);
        ArrayList<Pion> couleur = this.pionsDeMemeCouleur(p);
        couleur.remove(i);
    }
    private void mettreAJourPion(Pion p,Pion pcible){
        ArrayList<Pion> couleur = this.pionsDeMemeCouleur(p);
        if(couleur==null){
            System.out.println("Pion non trouvé dans la liste des pions de même couleur.");
            return;
        }
        int index = this.getIndice(p);
        if (index != -1) {
            couleur.set(index, pcible);
        }else{
            System.out.println("Pion non trouvé dans la liste.");
        }
    }


    /**
     * Simule le déplacement d'un pion et retourne les coordonnées de la case cible.
     * Ne modifie pas les coordonnées actuelles du pion.
     * elle a été creer pout ma méthode bouger_pion
     */
    private int[] simulerDeplacement(Pion pion, int li2, int cl2) {
        // Vérifie si le déplacement est valide
        if (!pion.deplacement_ok(li2, cl2)) {
            System.out.println("Déplacement invalide.");
            return null;
        }

        // Retourne les coordonnées de la case cible
        return new int[] { li2, cl2 };
    }

    /**
     * la methode bouger pion trouve le pion des deux premier parametre et le bouge
     * au coordonee des deux dernier paramètre
     */
    public void bouger_pion(int li1, int cl1, int li2, int cl2) {
        Pion pion = this.trouvePion(li1, cl1);
        Pion pioninit=pion;
        // on trouve le pion à bouger
        if (pion == null) {
            throw new IllegalArgumentException("Erreur : le pion n'existe pas à la position (" + li1 + ", " + cl1 + ").");
        }
        if (!pion.deplacement_ok(li2, cl2)) {
            throw new IllegalArgumentException("Erreur : le déplacement de (" + li1 + ", " + cl1 + ") à (" + li2 + ", " + cl2 + ") n'est pas valide.");
        }
        // on vérifie si le pion existe
        while (pion.getLigne() != li2 && pion.getColonne() != cl2) {
            int nbdepiontuez = 0;
            int[] coordonnees = this.simulerDeplacement(pion, li2, cl2);
            if (trouvePion(coordonnees[0], coordonnees[1]) != null) {
                Pion pionCible = trouvePion(coordonnees[0], coordonnees[1]);
                if (pionCible != null) {
                    // Si le pion cible est de la même couleur ou que le pion est un soldat
                    if (pionCible.getCouleur().equals(pion.getCouleur())) {
                        throw new IllegalArgumentException("Erreur : déplacement invalide. La case cible est occupée par un pion de la même couleur ou le pion est un soldat.");
                    } else {
                        if (nbdepiontuez > 0) {
                            throw new IllegalArgumentException("Erreur : ce pion ne peut pas capturer un autre pion.");
                        } else {
                            // Si le pion cible est de couleur différente, on le capture
                            this.suppPion(pionCible);
                        }
                    }
                }
            }
            // Si le pion cible est vide, on effectue le déplacement
            pion.mouvement(li2, cl2);
        }
        // Mettre à jour la position du pion dans la liste
        this.mettreAJourPion(pioninit,pion);
        System.out.println("Déplacement effectué : de (" + li1 + ", " + cl1 + ") à (" + li2 + ", " + cl2 + ").");
        System.out.println("Pion bougé avec succès.");
    }

    /**
     * La méthode toString permet d'afficher l'état de l'échiquier
     * comme si le joueur blanc regardait l'échiquier.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // on crée un tableau pour afficher l'état de l'échiquier
        Pion[][] plateau = this.lePlateau();
        // il nous manque plus qu'à afficher cela de façon à être compris
        for (int i = 8; i >= 1; i--) { // Les lignes sont affichées de haut en bas
            // pour commencer afficher la ligne
            sb.append(i).append("  "); // Affiche le numéro de la ligne
            // pour un affichage propre on va ajouter quelques espaces
            // on peut passer aux colonnes
            for (int j = 1; j <= 8; j++) {
                Pion cepion = plateau[i][j];
                // cepion prend la case que l'on veut afficher
                // ainsi nous devons voir si elle est null ou remplie pour pouvoir l'afficher
                if (cepion == null) {
                    sb.append("-   "); // Case vide
                } else {
                    // si elle n'est pas null alors il suffit d'afficher le pion qui correspond à
                    // cette case
                    // pour cela nous allons prendre l'initiale du nom de la classe + B si c'est blanc
                    // ou N si c'est noir
                    char premierlettre = cepion.getClass().getSimpleName().charAt(0); // getclass() affiche 'class Cavalier'
                    // et simpleName() seulement 'Cavalier' et pour prendre la première lettre nous
                    // prenons charAt('0').
                    // le souci est de savoir si le pion est blanc ou noir
                    // pour cela nous allons voir si le pion est dans l'arraylist de blanc sinon il
                    // est forcément dans noir
                    boolean estBlanc = blancs.contains(cepion);
                    // il suffit d'afficher tous les pions.
                    String lettreCouleur;
                    if (estBlanc) {
                        lettreCouleur = "B";
                    } else {
                        lettreCouleur = "N";
                    }
                    sb.append(premierlettre).append(lettreCouleur).append("  "); // toujours mettre des espaces pour aérer
                }
            }
            sb.append('\n');
            // cette ligne permet de revenir à la ligne
        }
        // il ne manque plus qu'à afficher les lettres en dessous    
        sb.append("   A   B   C   D   E   F   G   H\n");

        return sb.toString();

    }

}

