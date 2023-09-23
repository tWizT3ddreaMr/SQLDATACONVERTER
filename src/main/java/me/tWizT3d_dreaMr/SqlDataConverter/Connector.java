package me.tWizT3d_dreaMr.SqlDataConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Connector {
	  private Connection connection;
	  private String host, database, username, password;
	  private int port;
public Connector(String host,int port, String database,String username,String password) throws ClassNotFoundException, SQLException {
	  	this.host=host;
	  	this.port=port;
	  	this.database=database;
	  	this.username=username;
	  	this.password=password;
	  	
	  	openConnection();
	  	
	  	
}

	public void openConnection() throws ClassNotFoundException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException classNotFoundException)
		{
			try{
			Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException classNotFoundException2)
			{
				System.out.println("DoublFail");
			}
		}
		try
		{
		connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?" + 
		        "&autoReconnect=true&wait_timeout=31536000&interactive_timeout=31536000&useUnicode=true&characterEncoding=utf8&useSSL=" + 
		        "false", this.username, this.password);
		}
		catch (Exception e)
		{
			System.out.println("Failed Database Connection: " + e);
		}
		
	}
	public Connection getConnection() {
		return connection;
	}
	public static void conversion() {

            	/*ResultSet results=allResults();
                Connection conn=null;
                
            	try {
            		//results.//
				while(results.next()) {
					final long date =Long.parseLong(results.getString("Time"));
				    final String buuid=results.getString("PlayerUUID");
				    final String item=results.getString("Type");
				    final String price=results.getString("Price");
				    final int x=results.getInt("SignX");
					final int y=results.getInt("SignY");
					final int z=results.getInt("SignZ");
					final String world=results.getString("SignWorld");
					
                	
                    try {
                        PreparedStatement stmt = conn.prepareStatement("INSERT INTO shop_transaction (t_type, price, amount, item, barter_item) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                        stmt.setNull(1, Types.VARCHAR);
                        stmt.setDouble(2, Double.parseDouble(price));
                        stmt.setNull(3, Types.INTEGER);
                        stmt.setString(4, item);
                        stmt.setNull(5, Types.VARCHAR);

                        stmt.execute();

                        ResultSet keys = stmt.getGeneratedKeys();
                        keys.next();
                        int transactionID = keys.getInt(1);
                        stmt.close();

                        stmt = conn.prepareStatement("INSERT INTO shop_action(ts, player_uuid, owner_uuid, player_action, transaction_id, shop_world, shop_x, shop_y, shop_z) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");
                        stmt.setTimestamp(1, new Timestamp(date));
                        stmt.setString(2, buuid);
                        stmt.setNull(3, Types.VARCHAR);
                        stmt.setNull(4, Types.VARCHAR);
                        stmt.setInt(5, transactionID);
                        stmt.setString(6, world);
                        stmt.setInt(7, x);
                        stmt.setInt(8, y);
                        stmt.setInt(9, z);
                        stmt.execute();
                        conn.close();
                        return;
                    } catch (SQLException e) {
                        System.out.println("[Shop] SQL error occurred while trying to log transaction.");
                        e.printStackTrace();
                        conn.close();
                        return;
                    }
            
				}
				} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}*/
    	  	 
    }
	public ResultSet ResultFromStatement(String sta){
	  	ResultSet result=null;
	  	/*
	  	 * 
SELECT id, uuid, registered, name, times_kicked
FROM plan_users
ORDER BY (id) DESC
LIMIT 1
	  	 * 
	  	 * */
	  		try {PreparedStatement preparedStatement =
	  		        connection.prepareStatement(sta);
	  		result= preparedStatement.executeQuery();
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		return result;
		
	}
	public void ExecuteStatement(PreparedStatement stmt){
        try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return;
	  	/*
	  	 * 
SELECT id, uuid, registered, name, times_kicked
FROM plan_users
ORDER BY (id) DESC
LIMIT 1
	  	 * 
	  	 * */
		
	}
}