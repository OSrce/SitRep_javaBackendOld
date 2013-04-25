package com.osrce.sitrep.cometd;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;

import org.cometd.bayeux.server.BayeuxServer;



public class Initializer extends GenericServlet
{
    private QueryEmitter emitter;
//    public DatabaseListener dbListener;
    
//    @Inject
//    private BayeuxServer bayeuxServer;
    private ClientSessionListener clientSessionListener;

    @Override
    public void init() throws ServletException
    {
    	
//        BayeuxServer bayeux = (BayeuxServer)getServletContext().getAttribute(BayeuxServer.ATTRIBUTE);
//        new ClientStatusService(bayeux);
//        clientSessionListener = new ClientSessionListener();
//    	bayeux.addListener(clientSessionListener);
    	
        // Create the emitter
//        emitter = new QueryEmitter();
        
//        dbListener = new DatabaseListener();
/*        try {
        	dbListener.start();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
*/
    	// Retrieve the CometD service instantiated by AnnotationCometdServlet
//        QueryService service = (QueryService)getServletContext().getAttribute(QueryService.class.getName());

        // Register the service as a listener of the emitter
//        emitter.getListeners().add(service);

//        BayeuxServerListener theChannelListener = new BayeuxServerListener();
//        bayeuxServer.addListener(theChannelListener);
        
        // Start the emitter
//        emitter.start();
    }

    @Override
    public void destroy()
    {
        // Stop the emitter
 //       emitter.stop();
        
 //       dbListener.stop();

    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException
    {
        throw new UnavailableException("Initializer");
    }
}





