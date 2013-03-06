package com.osrce.sitrep.cometd;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;


public class ClientSessionListener implements BayeuxServer.SessionListener {

    @Override 
	public void sessionAdded(ServerSession session) {
	    System.out.println( "ClientStatusService SessionAdded!");
/*	    Set<String> theStrs = session.getAttributeNames();
	    Iterator<String> theIt = theStrs.iterator();
	    while(theIt.hasNext()) {
		    System.out.println( "AttributeName:"+theIt.toString()+" value:"+session.getAttribute(theIt.toString()) );
		    
		    
	    }
	    */
	    System.out.println( "ClientStatusService ID:"+session.getId() );

	    System.out.println( "ClientStatusService UserAgent:"+session.getUserAgent() );

	    return;
	}
	
    @Override 
	public void sessionRemoved(ServerSession session, boolean timedout) {
	    System.out.println( "ClientStatusService SessionRemoved!");

	    return;
	}


//private static final String CLIENT_CHANNEL = "/foo/new";
//private static final String LISTENER_CHANNEL = "/meta/connect";

/*
public ClientStatusService(BayeuxServer bayeuxServer) {
    super(bayeuxServer, "notification");
//    this.addService(LISTENER_CHANNEL, "processNotification");
    System.out.println( "ClientStatusService Initialized!");
//    this.addService("/query/**", "processQuery");

    //    BayeuxServerListener theListener = new BayeuxServerListener();
//    this.getBayeux().addListener( )

}

public void processQuery(ServerSession serverSession, ServerMessage message) {
	
	System.out.println( "processQuery Called!==="+ message.getChannel()  );
	
	
	
	return;
}

public void processNotification(ServerSession serverSession, Map<String, Object> data) {
    LocalSession localSession = this.getLocalSession();
    if(logger.isDebugEnabled()) {
        logger.debug("Local Session : " + localSession.getId() + ".");
    }
  System.out.println( "processNotification Called!==="+ serverSession.getId()  );  

//  if ( serverSession.isConnected() )
  
  
    ServerChannel serverChannel = this.getBayeux().getChannel(CLIENT_CHANNEL);
    Set<ServerSession> subscribers = serverChannel.getSubscribers();
    if(0 == subscribers.size()) {
        logger.info("There are no subcribers for " + CLIENT_CHANNEL + ".");
    }
    for(ServerSession subscriber : subscribers) {
        logger.info("The subscriber for " + CLIENT_CHANNEL + " : " + subscriber.getId() + ".");
    }
    serverChannel.publish(localSession, data, null);
}
*/

}