package it.gemmed.servlet;

import it.gemmed.database.FindArbitroDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Message;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che genera la pagina profilo di un utente arbitro
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class ProfiloArbitroServlet extends AbstractDatabaseServlet {
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

		List<Torneo> t;

		Arbitro arbitro = (Arbitro) req.getSession().getAttribute("arbitro");
		try {
			t = new FindTorneoDatabase(DS.getConnection()).findTorneoPerArbitro(arbitro.getEmail());
			
			
			req.setAttribute("tornei", t);
		
			req.setAttribute("arbitro", arbitro);
			req.getRequestDispatcher("/jsp/profilo-arbitro.jsp").forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		List<Torneo> t;

		Arbitro arbitro = (Arbitro) req.getSession().getAttribute("arbitro");
		try {
			t = new FindTorneoDatabase(DS.getConnection()).findTorneoPerArbitro(arbitro.getEmail());
			
			
			req.setAttribute("tornei", t);
		
			req.setAttribute("arbitro", arbitro);
			req.getRequestDispatcher("/jsp/profilo-arbitro.jsp").forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}