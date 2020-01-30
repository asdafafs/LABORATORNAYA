package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Database {
private Connection conn;
public Database() {
	this.conn=null;
}
/**
* open connection
*/
private boolean openConnection() {
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Scanner text2=new Scanner(System.in);
		String login = text2.next();
		Scanner text=new Scanner(System.in);
		String password = text.next();
		this.conn = DriverManager.getConnection("jdbc:mysql://localhost/anton?user="+login+"&password="+password+"");
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		System.out.println("SQL exception"+ e.getMessage());
		e.printStackTrace();
		return false;
		
	}
	return true;
}
/**
* close connection
*/
 public void closeConnection() {
	 try {
		 if (this.conn != null)this.conn.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 this.conn=null;
 }
 
 /**
 * return all client
 * @return List<Client>
 */
 public List<Inspector> getAllInspector(){
	 Statement st = null;
	 ResultSet rs = null;
	 List <Inspector> lInspector = new ArrayList<Inspector>();
	 if(openConnection()) {
		 try {
			 st = conn.createStatement();
			 rs = st.executeQuery("select*from anton.Inspector");
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
 /**
 * return Inspector on idInspector
 ** @param idc for id Inspector
 * @return new Inspector
 */
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

