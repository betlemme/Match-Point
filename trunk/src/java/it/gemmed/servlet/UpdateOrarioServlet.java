package it.gemmed.servlet;

import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.database.UpdatePartitaSingolaDatabase;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Risultato;
import it.gemmed.resource.Torneo;
import it.gemmed.resource.Circolo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *  Servlet che modifica la data di una partita
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class UpdateOrarioServlet extends AbstractDatabaseServlet {
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

		int id = -1;
		int numeroPartita = -1;
		Date data = null;
		
		
		Message m = null;
		
		try {

			id = Integer.parseInt(req.getParameter("torneo"));
			numeroPartita = Integer.parseInt(req.getParameter("id"));//id perchè è javascript
			
			data = Date.valueOf(req.getParameter("data"));
			
			//creo bean per aggiornare vincitore
			PartitaSingola partita = new PartitaSingola(numeroPartita, id);
			
			new UpdatePartitaSingolaDatabase(DS.getConnection(), partita).updateData(data);

			m = new Message("aggiornamento riuscito");
		}  catch (NumberFormatException ex) {
			m = new Message("Invalid input parameters: data must be Date.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
				m = new Message("unexpected error while accessing the database.", 
						"E200", ex.getMessage());
		}
			
			req.setAttribute("id", id);
			req.setAttribute("message", m);
			
			res.sendRedirect("profiloTorneoArbitro?id="+id);
		 

	}

}