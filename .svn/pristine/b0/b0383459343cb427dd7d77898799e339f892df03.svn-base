package it.gemmed.servlet;

import it.gemmed.database.FindArbitroDatabase;
import it.gemmed.database.FindCampoDatabase;
import it.gemmed.database.FindCircoloDatabase;
import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Campo;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Torneo;
import it.gemmed.resource.Circolo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *  Servlet che genera la pagina profilo in html
 *  di un torneo, visibile unicamente dall'arbitro assegnato a tale torneo.
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class ProfiloTorneoArbitroServlet extends AbstractDatabaseServlet {
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

		
		int id;
		Torneo torneo = null;
		Message m = null;
		List<Giocatore> l = null;
		List<PartitaSingola> p = null;
		List<Campo> c = null;
		Arbitro a = null;
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
			torneo = new FindTorneoDatabase(DS.getConnection()).findTorneoConInfo(id);
			l = new FindPlayerDatabase(DS.getConnection()).findPlayerByTorneo(id);
			p = new FindPartitaDatabase(DS.getConnection()).findPartite(id);
			a = new FindArbitroDatabase(DS.getConnection()).findArbitro(torneo.getArbitro());
			//dall'id torneo, ricavo anche il circolo che lo ospita(email_circolo). dal circolo quindi ricavo la lista dei suoi campi da gioco
			String email_circolo = new FindCircoloDatabase(DS.getConnection()).findCircolo(id);
			c = new FindCampoDatabase(DS.getConnection()).findCampi(email_circolo);
			
			m = new Message("Employees successfully searched.");
		}  catch (NumberFormatException ex) {
			log(ex.getMessage());
		} catch (SQLException ex) {
			log(ex.getMessage());
		}
			req.setAttribute("torneo", torneo);
			req.setAttribute("giocatori", l);
			req.setAttribute("partite", p);
			req.setAttribute("arbitro", a);
			req.setAttribute("campi", c);
			req.getRequestDispatcher("/jsp/profilo-torneo-arbitro.jsp").forward(req, res);
		 

	}

}