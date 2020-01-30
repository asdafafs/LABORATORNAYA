package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Database {
private Connection conn;
public Database() {
	this.conn=null;
}
private boolean openConnection() {
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		this.conn = DriverManager.getConnection("jdbc:mysql://nix:3306/asff?user=LABORATORNAYA&password=LAORATORNAYA");
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		System.out.println("SQL exception"+ e.getMessage());
		e.printStackTrace();
		return false;
		
	}
	return true;
}
 public void closeConnection() {
	 try {
		 if (this.conn != null)this.conn.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 this.conn=null;
 }
 public List<Inspector> getAllInspector(){
	 Statement st = null;
	 ResultSet rs = null;
	 List <Inspector> lInspector = new ArrayList<Inspector>();
	 if(openConnection()) {
		 try {
			 st = conn.createStatement();
			 rs = st.executeQuery("select*from LABORATORNAYA.client");
			 while(rs.next()) {
				 lInspector.add(new Inspector(rs.getInt("idInspector"), 
						 rs.getString("Name"), rs.getString("Surname"), rs.getString("Position"), 
						 rs.getString("Rank"),rs.getString("Conclusion")));
			 }}
		 catch(SQLException e) {
				 System.out.println("SQL exception" + e.getMessage());
				 e.printStackTrace();
				 return null;
			 }
		 finally {
			 try {
				 if(st != null)
					 st.close();
				 closeConnection();}
			 catch(SQLException e ) {
				 e.printStackTrace();
				 
			 }
			 st=null;
			 }
		 }
	 return lInspector;
		 }
 public void newInspector(String Name, String Surname, String Position, String Rank, String Conclusion) {
	 if (openConnection()) {
	 Statement st = null;
	 try {
	 st = conn.createStatement();
	 st.executeUpdate("insert into LABORATORNAYA.Inspector (Name, Surname, Position, Rank, Conclusion)"
	 + "values('" + Name + "','"	 + Surname + "','" + Position + "','" + Rank+ "','" + Conclusion+"');");
	 } catch (SQLException e) {
	 System.out.println("SQl exception" + e.getMessage());
	 e.printStackTrace();
	 } finally {
	 try {
	 if (st != null)
	 st.close();
	 closeConnection();
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	 st = null;
	 }
	 }
	 }	 
 }
