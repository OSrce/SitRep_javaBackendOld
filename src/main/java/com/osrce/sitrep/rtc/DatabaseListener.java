package com.osrce.sitrep.rtc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.coweb.bots.Proxy;
import org.coweb.bots.VanillaBot;
import org.postgresql.PGNotification;

import com.osrce.sitrep.domain.Event;



public class DatabaseListener extends VanillaBot implements Runnable {

    private Proxy proxy = null;
	
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private Connection conn;
	private org.postgresql.PGConnection pgconn;

//	private List<DBListener> listeners = new ArrayList<DBListener>();
	private List<String> botList = new ArrayList<String>();
	
	private boolean onEntity;
	private boolean onEntityStatus;
	private boolean onEvent;
	private boolean onSrmap;
	
	private List<Event> tempEvents = new ArrayList<Event>();
	private Map<Long,String> tempEventsChangeType = new HashMap<Long, String>();
	
	
	//CONSTRUCTOR
	public DatabaseListener()  {
		// TODO Auto-generated constructor stub
		onEntity = false;
		onEntityStatus = false;
		onEvent = false;
		onSrmap = false;
		botList.add("layer.999");
		try {
	       	this.start();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	

	public void start() throws SQLException
	{
		this.conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/sitrepdev","sitrepadmin","");
		this.pgconn = (org.postgresql.PGConnection)conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN entity");
		stmt.execute("LISTEN entity_status");
		stmt.execute("LISTEN event");
		stmt.execute("LISTEN srmap");
		stmt.close();
		System.out.println("Starting Database Listener!");
		
		run();
	}

	public void stop()
	{
		//scheduler.shutdownNow();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
//			System.out.println("Running!");
			// issue a dummy query to contact the backend
			// and receive any pending notifications.
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1");
			rs.close();
			stmt.close();

			org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
			if (notifications != null) {
				tempEvents.clear();
				tempEventsChangeType.clear();
				for (int i=0; i<notifications.length; i++) {
					String theParam = notifications[i].getParameter();
					String[] rowInfo = theParam.split(":");
					Long rowId = Long.parseLong( rowInfo[1] );
//					System.out.println("Got notification: " + notifications[i].getName() + " Operation:" + rowInfo[0] + " Row ID: "+ rowInfo[1] );
					//Figure out which table and row it is and get it.
					if(notifications[i].getName().equals( "event" ) ) {
						System.out.println("Got notification: " + notifications[i].getName() + " Operation:" + rowInfo[0] + " Row ID: "+ rowInfo[1] );
						System.out.println("Event rowId : "+rowId.toString() );
						Event e = Event.findEvent(rowId);
						tempEvents.add(e);
						tempEventsChangeType.put( rowId, rowInfo[0]  );
					}
					
				}	
				// Now check each Temp List and if not empty send to layer looking for it.
				if( ! tempEvents.isEmpty() ) {
					System.out.println("tempEvents size = "+ tempEvents.size() );
		            DatabaseListener bot = DatabaseListener.this;
		            Map<String, Object> reply = new HashMap<String, Object>();
		            reply.put("layer", "999");
		            reply.put("event", ( List<Event>) tempEvents);
		            reply.put("operations", ( Map<Long,String> ) tempEventsChangeType);

		            
		            bot.proxy.publish(bot, reply);
					System.out.println("bot published on svc: databaseMonitor!" );

					
				}
				
			}

			// wait a while before checking again for new
			// notifications
//			Thread.sleep(500);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
	//	} catch (InterruptedException ie) {
//			ie.printStackTrace();
		}
        long howLong = 1000;  //random.nextInt(1000);
        scheduler.schedule(this, howLong, TimeUnit.MILLISECONDS);
	}
		

	@Override
	public void setProxy(Proxy proxy) {
	       this.proxy = proxy;		
	}
	

}
