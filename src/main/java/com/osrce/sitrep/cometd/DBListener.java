package com.osrce.sitrep.cometd;


public interface DBListener {

//	public enum dbType { entity, entityStatus, event, srmap };
	
	public void beginTransaction();
	public void endTransaction();
	
	public int databaseOperation(String table, String theOp, Long theId);
	
	

}