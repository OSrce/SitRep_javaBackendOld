package com.osrce.sitrep.rtc;

import org.coweb.bots.VanillaBot;
import org.coweb.bots.Proxy;

import com.osrce.sitrep.domain.Event;

import java.util.List;
import java.util.Timer;
import java.util.Map;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Random;

/**
 * Simple bot that listens for messages from the moderator and sends out
 * artificially generated visitor counts every five seconds.
 */
public class DBBot extends VanillaBot {

    private Proxy proxy = null;
    private int total = 0;
    private Timer timer = null;
    private Map<String, Object> visits = new HashMap<String, Object>();
    private Random r = new Random();

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

    /*
     * Called upon session startup.
     */
    @Override
	public void init() {
        this.visits.clear();
        this.timer = new Timer();
 //       this.timer.scheduleAtFixedRate(new BotTimer(), 0, 5000);
	}

    /*
     * Handle messages from the moderator. The moderator will send us marker
     * positions via this mechanism.
     */
	public synchronized void onRequest(Map<String, Object> params, String replyToken,
            String username) {

    	System.out.println("DBBot onRequest username : "+username);
    	System.out.println("DBBot onRequest params : "+params.toString() );

    
/*    	
        String uuid = (String)params.get("uuid");
        int visitCount = this.getVisitCount(params);
        this.visits.put(uuid, visitCount);
*/
        /* The reply token is used to uniquely identify which client sent the
         * message and to distinguish between multiple messages if the client
         * has sent more than one to this service. It must be considered
         * opaque and not be altered. */
        Map<String, Object> reply = new HashMap<String, Object>();
        reply.put("reply", "acknowledged");
        
        // LOOKUP USER TO DETERMINE ACL FOR USER - LAYER.
        //  access : NONE,READONLY,READWRITE
        
        System.out.println("DBBot params_id type = "+params.get("id").getClass().getName() );
    	Long requestedLayerId = (Long) params.get("id");
    	if(requestedLayerId == 999 ) {
    		System.out.println("DBBot recieved request from client for Layer 999!");
 
/*    		
    		List<Event> result;
    		Map<String, String[]> theParams = new HashMap<String, String[]>();
    		String[] groupId = { "999" };
    		String[] hasEnd = { "false"};
    		String[] hasLocation = { "true"};
    		theParams.put("groupId", groupId );
    		theParams.put("hasEnd", hasEnd );
    		theParams.put("hasLocation", hasLocation );
            result = Event.findAllWithParams(theParams);
    		reply.put("layer", Event.toJsonArray( result ) );
*/
    		reply.put("layer", "999");
    		reply.put("type", "initialRequest");
		
//    		reply.put("layer", result );

    		
    	}
        
        
        this.proxy.reply(this, replyToken, reply);
        
	}

    private int getVisitCount(Map<String, Object> params) {
        /* params contains the latitude and longitude of the pin drop
         * location, but the truth is that we artificially generate the visit
         * count, so we don't really look at the lat/lon location. */
        synchronized (this.r) {
            return r.nextInt(1000);
        }
    }

    private void updateVisits() {
        /* We artificially increment the visit count, as if over time,
         * more visitors visit the location in question. */
        for (String uuid: this.visits.keySet()) {
            int cnt = (Integer)this.visits.get(uuid);
            synchronized (this.r) {
                cnt += this.r.nextInt(10);
            }
            this.visits.put(uuid, cnt);
        }
    }

    private class BotTimer extends TimerTask {
        @Override
        public void run() {
            DBBot bot = DBBot.this;
            bot.updateVisits();
//            bot.proxy.publish(bot, bot.visits);
        }
    }

}

