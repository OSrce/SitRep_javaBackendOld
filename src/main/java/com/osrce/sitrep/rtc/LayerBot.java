package com.osrce.sitrep.rtc;

import org.coweb.bots.VanillaBot;
import org.coweb.bots.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.Map;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Random;

import com.osrce.sitrep.domain.*;


/**
 * Simple bot that listens for messages from the moderator and sends out
 * artificially generated visitor counts every five seconds.
 */
public class LayerBot extends VanillaBot {

	
    private Proxy proxy = null;
    private int total = 0;
    private Timer timer = null;
    private Map<String, Object> visits = new HashMap<String, Object>();
    private Random r = new Random();
    
    private Layer layer;
    private Event event;
    private Map<String, String[]> theParams;
    
	private Map<Long,Event> eventList; 

	class updateObject {
		String operation;
		Event e;
		updateObject(String op, Event e) {
			this.operation = op;
			this.e = e;
		}
		
		public String toString() {
			return "{ \"operation\":\""+this.operation+"\", \"event\":"+e.toJson() + "}";
		}
		
	}
	

    /* This is necessary for all bots. The proxy is necessary for
     * communication. */
    public void setProxy(Proxy proxy) {
       this.proxy = proxy;
    }

    @Override
	public void onShutdown() {
        if (null != this.timer) {
            this.timer.cancel();
            this.timer = null;
        }
	}
    
    public String getLayerName() {
    	return this.layer.getName();
    }
    

    /*
     * Called upon session startup.
     */
    @Override
	public void init() {
    	System.out.println("LayerBot init called!");
//        this.visits.clear();
//        this.timer = new Timer();
//        this.timer.scheduleAtFixedRate(new BotTimer(), 0, 5000);
    	
//        System.out.println("appContext.getBean called! before"+ this.appContext.toString() );
//        this.databaseListener = (DatabaseListener) this.appContext.getBean("databaseListener");
//        System.out.println("appContext.getBean called! - after");
        this.initdb( new Long(999) );
    }

    public void initdb(Long layerId) {
    	System.out.println("LayerBot initdb called for : " + layerId);
    	layer = Layer.findLayer(layerId);
    	
		this.theParams = new HashMap<String, String[]>();
		String[] groupId = { "999"};
		String[] hasEnd = { "false"};
		String[] hasLocation = { "true"};
		this.theParams.put("groupId", groupId );
		this.theParams.put("hasEnd", hasEnd );
		this.theParams.put("hasLocation", hasLocation );
//		hm.put("layer", Event.toJsonArray( result ) );
    	    	
    	
//    	LayerBot bot = LayerBot.this;
//    	databaseListener.addListener(bot);
    	
    	
    }
    
    
    
    /*
     * Handle messages from the moderator. The moderator will send us marker
     * positions via this mechanism.
     */
	public synchronized void onRequest(Map<String, Object> params, String replyToken,
            String username) {

    	System.out.println("LayerBot onRequest username : "+username);
    	System.out.println("LayerBot onRequest params : "+params.toString() );

        /* The reply token is used to uniquely identify which client sent the
         * message and to distinguish between multiple messages if the client
         * has sent more than one to this service. It must be considered
         * opaque and not be altered. */
        Map<String, Object> reply = new HashMap<String, Object>();
        reply.put("reply", "acknowledged");
    	
    	
    	if(params.containsKey("type") &&  params.get("type").equals( "initialRequest")  ) {
    		System.out.println("LayerBot initialRequest!");
    		if( this.eventList == null ) {
    			this.eventList = new HashMap<Long, Event>();
    			List<Event> tempList = Event.findAllWithParams(theParams);
    	        Iterator<Event> iterator = tempList.iterator();
        		while (iterator.hasNext()) {
        			Event e = iterator.next();
        			this.eventList.put( e.getId(), e );
        		}
    	        
    		}
    		
    		reply.put("data", Event.toJsonArray( this.eventList.values() ) );	
    	}

    	
    	if(params.containsKey("event") && this.eventList != null ) {
    		System.out.println("LayerBot Update from DatabaseListener!");
    		List<Event> tempList =  ( List<Event>) params.get("event");
    		Map<Long,String> tempListOperations = ( Map<Long,String> ) params.get("operations");
    		Map<String, Object> dataUpdates = new HashMap<String, Object>();
    		List<updateObject> updateObjectList = new ArrayList<updateObject>();
    		Iterator<Event> iterator = tempList.iterator();
    		while (iterator.hasNext()) {
    			Event e = iterator.next();
    			//CHECK TO SEE IF NEW ITEM IN existing eventList;
    			if( e != null ) {
    				if(this.eventList.containsKey( e.getId() ) ) {
    					if( tempListOperations.get(e.getId()).equals("DELETE")  ) {
    						//SEND DELETE MSG
    						System.out.println("LayerBot DELETE ID:"+e.getId() );
    						updateObjectList.add( new updateObject( "DELETE", e));
    						this.eventList.remove(e.getId() );
    					} else if( tempListOperations.get(e.getId()).equals("UPDATE")  ) {
    						if ( matchesLayer(e) ) {
    							//SEND UPDATE MSG
    							System.out.println("LayerBot UPDATE ID:"+e.getId() );
    							updateObjectList.add( new updateObject("UPDATE", e));
    							this.eventList.remove(e.getId() ); 
    							this.eventList.put(e.getId(), e);
    						} else {
    							//NO LONGER MATCHES LAYER SO SEND DELETE MSG
    							System.out.println("LayerBot DELETE ID:"+e.getId() );
    							updateObjectList.add( new updateObject("DELETE", e) );
        						this.eventList.remove(e.getId() );
    						}
    					} else if( tempListOperations.get(e.getId()).equals("INSERT")  ) {
							System.out.println("LayerBot INSERT, BUT ID IS ALREADY IN eventlist! ID :"+e.getId() );
    					}
    				// ELSE e is not already in eventList
    				} else {
    					if( tempListOperations.get(e.getId()).equals("DELETE")  ) {
    						System.out.println("LayerBot DELETE, ID NOT IN eventList ID:"+e.getId() );
    					} else if( tempListOperations.get(e.getId()).equals("UPDATE")  ) {
							System.out.println("LayerBot UPDATE, ID NOT IN eventList ID:"+e.getId() );
							if ( matchesLayer(e) ) {
								System.out.println("LayerBot INSERT ID:"+e.getId() );
								updateObjectList.add( new updateObject("INSERT", e));
								this.eventList.put(e.getId(), e);
							}    						
    					} else if( tempListOperations.get(e.getId()).equals("INSERT")  ) {
							System.out.println("LayerBot INSERT, ID NOT IN eventList ID:"+e.getId() );
							if ( matchesLayer(e) ) {
								System.out.println("LayerBot INSERT ID:"+e.getId() );
								updateObjectList.add( new updateObject( "INSERT", e) );
								this.eventList.put(e.getId(), e);		
							}
    					}    				

    				}
    			}  // END e not null
    		}
    		dataUpdates.put("updateObjects", updateObjectList);
    		this.proxy.publish(this, dataUpdates);
    	}
    	


        this.proxy.reply(this, replyToken, reply);
        
	}

	public boolean matchesLayer(Event e) {
		if (e.getGroupId() == 999 && ! e.isHasEnd() && e.isHasLocation()  ) {
			return true;
		} else {
			return false;
		}
		
	}
    
    

}

