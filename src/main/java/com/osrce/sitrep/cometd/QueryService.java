package com.osrce.sitrep.cometd;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;



@Service
public class QueryService implements QueryEmitter.Listener
{
    @Inject
    private BayeuxServer bayeuxServer;
    @Session
    private LocalSession sender;
    
    
    public void onUpdates(List<QueryEmitter.Update> updates)
    {
        for (QueryEmitter.Update update : updates)
        {
            // Create the channel name using the stock symbol
//            String channelName = "/query/" + update.getSymbol().toLowerCase(Locale.ENGLISH);
            String channelName = "/query/" + update.getQueryid() + "/" + update.getResultid();

            // Initialize the channel, making it persistent and lazy
            bayeuxServer.createIfAbsent(channelName, new ConfigurableServerChannel.Initializer()
            {
                public void configureChannel(ConfigurableServerChannel channel)
                {
                    channel.setPersistent(true);
                    channel.setLazy(true);
                }
            });

            // Convert the Update business object to a CometD-friendly format
            Map<String, Object> data = new HashMap<String, Object>(4);
//            data.put("symbol", update.getSymbol());
//            data.put("oldValue", update.getOldValue());
//            data.put("newValue", update.getNewValue());

            // Publish to all subscribers
            ServerChannel channel = bayeuxServer.getChannel(channelName);
            channel.publish(sender, data, null);
        }
    }
}






