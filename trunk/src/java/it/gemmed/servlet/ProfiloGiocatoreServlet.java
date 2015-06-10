package it.gemmed.servlet;

import it.gemmed.database.FindIscrizioneDatabase;
import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che genera la pagina profilo in html di un giocatore
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class ProfiloGiocatoreServlet extends AbstractDatabaseServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Message m = null;
		List<Torneo> t;
		List<Torneo> storico;
		List<Torneo> disp;
		List<Torneo> incorso;
		List<PartitaSingola> next;

		Giocatore giocatore = (Giocatore) req.getSession().getAttribute("giocatore");
		try {
			t = new FindIscrizioneDatabase(DS.getConnection()).findTorneoDisponibile(giocatore.getEmail());
			storico = new FindIscrizioneDatabase(DS.getConnection()).findTorneoConcluso(giocatore.getEmail());
			next = new FindPartitaDatabase(DS.getConnection()).findPartitaDisputare(giocatore.getEmail());
			disp = new FindIscrizioneDatabase(DS.getConnection()).findTorneoDaDisputare(giocatore.getEmail());
			incorso = new FindIscrizioneDatabase(DS.getConnection()).findTorneoInCorso(giocatore.getEmail());
			
			for(int i = 0; i < t.size(); i++)
			{
				if(giocatore.getScadenza_tessera() != null)
				{
					if(giocatore.getScadenza_tessera().before(t.get(i).getData_fine()))
					{
						t.remove(i);
					}//if
				}
			
			}//for
			
			
			for(int i = 0; i<next.size(); i++)
			{
				Torneo temp = new FindTorneoDatabase(DS.getConnection()).findTorneo(next.get(i).getTorneo());
				next.get(i).setNomeTorneo(temp.getNome());
			}//for
			
			req.setAttribute("torneidisponibili", t);
			req.setAttribute("torneidisputati", storico);
			req.setAttribute("prossimepartite", next);
			req.setAttribute("torneidadisputare", disp);
			req.setAttribute("torneiincorso", incorso);
					
			req.setAttribute("giocatore", giocatore);
			req.getRequestDispatcher("/jsp/profilo-giocatore.jsp").forward(req, res);
		} catch (SQLException e) {
			log(e.getMessage());
		}//try-cath

	}//doPost
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
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Message m = null;
		List<Torneo> t;
		List<Torneo> storico;
		List<Torneo> disp;
		List<Torneo> incorso;
		List<PartitaSingola> next;

		Giocatore giocatore = (Giocatore) req.getSession().getAttribute("giocatore");
		try {
			t = new FindIscrizioneDatabase(DS.getConnection()).findTorneoDisponibile(giocatore.getEmail());
			storico = new FindIscrizioneDatabase(DS.getConnection()).findTorneoConcluso(giocatore.getEmail());
			next = new FindPartitaDatabase(DS.getConnection()).findPartitaDisputare(giocatore.getEmail());
			disp = new FindIscrizioneDatabase(DS.getConnection()).findTorneoDaDisputare(giocatore.getEmail());
			incorso = new FindIscrizioneDatabase(DS.getConnection()).findTorneoInCorso(giocatore.getEmail());
			
			for(int i = 0; i < t.size(); i++)
			{
				if(giocatore.getScadenza_tessera() != null)
				{
					if(giocatore.getScadenza_tessera().before(t.get(i).getData_fine()))
					{
						t.remove(i);
					}//if
				}
			
			}//for
			
			
			for(int i = 0; i<next.size(); i++)
			{
				Torneo temp = new FindTorneoDatabase(DS.getConnection()).findTorneo(next.get(i).getTorneo());
				next.get(i).setNomeTorneo(temp.getNome());
			}//for
			
			req.setAttribute("torneidisponibili", t);
			req.setAttribute("torneidisputati", storico);
			req.setAttribute("prossimepartite", next);
			req.setAttribute("torneidadisputare", disp);
			req.setAttribute("torneiincorso", incorso);
					
			req.setAttribute("giocatore", giocatore);
			req.getRequestDispatcher("/jsp/profilo-giocatore.jsp").forward(req, res);
		} catch (SQLException e) {
			log(e.getMessage());
		}//try-cath

	}//doGet
	
}//ProfiloGiocatoreServler