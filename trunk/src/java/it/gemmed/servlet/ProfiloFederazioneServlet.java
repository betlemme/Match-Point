package it.gemmed.servlet;

import it.gemmed.database.FindArbitroDatabase;
import it.gemmed.database.FindCircoloDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
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
 * Servlet che genera la pagina profilo in html di un 
 * utente della federazione.
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class ProfiloFederazioneServlet extends AbstractDatabaseServlet {
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
		List<Arbitro> a;
		List<Circolo> c;
		List<Giocatore> p = new ArrayList<Giocatore>();
		List<Arbitro> ast;
		List<Giocatore> pst;
		try {
			t = new FindTorneoDatabase(DS.getConnection()).findTorneo();
			a = new FindArbitroDatabase(DS.getConnection()).findArbitro();
			c = new FindCircoloDatabase(DS.getConnection()).findCircolo();
			ast = new FindArbitroDatabase(DS.getConnection()).findArbitroScaduto();
			pst = new FindPlayerDatabase(DS.getConnection()).findPlayerNoTessera();
			
			if(req.getParameter("search_player")!=null) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("search", true);
				if(req.getParameter("radio_choice").equals("nome"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByNome(req.getParameter("value"));
				}
				else if(req.getParameter("radio_choice").equals("cognome"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByCognome(req.getParameter("value"));
				}
				else if(req.getParameter("radio_choice").equals("email"))
				{
					p.add(new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(req.getParameter("value")));
				}
				else if(req.getParameter("radio_choice").equals("tessera"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByTessera(req.getParameter("value"));
				}

			}

			HttpSession session = req.getSession();
			session.setAttribute("tornei", t);
			session.setAttribute("arbitri", a);
			session.setAttribute("circoli", c);
			session.setAttribute("giocatori", p);
			session.setAttribute("giocatoriScad", pst);
			session.setAttribute("arbitriScad", ast);
			req.getRequestDispatcher("/jsp/profilo-federazione.jsp").forward(req, res);
		} catch (SQLException e) {
			log(e.getMessage());
		}

	}
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
		List<Arbitro> a;
		List<Circolo> c;
		List<Giocatore> p = new ArrayList<Giocatore>();
		List<Arbitro> ast;
		List<Giocatore> pst;
		try {
			t = new FindTorneoDatabase(DS.getConnection()).findTorneo();
			a = new FindArbitroDatabase(DS.getConnection()).findArbitro();
			c = new FindCircoloDatabase(DS.getConnection()).findCircolo();
			ast = new FindArbitroDatabase(DS.getConnection()).findArbitroScaduto();
			pst = new FindPlayerDatabase(DS.getConnection()).findPlayerNoTessera();
			
			if(req.getParameter("search_player")!=null) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("search", true);
				if(req.getParameter("radio_choice").equals("nome"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByNome(req.getParameter("value"));
				}
				else if(req.getParameter("radio_choice").equals("cognome"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByCognome(req.getParameter("value"));
				}
				else if(req.getParameter("radio_choice").equals("email"))
				{
					p.add(new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(req.getParameter("value")));
				}
				else if(req.getParameter("radio_choice").equals("tessera"))
				{
					p = new FindPlayerDatabase(DS.getConnection()).findPlayerByTessera(req.getParameter("value"));
				}

			}

			HttpSession session = req.getSession();
			session.setAttribute("tornei", t);
			session.setAttribute("arbitri", a);
			session.setAttribute("circoli", c);
			session.setAttribute("giocatori", p);
			session.setAttribute("giocatoriScad", pst);
			session.setAttribute("arbitriScad", ast);
			req.getRequestDispatcher("/jsp/profilo-federazione.jsp").forward(req, res);
		} catch (SQLException e) {
			log(e.getMessage());
		}

	}

}