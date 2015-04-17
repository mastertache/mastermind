import java.util.ArrayList;
import java.util.Scanner;

public class MasterMind {

	/**
	 * Programme de jeu de MasterMind
	 */
	static Scanner clavier = new Scanner(System.in);
	/*
	 * La méthode hasard() tire un nombre au hasard entre 1 et max
	 * Donnée : un entier
	 * Retourne un entier
	 */
	static int hasard(int max) {
		  return (1 + (int)(Math.random() * max));
		}
	/*
	 * La méthode tirerCombinaison() donne une combinaison de 4 chiffres au hasard.
	 * Donnée : rien
	 * Retourne un tableau de max (entre 4 et 6) entiers tirés au hasard (entre 1 et 6).
	 */
	static public int[] tirerCombinaison(int max){
			int[] val = new int[max];			
			
			for(int i=0;i<val.length;++i){					
					val[i] = hasard(6); //valeur au hasard entre 1 et 6
			}
			return val;
	}
	/*
	 * La méthode demanderCoup() permet de récupérer la proposition de max entiers faite par l'utilisateur.
	 * Donnée : un entier max qui indique le nombre de chiffres de la proposition
	 * Retourne un tableau de max (entier entre 1 et 4)  entiers.
	 */
	static public int[] demanderCoup(int max){
		int[] val = new int[max];
		String [] ValeursNumeriquesPossibles = {"1","2","3","4","5","6"};
		String [] Informations = {"Votre réponse n'est pas valide, ce doit être un entier compris entre 1 et 6."};
		Reponse rep = new Reponse(ValeursNumeriquesPossibles, Informations);
		System.out.println("Entrez les "+max+" chiffres de votre proposition (entre 1 et 6) : ");
		for(int i=0;i<val.length;++i){
			do{
			//do{
				System.out.print("Le chiffre n°"+(i+1)+" : ");
				//val[i] = clavier.nextInt();
				val[i] = rep.reponseNumValide();
				/*if(val[i]<1){
					System.out.println("La valeur doit être plus grande que 1.");
				}
				if(val[i]>6){
					System.out.println("La valeur doit être plus petite que 6.");
				}
			}while((val[i]<1) || (val[i]>6));			
			*/
			}while(val[i] == -1);
			System.out.print("");
		}
		return val;
	}
	/*
	 * La méthode copieTableau() permet de copier un tableau statique dans un tableau dynamique
	 * Donnée : un tableau d'entier statique 
	 * Retourne un tableau de même taille (et contenu) que le tableau statique mais c'est un tableau dynamique
	 * on peut supprimer des cellules
	 */
	static public ArrayList<Integer> copieTableau(int[] aCopier){
		ArrayList<Integer> t = new ArrayList<Integer>();		
		
		for(int i=0;i<aCopier.length;++i){		
			t.add(aCopier[i]);
		}		
		return t;
	}
	/*
	 * La méthode compare() compare si la liste maCombinaison est identique à la liste laCombinaison.
	 * Données : laCombinaison qui est une liste d'entiers contenant la combinaison cherchée
	 * 			 maCombinaison qui est une liste d'entiers contenant la combinaison du joueur
	 *           reponse qui est une liste de deux entiers	
	 * Retourne true si les deux listes d'entiers laCombinaison et maCombinaison sont identiques.
	 * Retourne la liste reponse (pour aider le joueur).
	 *           si reponse[0]=reponse[1]=0, aucun entiers de la liste la maCombinaison est dans la
	 *           liste LaCombinaison.
	 *           sinon reponse[0] contient les entiers bien placés et reponse[1] contient les entiers
	 *           présents mais mal placés.
	 */
	static public boolean compare(int[] laCombinaison, int[] maCombinaison, int[] reponse){
		int cpt1 = 0;
		int cpt2 = 0;
		ArrayList<Integer> copieLa = new ArrayList<Integer>();
		ArrayList<Integer> copieMa = new ArrayList<Integer>();
				
		// initialisation de réponse.
		for(int i=0;i<reponse.length;++i){
			reponse[i] = 0;
		}				
		// Début du test.
		for(int i=0;i<laCombinaison.length;++i){		// on comptabilise les entiers bien placés 
			if(laCombinaison[i] == maCombinaison[i]){	// dans cpt1
				cpt1 = cpt1 +1;								
			}
			else{
				copieLa.add(laCombinaison[i]);// on ne copie que ce qui n'est pas bien placé
				copieMa.add(maCombinaison[i]);// on ne copie que ce qui n'est pas bien placé
			}
		}
		reponse[0] = cpt1;						// sauvegarde de des entiers bien placés dans reponse[0] 
				
		// initialisation des listes dejaTraiteMa et dejaTraiteLa.
		boolean[] dejaTraiteMa = new boolean[copieMa.size()];
		boolean[] dejaTraiteLa = new boolean[copieMa.size()];
		
		for(int i=0;i<dejaTraiteMa.length;++i){
			dejaTraiteMa[i] = false;	// indique que l'élément i de la liste copieMa n'a pas déjà été selectionné
			dejaTraiteLa[i] = false;	// indique que l'élément i de la liste copieLa n'a pas déjà été selectionné
		}
		// test si un entier est mal placé, on balaie toutes les valeurs de la liste copieLa et on compare avec la valeur de la liste copieMa.
		for(int i=0;i<copieMa.size();++i){
			for(int j=0;j<copieLa.size();++j){
				if((copieMa.get(i) == copieLa.get(j)) && (dejaTraiteMa[i] == false) && (dejaTraiteLa[j] == false)){
					cpt2 = cpt2 + 1;			//	on comptabilise les entiers présents mais mal placés
					dejaTraiteMa[i] = true;     // pour indiquer que l'élément i de copieMa est selectionné
					dejaTraiteLa[j] = true;     // pour indiquer que l'élément j de copieLa est selectionné
				}
			}
		}
		reponse[1] = cpt2;	// sauvegarde de des entiers présents mais mal placés dans reponse[1]
		
		// gagné, si tout est bien placé
		if(cpt1 == laCombinaison.length){
			return true;
		}
		// perdu
		else{
			return false;
		}
	}
	/*
	 * la méthode afficheCombinaison(), affiche le contenu d'une liste d'entiers.
	 * Donnée : une liste d'entiers
	 * Retourne une chaine de caractères contenant la liste tab.
	 */
	static String afficheCombinaison(int[] tab){
		String s="";
		for(int i=0;i<tab.length;++i){
			s=s+' '+tab[i];			
		}
		return (s=s+'\n');		
	}
	/*
	 * La méthode afficheResultat() affiche une aide pour le joueur.
	 *  en donnant le nombre de valeurs bien placées et
	 *  le nombre de valeurs mal placés 
	 * Donnée : un tableau d'entiers 
	 * Retourne une chaine de caractaires contenant le résultat.
	 */
	public static String afficheResultat(int[] tab){
		String s = "Dans votre combinaison, il y a "+tab[0];
		if (tab[0]>1){
			s = s + " valeurs bien placées, et ";
		}
		else{
			s = s + " valeur bien placée, et ";
		}
		if (tab[1]>1){
			s = s + tab[1] +" valeurs mal placées.";
		}
		else{
			s = s + tab[1] +" valeur mal placée.";
		}
		return s+'\n';
	}
	/*
	 * Procédure principal.
	 */
	public static void main(String[] args) {
		String [] valeursNumeriquesPossibles = {"4","5","6"};
		String [] siNonValide1 = {"Valeur non valide, il faut entrer un nombre entier compris entre 4 et 6."};
		Reponse rep1 = new Reponse(valeursNumeriquesPossibles,siNonValide1);
		String [] valeursQuitterPossibles = {"O","o","Oui","OUI"};
		String [] siNonValide2 = {"Valeur non valide, il faut entrer un nombre entier compris entre 4 et 6."};
		Reponse rep2 = new Reponse(valeursQuitterPossibles,siNonValide2);
		String quitter;
		int[] maCombinaison, laCombinaison;
		//int t [] = { 3, 3, 1 , 2};
		//int t [] = { 3, 3, 1 , 3, 5,1};
		int[] reponse = new int[2];		
		boolean r;
		int max = -1;
		//laCombinaison = t;
		do{
			System.out.println("Donner la longueur de la combinaison [4;6].");
			max = rep1.reponseNumValide();
		}while(max == -1);
		//max = clavier.nextInt();
		laCombinaison = tirerCombinaison(max);
		System.out.println("La combinaison est : "+ afficheCombinaison(laCombinaison));
		System.out.println("Le but de ce jeu est de trouver la combinaison de "+max+" entiers (compris entre 1 et 6).");
		System.out.println("Un même entier peut apparaître à plusieurs reprises.");
		System.out.println();
		do{
			maCombinaison = demanderCoup(max);
			System.out.println("");
			System.out.println("Votre combinaison est : "+ afficheCombinaison(maCombinaison));
			
			r = compare(laCombinaison, maCombinaison, reponse);			
			System.out.println(afficheResultat(reponse));
			System.out.println("Voulez-vous quitter le jeu (O/n) ?");
			quitter = rep2.reponseOuiValide();
		}while(r == false && quitter == "N");
		System.out.println("");
		if(quitter == "O"){
			System.out.println("A bientôt.");
		}
		else{
			System.out.println("gagné!");
		}
	}

}
