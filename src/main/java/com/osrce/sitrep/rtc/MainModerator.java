
package com.osrce.sitrep.rtc;

import org.coweb.DefaultSessionModerator;
import org.coweb.SessionModerator;

import com.osrce.sitrep.domain.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bot class for the comap example. When a user adds a marker this bot
 * will see the sync event and start pushing info to the session.
 */
public class MainModerator extends DefaultSessionModerator {

    private SessionModerator.CollabInterface collab;
    boolean isReady = false;
    private SessionModerator.CollabInterface layercollab;
    private SessionModerator.CollabInterface dbcollab;

	
    
    public MainModerator() {
    	super();
    	
 //   	this.appContext = new ClassPathXmlApplicationContext(new String[] {"/WEB-INF/spring/webmvc-config.xml"});
//        System.out.println("appContext.getBean called! before"+ this.appContext.toString() );
//        this.databaseListener = (DatabaseListener) this.appContext.getBean("databaseListener");
//        System.out.println("appContext.getBean called! - after");
    	
    }

    /**
     * Watch for sync events for marker adds and moves.
     */ 
    @Override
    public synchronized void onSync(String clientId, Map<String, Object> data) {
    	System.out.println("onSync Called clientId : "+clientId);
        String topic = (String)data.get("topic");
        if (topic == null)
        	return;

    	System.out.println("onSync Topic : "+topic);

        
        if (topic.startsWith("coweb.sync.marker")) {
            /* Parse the topic field to find the item after
             * coweb.sync.marker. */
        	String[] seqs = topic.split("\\.");
        	String action = seqs[3];
        	String mid = seqs[4]; /* UUID of pin. */

        	if (action.equals("add") || action.equals("move")) {
                this.updateBot(mid, (Map<String, Object>)data.get("value"));
            }
        }
    }

    private void updateBot(String mid, Map<String, Object> value) {
        value.put("uuid", mid);
        this.collab.postService("zipvisits", value);
    }

    @Override
    public void onSessionEnd() {
        /* When the session ends (all clients leave), we must stop sending
         * the pin drop list to the bot. */
        this.collab = null;
        this.isReady = false;
    }

    @Override
    public boolean canClientJoinSession(String clientId,
            Map<String,Object> data) {
    	System.out.println("canClientJoinSession clientId : "+clientId);
    	System.out.println("canClientJoinSession data : " + data.toString() );

    	return true;
    }
    
    @Override
    public boolean canClientMakeServiceRequest(String svcName, String clientId,
            Map<String, Object> botData) {
        /* Disallow the client from making service requests, since it is not
         * necessary anyway. */
    	System.out.println("canClientMakeServiceRequest : "+svcName);
    	System.out.println("canClientMakeServiceRequest : "+botData.toString() );
        return true;
    }

    @Override
    public boolean canClientSubscribeService(String svcName,
            String clientId) {
        /* Do allow the client to subscribe to the service, since this is how
         * the client will receive the visit count data. */
    	System.out.println("canClientSubscribeService : " + svcName + " clientId : " + clientId   );
        return true;
    }

    @Override
    public void onSessionReady() {
        /* When the session is ready, create a new CollabInterface, so we can
         * talk to the service bot. */
    	System.out.println( "onSessionReady!"  );
        this.collab = this.initCollab("layer");
//        this.layercollab = this.initCollab("layer.999");
//        this.dbcollab = this.initCollab("databaseMonitor");
        this.collab.subscribeService("databaseMonitor");

//        this.layercollab.subscribeService("layer.999");
        
        this.isReady = true;
    }

    @Override
    public void onClientJoinSession(String clientId ){
    	System.out.println("onClientJoinSession clientId : " + clientId   );
    	
    	
    }
    
    @Override
    public void onServiceResponse(String svcName, Map<String, Object> data,
            boolean error, boolean isPub) {
        /* The bot will send us an acknowledge message, but we just
         * ignore it. */
    	System.out.println( "onServiceResponse! svcName:"+svcName );
 //   	System.out.println( "onServiceResponse! data:"+data.toString() );

    	
    	
    	if( svcName.equals(  "databaseMonitor")  ) {
//    		System.out.println( "MainMonitor-onServiceResponse msg from databaseMonitor");
    		if(data.containsKey("layer") && data.get("layer").equals( "999" ) ) {
//        		System.out.println( "MainMonitor-onServiceResponse msg from databaseMonitor");
        		this.collab.postService("layer.999", data);
    			
    		}
    		if(data.containsKey("layer") && data.get("layer").equals( "2014" ) ) {
        		this.collab.postService("layer.2014", data);
    			
    		}
    		
    	} else if( svcName.equals( "layer" ) ) {
    		if( data.get("layer").equals( "999" ) ) {
    			this.collab.postService("layer.999", data);
    		} else if( data.get("layer").equals( "2014" ) ) {
    			this.collab.postService("layer.2014", data);
    		}
    	}
    	
    	
    }
    
    
    public Map getLateJoinState() {
    	System.out.println( "getLateJoinState!" );
		HashMap hm = new HashMap();
		List<Event> result;
		Map<String, String[]> theParams = new HashMap<String, String[]>();
		String[] groupId = { "999"};
		String[] hasEnd = { "false"};
		String[] hasLocation = { "true"};
		theParams.put("groupId", groupId );
		theParams.put("hasEnd", hasEnd );
		theParams.put("hasLocation", hasLocation );
        result = Event.findAllWithParams(theParams);	
		hm.put("layer", Event.toJsonArray( result ) );
//		hm.put("phonebook", arr);
		// hm.put("notepad", notePadText);  <-- If we had a notepad as part of the application.
		return hm;
	}

	

}

