import java.util.Scanner;
public class Partie {
    private Echequier echequier;
    private Joueur joueur1;       
    private Joueur joueur2;       
    private Joueur joueurJouer;  
    private boolean etatPartie;

    public Partie() {
        this.echequier = new Echequier();
        this.joueur1=new Joueur();
        this.joueur2=new Joueur();
        this.joueurJouer=joueur1; 
        this.etatPartie = false;
    }
    private int changeLettreEnChiffre(String lettre) {
        lettre = lettre.toLowerCase(); // Assure que la lettre est en minuscule
        if (lettre.equals("a")) {
            return 1;
        } else if (lettre.equals("b")) {
            return 2;
        } else if (lettre.equals("c")) {
            return 3;
        } else if (lettre.equals("d")) {
            return 4;
        } else if (lettre.equals("e")) {
            return 5;
        } else if (lettre.equals("f")) {
            return 6;
        } else if (lettre.equals("g")) {
            return 7;
        } else if (lettre.equals("h")) {
            return 8;
        } else {
            throw new IllegalArgumentException("Lettre invalide. Veuillez entrer une lettre entre 'a' et 'h'.");
        }
    }

    public void demanderEtBouger(){
        Scanner scanner=new Scanner(System.in);

        int ligneDépart;
        System.out.println("Quelle est la ligne du pion que vous voulez bouger?(attend un chiffre)");

        try{
            ligneDépart=Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e){
            scanner.close();
            throw new IllegalArgumentException("La collonne doit être un chiffre valide");
            
        }
        System.out.println("Quelle est la collonne du pion que vous voulez bouger?(attend une lettre)");

        String lettreDépart=scanner.nextLine();
        int collonneDépart=this.changeLettreEnChiffre(lettreDépart);


        int ligneArrive;
        System.out.println(("Quelle est la ligne d'arrivé du PIon?(attend un chiffre)"));
        try{
            ligneArrive=Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e){
            scanner.close();
            throw new IllegalArgumentException("La collonne doit être un chiffre valide");
            
        }
        System.out.println("Quelle est la colonne d'arrive du pion?(attend une lettre)");
        String lettreArrive=scanner.nextLine();
        int colonneArrive=this.changeLettreEnChiffre(lettreArrive);



        this.echequier.bouger_pion(ligneDépart, collonneDépart, ligneArrive, colonneArrive);
        this.echequier.toString();


    }

    public void changerJoueur() { // ici sa permet de changer de joueur quand un des 2 a jouer
        if (joueurJouer == joueur1) {
            joueurJouer = joueur2;
        } else {
            joueurJouer = joueur1;
        }
    }

    public boolean checkPartieTerminé() {//on verifie si la partie est terminé et annonce le vainqueur
        boolean roiBlancTrouve = false;
        boolean roiNoirTrouve = false;

        for (Pion p : echequier.getPionBlancs()) {
            if (p.getClass().getSimpleName().equals("Roi")) {
                roiBlancTrouve = true;  // on regard si y a le roi blanc
            }
        }

        for (Pion p : echequier.getPionNoirs()) {
            if (p.getClass().getSimpleName().equals("Roi")) {
                roiNoirTrouve = true; // et la on regard si y a le roi noir 
            }
        }

        if (!roiBlancTrouve) {
            etatPartie = false;
            System.out.println("Partie termine. Le roi blanc a ete eliminer. Le gagnant est : " + joueur2.getNom());
            return true;
        }

        if (!roiNoirTrouve) {
            etatPartie = false;
            System.out.println("Partie terminer. Le roi noir a ete eliminer. Le gagnant est : " + joueur1.getNom());
            return true;
        }

        return false;
    }

    private Joueur getJoueurJouer() {
        return joueurJouer;
    }
    public void deroullementPartie(){
    
        Scanner scanner=new Scanner(System.in);
        System.out.println("On va commencer une partie d'échec:");
        System.out.println();


        System.out.println("Quelle est le nom du joueur blanc?");
        String nomJoueur1= scanner.nextLine();

        System.out.println("Quelle est le nom du joueur en noirs");
        String nomJoueur2=scanner.nextLine();

        this.joueur1.setNom(nomJoueur1);
        this.joueur1.setCouleur("blancs");
        this.joueur2.setNom(nomJoueur2);
        this.joueur2.setCouleur("noirs");

        System.out.println("le Joueur blanc est "+ this.joueur1.getNom()+"\n"+"le joeuur noir est "+this.joueur2.getNom());
        this.etatPartie=true;

        while(!this.checkPartieTerminé()){
            System.out.println("c'est le tour de "+ this.joueurJouer.getNom());
            System.out.println(this.echequier);
            try{
                this.demanderEtBouger();
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("recommancer.");
            }
            this.checkPartieTerminé();
            this.changerJoueur();

        }
        this.checkPartieTerminé();
        scanner.close();
    }
    public static void main(String[] args) {
        Partie cettePartie= new Partie();
        cettePartie.deroullementPartie();
    }
}