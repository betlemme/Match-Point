package it.gemmed.resource;

/**
 * Risultati possibili di un set
 *
 *@author GEMMED
 */
public enum Risultato {
	seizero("6-0"),
	seiuno("6-1"),
	seidue("6-2"),
	seitre("6-3"),
	seiquattro("6-4"),
	settecinque("7-5"),
	settesei("7-6"),
	zerosei("0-6"),
	unosei("1-6"),
	duesei("2-6"),
	tresei("3-6"),
	quattrosei("4-6"),
	cinquesette("5-7"),
	seisette("6-7");

	private String risultato;
	
	Risultato(String text) {
	    this.risultato = text;
	  }
	
	/**
	 * @return risultato 
	 */
	
	public String getRisultato(){
		return this.risultato;

	}
	
	/**
	 * Metodo per l'aggiornamento del risultato
	 * 
	 * @param text contenente il risultato della partita
	 * @return r risultato aggiornato
	 */
	  public static Risultato fromString(String text) {
		    if (text != null) {
		      for (Risultato r : Risultato.values()) {
		        if (text.equalsIgnoreCase(r.risultato)) {
		          return r;
		        }
		      }
		    }
		    return null;
		  }
	

}