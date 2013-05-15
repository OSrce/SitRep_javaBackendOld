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
public class LayerBot2 extends VanillaBot {

	
    private Proxy proxy = null;
    
    private Layer layer;
    private Map<String, String[]> theParams;
    
	private Map<Long,EntityStatus> eventList; 

	class updateObject {
		String operation;
		EntityStatus e;
		updateObject(String op, EntityStatus e) {
			this.operation = op;
			this.e = e;
		}
		
		public String toString() {
			return "{ \"operation\":\""+this.operation+"\", \"item\":"+e.toJson() + "}";
		}
		
	}
	

    /* This is necessary for all bots. The proxy is necessary for
     * communication. */
    public void setProxy(Proxy proxy) {
       this.proxy = proxy;
    }

    @Override
	public void onShutdown() {
     
    	
	}
    

    

    /*
     * Called upon session startup.
     */
    @Override
	public void init() {
    	System.out.println("LayerBot2 init called!");
//        this.visits.clear();
//        this.timer = new Timer();
//        this.timer.scheduleAtFixedRate(new BotTimer(), 0, 5000);
    	
//        System.out.println("appContext.getBean called! before"+ this.appContext.toString() );
//        this.databaseListener = (DatabaseListener) this.appContext.getBean("databaseListener");
//        System.out.println("appContext.getBean called! - after");
        this.initdb( new Long(999) );
    }

    public void initdb(Long layerId) {
    	System.out.println("LayerBot2 initdb called for : " + layerId);
    	this.layer = Layer.findLayer(layerId);
    	
		this.theParams = new HashMap<String, String[]>();
		String[] groupId = { "2014"};
		String[] hasEnd = { "false"};
		String[] hasLocation = { "true"};
		this.theParams.put("entity.groupId", groupId );
		this.theParams.put("hasEnd", hasEnd );
		this.theParams.put("hasLocation", hasLocation );
//		hm.put("layer", EntityStatus.toJsonArray( result ) );
    	    	
    	
//    	LayerBot2 bot = LayerBot2.this;
//    	databaseListener.addListener(bot);
    	
    	
    }
    
    
    
    /*
     * Handle messages from the moderator. The moderator will send us marker
     * positions via this mechanism.
     */
	public synchronized void onRequest(Map<String, Object> params, String replyToken,
            String username) {

    	System.out.println("LayerBot2 onRequest username : "+username);
    	System.out.println("LayerBot2 onRequest params : "+params.toString() );

        /* The reply token is used to uniquely identify which client sent the
         * message and to distinguish between multiple messages if the client
         * has sent more than one to this service. It must be considered
         * opaque and not be altered. */
        Map<String, Object> reply = new HashMap<String, Object>();
        reply.put("reply", "acknowledged");
    	
    	
    	if(params.containsKey("type") &&  params.get("type").equals( "initialRequest")  ) {
    		System.out.println("LayerBot2 initialRequest!");
    		if( this.eventList == null ) {
    			this.eventList = new HashMap<Long, EntityStatus>();
    			List<EntityStatus> tempList = EntityStatus.findAllWithParams(theParams);
    	        Iterator<EntityStatus> iterator = tempList.iterator();
        		while (iterator.hasNext()) {
        			EntityStatus e = iterator.next();
        			this.eventList.put( e.getId(), e );
        		}
    	        
    		}
    		
    		reply.put("data", EntityStatus.toJsonArray( this.eventList.values() ) );	
    	}

    	
    	if(params.containsKey("itemList") && this.eventList != null ) {
    		System.out.println("LayerBot2 Update from DatabaseListener!");
    		List<EntityStatus> tempList =  ( List<EntityStatus>) params.get("itemList");
    		Map<Long,String> tempListOperations = ( Map<Long,String> ) params.get("operations");
    		Map<String, Object> dataUpdates = new HashMap<String, Object>();
    		List<updateObject> updateObjectList = new ArrayList<updateObject>();
    		Iterator<EntityStatus> iterator = tempList.iterator();
    		while (iterator.hasNext()) {
    			EntityStatus e = iterator.next();
    			//CHECK TO SEE IF NEW ITEM IN existing eventList;
    			if( e != null ) {
    				if(this.eventList.containsKey( e.getId() ) ) {
    					if( tempListOperations.get(e.getId()).equals("DELETE")  ) {
    						//SEND DELETE MSG
    						System.out.println("LayerBot2 DELETE ID:"+e.getId() );
    						updateObjectList.add( new updateObject( "DELETE", e));
    						this.eventList.remove(e.getId() );
    					} else if( tempListOperations.get(e.getId()).equals("UPDATE")  ) {
    						if ( matchesLayer(e) ) {
    							//SEND UPDATE MSG
    							System.out.println("LayerBot2 UPDATE ID:"+e.getId() );
    							updateObjectList.add( new updateObject("UPDATE", e));
    							this.eventList.remove(e.getId() ); 
    							this.eventList.put(e.getId(), e);
    						} else {
    							//NO LONGER MATCHES LAYER SO SEND DELETE MSG
    							System.out.println("LayerBot2 DELETE ID:"+e.getId() );
    							updateObjectList.add( new updateObject("DELETE", e) );
        						this.eventList.remove(e.getId() );
    						}
    					} else if( tempListOperations.get(e.getId()).equals("INSERT")  ) {
							System.out.println("LayerBot2 INSERT, BUT ID IS ALREADY IN eventlist! ID :"+e.getId() );
    					}
    				// ELSE e is not already in eventList
    				} else {
    					if( tempListOperations.get(e.getId()).equals("DELETE")  ) {
    						System.out.println("LayerBot2 DELETE, ID NOT IN eventList ID:"+e.getId() );
    					} else if( tempListOperations.get(e.getId()).equals("UPDATE")  ) {
							System.out.println("LayerBot2 UPDATE, ID NOT IN eventList ID:"+e.getId() );
							if ( matchesLayer(e) ) {
								System.out.println("LayerBot2 INSERT ID:"+e.getId() );
								updateObjectList.add( new updateObject("INSERT", e));
								this.eventList.put(e.getId(), e);
							}    						
    					} else if( tempListOperations.get(e.getId()).equals("INSERT")  ) {
							System.out.println("LayerBot2 INSERT, ID NOT IN eventList ID:"+e.getId() );
							if ( matchesLayer(e) ) {
								System.out.println("LayerBot2 INSERT ID:"+e.getId() );
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

	public boolean matchesLayer(EntityStatus e) {
		if ( e.getEntity().getGroupId() == 2014 && ! e.isHasEnd() && e.isHasLocation()  ) {
			return true;
		} else {
			return false;
		}
		
	}
    
    

}

