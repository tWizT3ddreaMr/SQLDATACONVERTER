package me.tWizT3d_dreaMr.SqlDataConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main  extends org.bukkit.plugin.java.JavaPlugin
{
public Connector C1;
public Connector C2;
public int userID, sessionID, worldtimeID;

public void onEnable() {
	addDefaultConnection("Origin");
	addDefaultConnection("Out");
	getConfig().options().copyDefaults(true);
	saveConfig();
	try {
		C1=Connect("Origin");
		C2=Connect("Out");
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	userID=1;
	sessionID=1;
	worldtimeID=1;
	try {
		UserConvert();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
private void addDefaultConnection(String n) {
	getConfig().addDefault(n+".host", "host");
	getConfig().addDefault(n+".port", 69420);
	getConfig().addDefault(n+".database", "database");
	getConfig().addDefault(n+".username", "username");
	getConfig().addDefault(n+".password", "password");
}
private Connector Connect(String n) throws ClassNotFoundException, SQLException {
	String h= getConfig().getString(n+".host");
	int po= getConfig().getInt(n+".port");
	String d= getConfig().getString(n+".database");
	String u= getConfig().getString(n+".username");
	String p= getConfig().getString(n+".password");
	return new Connector(h,po,d,u,p);
}

public void UserConvert() throws SQLException {
	Connection connection=C2.getConnection();
	ResultSet result= C1.ResultFromStatement("SELECT uuid, value FROM CombinedValue");
	while(result.next()) {
		
		String uuid=result.getString("uuid");
		long l=result.getInt("value");
	
		plan_user(connection, uuid);
		plan_sessions(connection, l);
		plan_world_times(connection);
	}
}
public void plan_user(Connection con,String uuid) throws SQLException {
    userID++;
    PreparedStatement stmt = con.prepareStatement("INSERT INTO plan_users (id, uuid, registered, name, times_kicked) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
    stmt.setInt(1, userID);
    stmt.setString(2, uuid);
    stmt.setLong(3, 1653944579333l);
    stmt.setString(4, "Finmine");
    stmt.setInt(5, 0);
    C2.ExecuteStatement(stmt);

}
public void plan_sessions (Connection con, long l) throws SQLException {
	sessionID++;
	PreparedStatement stmt = con.prepareStatement("INSERT INTO plan_sessions (id, user_id, server_id, session_start, session_end, mob_kills, deaths, afk_time, join_address_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
    stmt.setInt(1, sessionID);
    stmt.setInt(2, userID);
    stmt.setInt(3, 1);
    stmt.setLong(4, 0);
    stmt.setLong(5, l*1000);
    stmt.setInt(6, 0);
    stmt.setInt(7, 0);
    stmt.setLong(8, 0);
    stmt.setInt(9, 1);
    C2.ExecuteStatement(stmt);
	
}
public void plan_world_times(Connection con) throws SQLException {
	worldtimeID++;
	PreparedStatement stmt = con.prepareStatement("INSERT INTO plan_world_times (id, world_id, user_id, server_id, session_id, survival_time, creative_time, adventure_time, spectator_time) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
    stmt.setInt(1, worldtimeID);
    stmt.setInt(2, 1);
    stmt.setInt(3, userID);
    stmt.setInt(4, 1);
    stmt.setInt(5, sessionID);
    stmt.setLong(6, 0);
    stmt.setLong(7, 0);
    stmt.setLong(8, 0);
    stmt.setLong(9, 0);
    C2.ExecuteStatement(stmt);
}

}
