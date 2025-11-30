public abstract class Pion {
    private int ligne;
    private int colonne;
    private String couleur;

    public Pion(int laligne, int lacolonne) {
        this.ligne = laligne;
        this.colonne = lacolonne;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public void setLigne(int a) {
        this.ligne = a;
    }

    public void setColonne(int b) {
        this.colonne = b;
    }


    public String getCouleur() {
        return this.couleur;
    }

    public void setCouleur(String c) {
        this.couleur = c;
    }

    abstract String getNom();
    abstract boolean deplacement_ok(int li, int cl);

    abstract void mouvement(int li, int cl);

    public String toString() {
        return "la ligne est: " + this.ligne + "\nla colonne est: " + this.colonne;
    }
    
}