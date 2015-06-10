
package it.gemmed.servlet;

import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindPpcDatabase;
import it.gemmed.database.UpdateGiocatoreDatabase;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Ppc;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Calendar;


/**
 *  Servlet che aggiorna nel database la classifica dei giocatori 
 *  in base alle ultime partite disputate 
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class UpdateClassificaServlet extends AbstractDatabaseServlet{
	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * 
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *             Se si verificano degli errori nel comunicazione tra client/server.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//lista partite vinte dal giocatore
		List<Ppc> lpartV = null;
		//lista partite perse dal giocatore
		List<Ppc> lpartP = null;
		//lista giocatori di un torneo.
		List<Giocatore> lgioc = null;
		//rank finale da aggiornare nel db
		String classfinal= null;
		//classe privata per il calcolo della classifica
		Calcolo c = new Calcolo();



		try {
			int id = Integer.parseInt(req.getParameter("id"));
			lgioc = new FindPlayerDatabase(DS.getConnection()).findPlayerByTorneo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try-catch

		for (Giocatore tgioc : lgioc){
			try {
				lpartV = new FindPpcDatabase(DS.getConnection()).findPWin(tgioc.getEmail());
				lpartP = new FindPpcDatabase(DS.getConnection()).findPLost(tgioc.getEmail());

				
				classfinal = c.calcolo_class(tgioc,(Ppc[])lpartV.toArray(), (Ppc[])lpartP.toArray());

				//aggiornamento della classifica
				tgioc.setClassificaDinamica(classfinal);
				UpdateGiocatoreDatabase t = new UpdateGiocatoreDatabase(DS.getConnection(),tgioc);
				t.updateGiocatore();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//try-catch
		}//for

	}//doGet

	/**
	 * Metodo per il calcolo effettivo della classifica.
	 */
	private class Calcolo{
		
		public String calcolo_class(Giocatore g, Ppc[] won, Ppc[] lost){
			int class_vecc_int = Ppc.class2int(g.getClassifica());//classifica vecchia integer
			int punteggio_partenza = capitale_di_partenza(class_vecc_int);
			int incontri_considerati = incontri_considetati_da_classifica(class_vecc_int);
			incontri_considerati += incontri_da_vittorie_classifica(class_vecc_int,won.length);
			boolean isPromosso = false;
			String class_ret = "4.NC";
			int V = won.length;
			int E = 0;
			int I = 0;
			int G = 0;
			int perse_per_assenza = 0;
			for(int i = 0; i < lost.length; i++){
				if(lost[i].getForfait()){
					perse_per_assenza++;
				}
			}
			if(perse_per_assenza >= 3){
				E++;
			}
			if(perse_per_assenza >= 4){
				I++;
			}
			if(perse_per_assenza >= 5){
				for(int i = 0; i <= (perse_per_assenza-4); i++)
					G++;
			}
			int tmpprovaclassifica = Ppc.class2int(classificaMinimaATP_WTA(g));
			System.out.println(tmpprovaclassifica);
			if(Ppc.class2int(class_ret) < Ppc.class2int(classificaMinimaATP_WTA(g))){
				class_ret = classificaMinimaATP_WTA(g);
			}
			if(Ppc.class2int(class_ret) < Ppc.class2int(classificaMinimaITF(g))){
				class_ret = classificaMinimaITF(g);
			}

			Arrays.sort(won);
			Arrays.sort(lost);


			boolean finito = false;
			String classifica_provvisoria = g.getClassifica();
			while(!finito){
				incontri_considerati = incontri_considetati_da_classifica(Ppc.class2int(classifica_provvisoria));
				incontri_considerati += incontri_da_vittorie_classifica(Ppc.class2int(classifica_provvisoria),won.length);
				int vinte_pari_classifica = 0;
				int perse_pari_classifica = 0;
				int punti = 0;
				for(int i = 0; i < won.length; i++){
					if(Ppc.class2int(won[i].getClassifica()) == Ppc.class2int(classifica_provvisoria)){
						vinte_pari_classifica++;
					}
				}
				for(int i = 0; i < lost.length; i++){
					if(Ppc.class2int(lost[i].getClassifica()) == Ppc.class2int(classifica_provvisoria)){
						perse_pari_classifica++;
						E++;
					}
					if(Ppc.class2int(classifica_provvisoria) - Ppc.class2int(lost[i].getClassifica()) == -1)
						I++;
					if(Ppc.class2int(classifica_provvisoria) - Ppc.class2int(lost[i].getClassifica()) < -1)
						G++;

				}
				if(vinte_pari_classifica >= 5 && perse_pari_classifica < 1){
					if(Ppc.class2int(classifica_provvisoria) > Ppc.class2int("4.1")){
						punti = 100;
					}
					else
						punti = 50;
				}
				if(V - E - (2*I) - (3*G) > 0){
					incontri_considerati += V - E - (2*I) - (3*G);
				}
				int minimo_incontri_vittorie = (incontri_considerati < won.length ? incontri_considerati: won.length);
				for(int i = 0; i < minimo_incontri_vittorie ; i++){
					int tmp = valoreVittoriaClassifica(classifica_provvisoria, won[won.length - 1 - i].getClassifica());
					punti += tmp * (100-won[won.length - 1 - i].getRiduzione()) / 100;
				}

				punti += punteggio_partenza;
				punti += g.getPuntiAggiuntivi();

				System.out.println("Punti calcolati: " + punti + " da classifica " + classifica_provvisoria);
				
				if(retrocessione(classifica_provvisoria, g.getIsMaschio() ,punti) && !isPromosso){
					if(classifica_provvisoria.equals("4.NC")){
						//il metodo ritorna true alla retrocessione di default, e siccome non c'e' retrocessione
						//per chi ha gia' la classifica piu' bassa, ritorno tale classifica
					}
					else{
						classifica_provvisoria = Ppc.int2class(Ppc.class2int(classifica_provvisoria)-1);
					}
					finito = true;
				}
				else if(promozione(classifica_provvisoria, g.getIsMaschio() ,punti)){
					finito = false;
					isPromosso = true;
					classifica_provvisoria = Ppc.int2class(Ppc.class2int(classifica_provvisoria) + 1);
				}
				else{
					finito = true;
				}
			}
			if(Ppc.class2int(class_ret) < Ppc.class2int(classifica_provvisoria)){
				class_ret = classifica_provvisoria;
			}
			return class_ret;
		}

		private int capitale_di_partenza(int class_vecc_int){
			switch(class_vecc_int){
			case 1: return 0;
			case 2: return 2;
			case 3: return 5;
			case 4: return 10;
			case 5: return 20;
			case 6: return 30;
			case 7: return 50;
			case 8: return 80;
			case 9: return 120;
			case 10: return 160;
			case 11: return 200;
			case 12: return 240;
			case 13: return 290;
			case 14: return 330;
			case 15: return 370;
			case 16: return 410;
			case 17: return 450;
			case 18: return 500;
			case 19: return 550;
			case 20: return 600;
			default: return -1;
			}
		}
		private int incontri_considetati_da_classifica(int c){
			if(c < Ppc.class2int("4.NC"))
				return 0;
			else if(c <= Ppc.class2int("4.4"))
				return 5;
			else if(c <= Ppc.class2int("4.1"))
				return 6;
			else if(c <= Ppc.class2int("3.3"))
				return 7;
			else if(c <= Ppc.class2int("3.1"))
				return 8;
			else if(c <= Ppc.class2int("2.7"))
				return 9;
			else if(c <= Ppc.class2int("2.5"))
				return 10;
			else if(c == Ppc.class2int("2.4"))
				return 11;
			else if(c == Ppc.class2int("2.3"))
				return 12;
			else if(c == Ppc.class2int("2.2"))
				return 13;
			else if(c == Ppc.class2int("2.1"))
				return 14;
			else
				return 0;
		}

		private int incontri_da_vittorie_classifica(int c, int vit){
			if(c < Ppc.class2int("4.NC") || vit < 0)
				return 0;
			else if(c <= Ppc.class2int("4.1")){
				if(vit <= 5)
					return 1;
				else if(vit <= 10)
					return 2;
				else if(vit <= 15)
					return 3;
				else if(vit <= 20)
					return 4;
				else
					return 5;
			}
			else if(c <= Ppc.class2int("3.1")){
				if(vit <= 6)
					return 1;
				else if(vit <= 12)
					return 2;
				else if(vit <= 18)
					return 3;
				else if(vit <= 24)
					return 4;
				else
					return 5;
			}
			else if(c <= Ppc.class2int("2.5")){
				if(vit <= 7)
					return 1;
				else if(vit <= 14)
					return 2;
				else if(vit <= 21)
					return 3;
				else if(vit <= 28)
					return 4;
				else 
					return 5;
			}
			else if(c <= Ppc.class2int("2.1")){
				if(vit <= 8)
					return 1;
				else if(vit <= 16)
					return 2;
				else if(vit <= 24)
					return 3;
				else if(vit <= 32)
					return 4;
				else if(vit <= 40)
					return 5;
				else
					return 6;
			}
			else
				return 0;
		}

		/**
		 *	Un giocatore con una certa classifica ATP_WTA ha almeno
		 *  la classifica sotto indicata. Tuttavia la classifica
		 *  attual potrebbe risultare maggiore, per cui non viene 
		 *  ritornata immediatamente, ma sara' confrontata con 
		 *  quella calcolata in base agli altri parametri
		 * @param g Giocatore in esame
		 * @return Stringa rappresentate la minor classifica assegnabile a tale 
		 * giocatore sulla base.
		 */
		private String classificaMinimaATP_WTA(Giocatore g){
			if(g.getAtpWta() != 0 && g.getAtpWta() > 0){
				int prof = g.getAtpWta();
				if(g.getIsMaschio()){
					if(prof <= 300) return "1." + prof;
					else if(prof <= 500) return "2.1";
					else if(prof < 800) return "2.2";
					else if(prof < 900) return "2.3";
					else if(prof < 1000) return "2.4";
					else if(prof < 1200) return "2.5";
					else if(prof < 1300) return "2.6";
					else if(prof < 1500) return "2.7";
				}
				else{
					if(prof <= 150) return "1." + prof;
					else if(prof <= 400) return "2.1";
					else if(prof < 600) return "2.2";
					else if(prof < 700) return "2.3";
					else if(prof < 800) return "2.4";
					else if(prof < 900) return "2.5";
					else if(prof < 1000) return "2.6";
				}
			}
			return "4.NC";
		}

		public String classificaMinimaITF(Giocatore g){
			if(g.getItf() <= 0)
				return "4.NC";

			int ITF = g.getItf();
			int eta = 0;
			Calendar cal = new java.util.GregorianCalendar();
			cal.setTime(g.getData_nascita());
			eta = Calendar.YEAR - cal.get(Calendar.YEAR);
			if(eta <= 14){
				if(ITF <= 10)
					return "3.1";
				else if(ITF <= 20)
					return "3.3";
				else if(ITF <= 40)
					return "3.4";
				else if(ITF <= 70)
					return "3.5";
				else if(ITF <= 100)
					return "4.1";
			}
			else if (eta <= 16){
				if(ITF <= 10)
					return "2.5";
				else if(ITF <= 20)
					return "2.6";
				else if(ITF <= 50)
					return "2.8";
				else if(ITF <= 100)
					return "3.1";
				else if(ITF <= 200)
					return "3.3";
			}
			else if (eta <= 18){
				if(ITF <= 20)
					return "2.2";
				else if(ITF <= 50)
					return "2.3";
				else if(ITF <= 150)
					return "2.4";
				else if(ITF <= 300)
					return "2.6";
				else if(ITF <= 500)
					return "2.7";
				else if(ITF <= 700)
					return "3.1";
				else if(ITF <= 1000)
					return "3.3";
			}
			if(g.getIsMaschio()){
				if(eta >= 70){
					if(ITF <= 20){
						return "4.3";
					}
					else if(ITF <= 50){
						return "4.4";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 65){
					if(ITF <= 20){
						return "4.2";
					}
					else if(ITF <= 50){
						return "4.3";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 60){
					if(ITF <= 20){
						return "4.1";
					}
					else if(ITF <= 50){
						return "4.2";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 55){
					if(ITF <= 20){
						return "3.5";
					}
					else if(ITF <= 50){
						return "4.1";
					}
					else if(ITF <= 100){
						return "4.2";
					}
				}
				else if(eta >= 50){
					if(ITF <= 20){
						return "3.3";
					}
					else if(ITF <= 50){
						return "3.4";
					}
					else if(ITF <= 100){
						return "3.5";
					}
				}
				else if(eta >= 45){
					if(ITF <= 20){
						return "3.2";
					}
					else if(ITF <= 50){
						return "3.3";
					}
					else if(ITF <= 100){
						return "3.4";
					}
				}
			}
			else{
				if(eta >= 65){
					if(ITF <= 20){
						return "4.5";
					}
					else if(ITF <= 50){
						return "4.4";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 60){
					if(ITF <= 20){
						return "4.3";
					}
					else if(ITF <= 50){
						return "4.4";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 55){
					if(ITF <= 20){
						return "4.2";
					}
					else if(ITF <= 50){
						return "4.3";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 50){
					if(ITF <= 20){
						return "3.5";
					}
					else if(ITF <= 50){
						return "4.1";
					}
					else if(ITF <= 100){
						return "4.NC";
					}
				}
				else if(eta >= 45){
					if(ITF <= 20){
						return "3.3";
					}
					else if(ITF <= 50){
						return "3.4";
					}
					else if(ITF <= 100){
						return "3.5";
					}
				}
			}
			return "4.NC";
		}

		public int valoreVittoriaClassifica(String io, String avv){
			int io_num = Ppc.class2int(io);
			int avv_num = Ppc.class2int(avv);
			switch(avv_num - io_num){
			case 18: return 120;
			case 17: return 120;
			case 16: return 120;
			case 15: return 120;
			case 14: return 120;
			case 13: return 120;
			case 12: return 120;
			case 11: return 120;
			case 10: return 120;
			case 9: return 120;
			case 8: return 120;
			case 7: return 120;
			case 6: return 120;
			case 5: return 120;
			case 4: return 120;
			case 3: return 120;
			case 2: return 120;
			case 1: return 90;
			case 0: return 60;
			case -1: return 30;
			case -2: return 20;
			case -3: return 15;
			default: return 0;		
			}
		}


		public boolean promozione(String classifica, boolean isMaschio, int punti){
			int intclass = Ppc.class2int(classifica);
			if(isMaschio){
				switch(intclass){
				case 1: if(punti >= 50) return true; else return false;
				case 2: if(punti >= 100) return true; else return false;
				case 3: if(punti >= 155) return true; else return false;
				case 4: if(punti >= 250) return true; else return false;
				case 5: if(punti >= 340) return true; else return false;
				case 6: if(punti >= 410) return true; else return false;
				case 7: if(punti >= 480) return true; else return false;
				case 8: if(punti >= 510) return true; else return false;
				case 9: if(punti >= 560) return true; else return false;
				case 10: if(punti >= 660) return true; else return false;
				case 11: if(punti >= 730) return true; else return false;
				case 12: if(punti >= 770) return true; else return false;
				case 13: if(punti >= 910) return true; else return false;
				case 14: if(punti >= 960) return true; else return false;
				case 15: if(punti >= 1010) return true; else return false;
				case 16: if(punti >= 1070) return true; else return false;
				case 17: if(punti >= 1160) return true; else return false;
				case 18: if(punti >= 1250) return true; else return false;
				case 19: if(punti >= 1360) return true; else return false;
				default: return false;
				}
			}
			else{
				switch(intclass){
				case 1: if(punti >= 50) return true; else return false;
				case 2: if(punti >= 100) return true; else return false;
				case 3: if(punti >= 170) return true; else return false;
				case 4: if(punti >= 240) return true; else return false;
				case 5: if(punti >= 340) return true; else return false;
				case 6: if(punti >= 410) return true; else return false;
				case 7: if(punti >= 480) return true; else return false;
				case 8: if(punti >= 510) return true; else return false;
				case 9: if(punti >= 570) return true; else return false;
				case 10: if(punti >= 660) return true; else return false;
				case 11: if(punti >= 730) return true; else return false;
				case 12: if(punti >= 770) return true; else return false;
				case 13: if(punti >= 810) return true; else return false;
				case 14: if(punti >= 950) return true; else return false;
				case 15: if(punti >= 1010) return true; else return false;
				case 16: if(punti >= 1100) return true; else return false;
				case 17: if(punti >= 1100) return true; else return false;
				case 18: if(punti >= 1250) return true; else return false;
				case 19: if(punti >= 1360) return true; else return false;
				default:return false;
				}

			}
		}
		public boolean retrocessione(String classifica, boolean isMaschio, int punti){
			int intclass = Ppc.class2int(classifica);
			if(isMaschio){
				switch(intclass){
				case 1: return false;
				case 2: if(punti <= 25) return true; else return false;
				case 3: if(punti <= 55) return true; else return false;
				case 4: if(punti <= 90) return true; else return false;
				case 5: if(punti <= 145) return true; else return false;
				case 6: if(punti <= 205) return true; else return false;
				case 7: if(punti <= 245) return true; else return false;
				case 8: if(punti <= 290) return true; else return false;
				case 9: if(punti <= 325) return true; else return false;
				case 10: if(punti <= 370) return true; else return false;
				case 11: if(punti <= 465) return true; else return false;
				case 12: if(punti <= 535) return true; else return false;
				case 13: if(punti <= 565) return true; else return false;
				case 14: if(punti <= 640) return true; else return false;
				case 15: if(punti <= 700) return true; else return false;
				case 16: if(punti <= 750) return true; else return false;
				case 17: if(punti <= 880) return true; else return false;
				case 18: if(punti <= 920) return true; else return false;
				case 19: if(punti <= 975) return true; else return false;
				case 20: if(punti <= 1040) return true; else return false;
				default: return true;
				}
			}
			else{
				switch(intclass){
				case 2: if(punti <= 25) return true; else return false;
				case 3: if(punti <= 55) return true; else return false;
				case 4: if(punti <= 90) return true; else return false;
				case 5: if(punti <= 145) return true; else return false;
				case 6: if(punti <= 205) return true; else return false;
				case 7: if(punti <= 245) return true; else return false;
				case 8: if(punti <= 270) return true; else return false;
				case 9: if(punti <= 325) return true; else return false;
				case 10: if(punti <= 415) return true; else return false;
				case 11: if(punti <= 465) return true; else return false;
				case 12: if(punti <= 525) return true; else return false;
				case 13: if(punti <= 550) return true; else return false;
				case 14: if(punti <= 620) return true; else return false;
				case 15: if(punti <= 700) return true; else return false;
				case 16: if(punti <= 740) return true; else return false;
				case 17: if(punti <= 800) return true; else return false;
				case 18: if(punti <= 890) return true; else return false;
				case 19: if(punti <= 975) return true; else return false;
				case 20: if(punti <= 1040) return true; else return false;
				default: return false;
				}

			}
		}

	}//Calcolo


}//UpdateClassificaServlet

