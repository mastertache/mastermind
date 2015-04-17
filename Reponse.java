import java.util.Scanner;

public class Reponse {
	/*
	 * La class Reponse permet de traiter des entrées clavier.
	 */
	// attributs
	String [] reponsesValides;		// listes des réponses valides.
	String [] phrasesDeCorrection;	// listes des réponses possible en cas d'erreur
	static Scanner clavier = new Scanner(System.in);
	// constructeur
	public Reponse(String [] tab1, String [] tab2){		
		this.reponsesValides = tab1;
		this.phrasesDeCorrection = tab2;		
	}
	public String reponseOuiValide(){
		/*
		 * la méthode reponseOuiNonValide() permet de tester si une réponse entrée
		 * au clavier fait partie des répones valides.
		 * Données rien
		 * Retourne la chaine de caractère entrée au clavier si elle est valide
		 * et une chaine vide sinon.
		 */
		String ligne =clavier.nextLine();	
		boolean caractereValide = false;
		for(int i=0;i<this.reponsesValides.length; i++){	// on teste si il la chaine			
			if(ligne.equals(this.reponsesValides[i])){		// entrée au clavier, est identique
				caractereValide = true;						// a une des réponses possibles.
			}
		}
		if(caractereValide == true){			
			return "O"; // on retourne la valeur O pour Oui
		}
		else{
			//System.out.println(this.phrasesDeCorrection[0]);
			return "N";
		}
	}
	// méthode reponseNumValide permet de tester si une réponse entrée au clavier
	// fait partie des réponses valides.
	// Données : rien 
	// Retourne un entier qui correspond à une entrée entière valide
	//          si la valeur entrée au clavier n'est pas valide, -1 est retournée.
	public int reponseNumValide(){
		String ligne =clavier.nextLine();	
		boolean caractereValide = false;
		for(int i=0;i<this.reponsesValides.length; i++){	// on teste si il la chaine			
			if(ligne.equals(this.reponsesValides[i])){		// entrée au clavier, est identique
				caractereValide = true;						// a une des réponses possibles.
			}
		}
		if(caractereValide == true){
			String caractere1 = ""+ligne.charAt(0);
			return Integer.parseInt(caractere1); // on transforme la chaine de caractère en int
		}
		else{
			System.out.println(this.phrasesDeCorrection[0]);
			return -1;
		}
	}
}
