package com.osrce.sitrep.cometd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.postgresql.PGNotification;

public class DatabaseListener implements Runnable {

	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private Connection conn;
	private org.postgresql.PGConnection pgconn;

	//CONSTRUCTOR
	public DatabaseListener()  {
		// TODO Auto-generated constructor stub

	}

	public void start() throws SQLException
	{
		this.conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/sitrep","sitrepadmin","");
		this.pgconn = (org.postgresql.PGConnection)conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN entity_status");
		stmt.close();
		
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
				for (int i=0; i<notifications.length; i++) {
					System.out.println("Got notification: " + notifications[i].getName() + " Param:" + notifications[i].getParameter() );
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
		

}
