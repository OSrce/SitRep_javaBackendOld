package com.osrce.sitrep.cometd;

/*
import com.osrce.sitrep.domain.SrQueryMonitor;
import com.osrce.sitrep.domain.SrQueryMonitorResults;
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class QueryEmitter implements Runnable
{

	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<String> symbols = new ArrayList<String>();
    private final Map<String, Float> values = new HashMap<String, Float>();
    private final List<Listener> listeners = new CopyOnWriteArrayList<Listener>();

    public QueryEmitter()
    {
        symbols.addAll(Arrays.asList("ORCL", "MSFT", "GOOG", "YHOO", "FB"));
        values.put("ORCL", 29.94f);
        values.put("MSFT", 27.10f);
        values.put("GOOG", 655.37f);
        values.put("YHOO", 17.82f);
        values.put("FB", 21.33f);
    }

    public List<Listener> getListeners()
    {
        return listeners;
    }

    public void start()
    {
    	
        run();
    }

    public void stop()
    {
        scheduler.shutdownNow();
    }

    public void run()
    {
        Random random = new Random();

        List<Update> updates = new ArrayList<Update>();

        // Randomly choose how many stocks to update
        int howMany = 1; //random.nextInt(symbols.size()) + 1;
        for (int i = 0; i < howMany; ++i)
        {
            // Randomly choose which one to update
            int which = random.nextInt(symbols.size());
            String symbol = symbols.get(which);
            float oldValue = values.get(symbol);

            // Randomly choose how much to update
            boolean sign = random.nextBoolean();
            float howMuch = random.nextFloat();
            float newValue = oldValue + (sign ? howMuch : -howMuch);

            // Store the new value
            values.put(symbol, newValue);
            
            int queryid = 1;
            int resultid = 10;
            String action = "insert";
            
            
            // CREATE NEW UPDATE :
            // Should be queryid, resultid, action   
            updates.add(new Update(queryid, resultid, action));
        }

        // Notify the listeners
        for (Listener listener : listeners)
        {
 //           listener.onUpdates(updates);
        }

        // Randomly choose how long for the next update
        // We use a max delay of 1 second to simulate a high rate of updates
        long howLong = 1000;  //random.nextInt(1000);
        scheduler.schedule(this, howLong, TimeUnit.MILLISECONDS);
    }

    public static class Update
    {
    	private final int queryid;
    	private final int resultid;
        private final String action;
//        private final float oldValue;
//        private final float newValue;

        public Update(int queryid, int resultid, String action)
        {
            this.queryid = queryid;
            this.resultid = resultid;
            this.action = action;
        }

        public int getQueryid()
        {
            return queryid;
        }

        public int getResultid()
        {
            return resultid;
        }

        public String getAction()
        {
            return action;
        }
    }

    public interface Listener extends EventListener
    {
        void onUpdates(List<Update> updates);
    }


}


