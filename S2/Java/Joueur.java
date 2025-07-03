public class Joueur {
    private String nom;
    private String couleur;

    public Joueur(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }
    public Joueur(){
        this.nom="";
        this.couleur="";
    }

    public String getNom() {
        return this.nom;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void setNom(String nvNom){
        this.nom=nvNom;
    }
    public void setCouleur(String nvCouleur){
        this.couleur=nvCouleur;
    }

    public String toString() {
        return "le nom du joueur est: " + this.nom + "\n Sa couleur est: " + this.couleur;
    }
}
