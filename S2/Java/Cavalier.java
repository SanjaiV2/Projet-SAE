public class Cavalier extends Pion {
    private String nom;

    public Cavalier(int li, int col) {
        super(li, col);
        this.nom="Cavalier";
    }

    // Vérifie si le déplacement du Cavalier est valide.
    public boolean deplacement_ok(int lig, int cl) {
        return (
            (lig == this.getLigne() + 2 && (cl == this.getColonne() + 1 || cl == this.getColonne() - 1))
            ||
            (lig == this.getLigne() - 2 && (cl == this.getColonne() + 1 || cl == this.getColonne() - 1))
            ||
            (cl == this.getColonne() + 2 && (lig == this.getLigne() + 1 || lig == this.getLigne() - 1))
            ||
            (cl == this.getColonne() - 2 && (lig == this.getLigne() + 1 || lig == this.getLigne() - 1))
        );
    }

    public void mouvement(int li, int cl) {
        try {
            if (!this.deplacement_ok(li, cl)) {
                throw new IllegalArgumentException();
            }

            this.setLigne(li);
            this.setColonne(cl);
            System.out.println("Cavalier deplacement ok");
        } catch (Exception e) {
            System.out.println("coup non-permis");
        }
    }
    public String getNom() {
        return this.nom;
    }
}