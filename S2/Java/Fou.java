public class Fou extends Pion {

    private String nom;

    public Fou(int li, int cl) {
        super(li, cl);
        this.nom="Fou";
    }

    // Vérifie si le déplacement du Fou est valide.
    // Le Fou peut se déplacer uniquement en diagonale, ce qui signifie que la différence absolue
    // entre les colonnes et les lignes doit être égale.
    public boolean deplacement_ok(int lig, int col) {
        int abvalcol = Math.abs(this.getColonne() - col); // Différence absolue entre les colonnes
        int abvalli = Math.abs(this.getLigne() - lig);    // Différence absolue entre les lignes
        return (abvalcol == abvalli); // Le déplacement est valide si les deux différences sont égales
    }

    // Effectue le déplacement du Fou si celui-ci est valide.
    // Si le déplacement est invalide, une exception est levée et un message d'erreur est affiché.
    public void mouvement(int li, int cl) {
        try {
            if (!this.deplacement_ok(li, cl)) { // Vérifie si le déplacement est valide
                throw new IllegalArgumentException(); // Lève une exception si le déplacement est invalide
            }

            // Gestion des déplacements en diagonale
            if (this.getColonne() < cl && this.getLigne() < li) {
                this.setLigne(this.getLigne() + 1); // Avance d'une case en diagonale haut-droite
                this.setColonne(this.getColonne() + 1);
            } else if (this.getColonne() > cl && this.getLigne() > li) {
                this.setLigne(this.getLigne() - 1); // Recule d'une case en diagonale bas-gauche
                this.setColonne(this.getColonne() - 1);
            } else if (this.getColonne() > cl && this.getLigne() < li) {
                this.setLigne(this.getLigne() + 1); // Avance d'une case en diagonale haut-gauche
                this.setColonne(this.getColonne() - 1);
            } else if (this.getColonne() < cl && this.getLigne() > li) {
                this.setLigne(this.getLigne() - 1); // Recule d'une case en diagonale bas-droite
                this.setColonne(this.getColonne() + 1);
            }

            System.out.println("Fou deplacement ok"); // Affiche un message de succès
        } catch (Exception e) {
            System.out.println("coup non-permis"); // Affiche un message d'erreur si le déplacement est invalide
        }
    }
    public String getNom() {
        return this.nom;
    }
}