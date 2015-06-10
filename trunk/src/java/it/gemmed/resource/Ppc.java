package it.gemmed.resource;

/**
 * Classe per l'assegnazione dei punti sulla base del tipo di
 * vittoria conseguita su PartitaSingola e sulla base del tipo
 * di torneo.
 * 
 * @author GEMMED
 */

public class Ppc implements Comparable<Object>{
	
	String classifica; //classifica del giocatore
	boolean forfait; //partita assegnata per assenza di uno dei contendenti
	int riduzione; //riduzione del torneo
	
	/**
	 * Costruttore di Ppc completo
	 * 
	 * @param classifica Stringa contenente la classifica attuale
	 * @param forfait Boolean posto a true se la partita è stata vinta per forfait
	 * @param riduzione Valore di riduzione del punteggio sulla base del torneo
	 */
	
	public Ppc(String classifica, boolean forfait, int riduzione){
		this.classifica = classifica;
		this.forfait = forfait;
		this.riduzione = riduzione;
	}
	
	/**
	 * Costruttore di Ppc completo standard in cui la vittoria non
	 * è avvenuta per forfait e la riduzione del torneo è nulla
	 * 
	 * @param iclassifica Stringa contenente la classifica attuale
	 */
	public Ppc(String iclassifica){
		this(iclassifica, false, 0);
	}
	
	/**
	 * @return classifica
	 */
	public String getClassifica() {
		return classifica;
	}
	
	/**
	 * @param classifica
	 */
	public void setClassifica(String classifica) {
		this.classifica = classifica;
	}
	
	/**
	 * @return forfait
	 */
	public boolean getForfait() {
		return forfait;
	}
	
	/**
	 * @param forfait 
	 */
	public void setForfait(boolean forfait) {
		this.forfait = forfait;
	}
	
	/**
	 * @return riduzione
	 */
	public int getRiduzione() {
		return riduzione;
	}
	
	/**
	 * @param riduzione
	 */
	public void setRiduzione(int riduzione) {
		this.riduzione = riduzione;
	}
	
	/**
	 * Metodo di confronto classifica con la classifica dell'avversario
	 * 
	 * @param o Object a cui viene fatto un casting Ppc, si riferisce all'avversario 
	 */
	public int compareTo(Object o) {
		Ppc avv = (Ppc) o;
		return Ppc.class2int(classifica) - Ppc.class2int(avv.getClassifica());
	}

	
	/**
	 * Metodo di assegnazione punteggio
	 * 
	 * @param c classifica 
	 * @return integer che rappresenta la classifica
	 */
	public static int class2int(String c){
		if(c.equals("4.NC"))
			return 1;
		else if(c.equals("4.6"))
			return 2;
		else if(c.equals("4.5"))
			return 3;
		else if(c.equals("4.4"))
			return 4;
		else if(c.equals("4.3"))
			return 5;
		else if(c.equals("4.2"))
			return 6;
		else if(c.equals("4.1"))
			return 7;
		else if(c.equals("3.5"))
			return 8;
		else if(c.equals("3.4"))
			return 9;
		else if(c.equals("3.3"))
			return 10;
		else if(c.equals("3.2"))
			return 11;
		else if(c.equals("3.1"))
			return 12;
		else if(c.equals("2.8"))
			return 13;
		else if(c.equals("2.7"))
			return 14;
		else if(c.equals("2.6"))
			return 15;
		else if(c.equals("2.5"))
			return 16;
		else if(c.equals("2.4"))
			return 17;
		else if(c.equals("2.3"))
			return 18;
		else if(c.equals("2.2"))
			return 19;
		else if(c.equals("2.1"))
			return 20;
		else if(c.substring(0, 2).equals("1.")) 
			return 20 + Integer.parseInt(c.substring(2));
		else return -1;
	}

	/**
	 * Metodo per il calcolo della classifica
	 * 
	 * @param i
	 * @return valore della classifica
	 */
	public static String  int2class(int i){
		if(i > 20)
			return "1." + (i - 20);
		switch(i){
		case 1: return "4.NC";
		case 2: return "4.6";
		case 3: return "4.5";
		case 4: return "4.4";
		case 5: return "4.3";
		case 6: return "4.2";
		case 7: return "4.1";
		case 8: return "3.5";
		case 9: return "3.4";
		case 10: return "3.3";
		case 11: return "3.2";
		case 12: return "3.1";
		case 13: return "2.8";
		case 14: return "2.7";
		case 15: return "2.6";
		case 16: return "2.5";
		case 17: return "2.4";
		case 18: return "2.3";
		case 19: return "2.2";
		case 20: return "2.1";
		default: return null;
		}
	}

}
