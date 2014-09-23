package database;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class MainLogic {
	
	
	 public ResultSet  database(String table){
		 ResultSet rs = null;
	        Connection connection = null;
	        Statement statement = null; 
	        String query = "select * from "+table+";";
	        try {           
	            connection = JDBCMySQLConnection.getConnection();
	         
	            statement = connection.createStatement();
	            rs =  statement.executeQuery(query);
	             
	           System.out.println(rs);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return rs;
	        
	    }
	 
	 
	 
	 public void insertCounter(String word,int[] counts)
	 {
		 Connection connection = null;
		 connection = JDBCMySQLConnection.getConnection();
		 String query = " insert into counter(word, pcount,ncount,neucount)" + " values (?, ?,?,?)";
		 try
		 {
		 PreparedStatement preparedStmt = connection.prepareStatement(query);
		 preparedStmt.setString(1, word);
		 preparedStmt.setInt(2,counts[2]);
		 preparedStmt.setInt(3,counts[1]);
		 preparedStmt.setInt(4,counts[0]);
		 preparedStmt.execute();
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	     
	 }
	 
	 
	 
	 
	 
	 public void insertwords(String word,int polarity,int lid)
	 {
		 Connection connection = null;
		 connection = JDBCMySQLConnection.getConnection();
		 String query = " insert into counter(word, polarity,lid)" + " values (?, ?,?)";
		 try
		 {
		 PreparedStatement preparedStmt = connection.prepareStatement(query);
		 preparedStmt.setNString(1, word);
		 preparedStmt.setInt(2,polarity);
		 preparedStmt.execute();
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	     
	 }
	 
	 
	 
	 public void insertTweets(String tweet,int polarity,String location,String tdate)
	 {
		 Connection connection = null;
		 connection = JDBCMySQLConnection.getConnection();
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");                       
	     
		 String query = " insert into tweets (tweet,polarity,location)" + " values ( ?,?,?)";
		 try
		 {
		 //java.sql.Date d=(Date) new java.sql.Date(format.parse(tdate).getTime()); 
		 PreparedStatement preparedStmt = connection.prepareStatement(query);
		 preparedStmt.setNString(1, tweet);
		 preparedStmt.setInt(2, polarity);
		 preparedStmt.setNString(3, location);
		 //preparedStmt.setDate(4, d);
		 preparedStmt.execute();
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	     
	 }
	 
	 
	 
	 
	 public void insertcampign(int campignid,int tweet,String location,String word,String from1,String to1)
	 {
		 Connection connection = null;
		 connection = JDBCMySQLConnection.getConnection();
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");                       
	     
		 String query = " insert into campign (campignid,tweet,location,word,from1,to1)" + " values (?, ?,?,?,?,?)";
		 try
		 {
		 java.sql.Date f1=(Date) new java.sql.Date(format.parse(from1).getTime()); 
		 java.sql.Date t1=(Date) new java.sql.Date(format.parse(to1).getTime()); 
		 PreparedStatement preparedStmt = connection.prepareStatement(query);
		 preparedStmt.setInt(1, campignid);
		 preparedStmt.setInt(2, tweet);
		 preparedStmt.setNString(3, location);
		 preparedStmt.setNString(4, word);
		 preparedStmt.setDate(5, f1);
		 preparedStmt.setDate(6, t1);
		 preparedStmt.execute();
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	     
	 }
	 
	 
	 
	 
	 
	
}



