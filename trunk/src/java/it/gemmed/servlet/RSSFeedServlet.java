package it.gemmed.servlet;

import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Servlet per la gestione di RSSFeed
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class RSSFeedServlet extends AbstractDatabaseServlet {
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
	private static final DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_1.0");

		feed.setTitle("Prossime partite - Match Point");
		feed.setLink("http://match-point.com");
		feed.setDescription("This feed has been created using ROME (Java syndication utilities");
		
		List<PartitaSingola> last = new ArrayList<PartitaSingola>();
		try {
			last = new FindPartitaDatabase(DS.getConnection()).findProssimePartite();
		} catch (SQLException e1) {
			log(e1.getMessage());
		}

        List entries = new ArrayList();
        SyndEntry entry;
        SyndContent description;

        for (PartitaSingola partita : last) {
            entry = new SyndEntryImpl();
            entry.setTitle("Partita "+partita.getSfidanteA()+" " + partita.getSfidanteB());
            entry.setLink("http://match-point");
    		entry.setPublishedDate(new Date());
            description = new SyndContentImpl();
            description.setType("text/plain");
            // Bisognerebbe verificare che ora e data siano settate, ma per questa sera ho fatto abbastanza.
            String ora = partita.getOra() == null ? "N/A" : partita.getOra().toString();
            String data = partita.getData() == null ? "N/A" : partita.getData().toString();
            description.setValue("Partita "+partita.getSfidanteA()+" " + partita.getSfidanteB() + " alle " + ora + " il " + data);
            entry.setDescription(description);
            entries.add(entry);
		}
        
        feed.setEntries(entries);

        SyndFeedOutput output = new SyndFeedOutput();
        String f = null;
		try {
			f = output.outputString(feed);
		} catch (FeedException e) {
			log(e.getMessage());
		}
		
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("application/rss+xml"); 
        out.print(f);
	}

}