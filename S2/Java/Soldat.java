public class Soldat extends Pion {
    private String nom;

    public Soldat(int li, int col) {
        super(li, col);
        this.nom="Soldat";
    }

    // Vérifie si le déplacement du Soldat est valide.
    // Le Soldat peut avancer d'une ou deux cases verticalement sur la même colonne.
    public boolean deplacement_ok(int lig, int cl) {
        return (
            this.getLigne() == lig && // Le Soldat reste sur la même colonne
            (this.getColonne() + 1 == cl || this.getColonne() + 2 == cl
            ||
            this.getColonne()-1 ==cl || this.getColonne()-2==cl) // Le Soldat avance d'une ou deux lignes
        
            );
    }

    // Effectue le déplacement du Soldat si celui-ci est valide.
    // Si le déplacement est invalide, une exception est levée et un message d'erreur est affiché.
    public void mouvement(int li, int cl) {
        try {
            if (!this.deplacement_ok(li, cl)) { // Vérifie si le déplacement est valide
                throw new IllegalArgumentException(); // Lève une exception si le déplacement est invalide
            }
            this.setLigne(li); // Met à jour la ligne du Soldat
            this.setColonne(cl); // Met à jour la colonne du Soldat
            System.out.println("Soldat deplacement ok"); // Affiche un message de succès
        } catch (Exception e) {
            System.out.println("coup non-permis"); // Affiche un message d'erreur si le déplacement est invalide
        }
    }
    public String getNom() {
        return this.nom;
    }

}
