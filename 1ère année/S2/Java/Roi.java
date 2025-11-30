public class Roi extends Pion {

    private String nom;

    public Roi(int li, int cl) {
        super(li, cl);
        this.nom="Roi";
    }

    // Vérifie si le déplacement du Roi est valide selon les règles du jeu d'échecs.
    // Le Roi peut se déplacer d'une case dans n'importe quelle direction (horizontal, vertical ou diagonal).
    public boolean deplacement_ok(int lig, int col) {
        return (
            (this.getLigne() + 1 == lig || this.getLigne() - 1 == lig) && this.getColonne() == col // Déplacement vertical
            || (this.getColonne() + 1 == col || this.getColonne() - 1 == col) && this.getLigne() == lig // Déplacement horizontal
            || (this.getLigne() + 1 == lig && this.getColonne() + 1 == col) // Déplacement diagonal haut-droite
            || (this.getLigne() + 1 == lig && this.getColonne() - 1 == col) // Déplacement diagonal haut-gauche
            || (this.getLigne() - 1 == lig && this.getColonne() + 1 == col) // Déplacement diagonal bas-droite
            || (this.getLigne() - 1 == lig && this.getColonne() - 1 == col) // Déplacement diagonal bas-gauche
        );
    }

    // Effectue le déplacement du Roi si celui-ci est valide.
    // Si le déplacement est invalide, une exception est levée et un message d'erreur est affiché.
    public void mouvement(int li, int cl) {
        try {
            if (!this.deplacement_ok(li, cl)) { // Vérifie si le déplacement est valide
                throw new IllegalArgumentException(); // Lève une exception si le déplacement est invalide
            }
            this.setLigne(li); // Met à jour la ligne du Roi
            this.setColonne(cl); // Met à jour la colonne du Roi
            System.out.println("Roi deplacement ok"); // Affiche un message de succès
        } catch (Exception e) {
            System.out.println("coup non-permis"); // Affiche un message d'erreur si le déplacement est invalide
        }
    }
    public String getNom() {
        return this.nom;
    }
}