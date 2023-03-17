package com.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import user.AppConstants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
	
public class chats {
	public static JSONArray getChats(String sender,String reciever,int msgcount) {
		JSONArray chats= new JSONArray();
//		List<HashMap<String, String>> chats= new JSONArray();
		String currentDate=AppConstants.getCurrentTime();
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select message,sender,reciever,sent_time,recieved_time from messages where (sender=\""+sender+"\" and reciever=\""+reciever+"\" or sender=\""+reciever+"\" and reciever=\""+sender+"\") and sent_time<\""+currentDate+"\" order by sent_time desc limit "+msgcount+",15";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			int i=1;
			while(rs.next()) {
//				if(rs.getDate(5)==null) continue;
				
				JSONObject chat = new JSONObject();
				Date date,rdate;
				String time,rtime=null;
				String contact;
								
				Time t,rt;
				
				contact=rs.getString(2);
				t=rs.getTime(4);
				date=(rs.getDate(4));
				if(contact.equals(sender)) {
//					t=rs.getTime(4);
//					date=(rs.getDate(4));	
					chat.put("type", "sender");
				}
				else {
//					t=rs.getTime(5);
//					date=(rs.getDate(5));	
					chat.put("type", "reciever");
				}
				java.util.Date gettime1=new java.util.Date(t.getTime());
				DateFormat sdf1=new SimpleDateFormat("hh:mm aa");
				time=sdf1.format(gettime1);
				rt=rs.getTime(5);
				rdate=(rs.getDate(5));
				if(rt!=null) {
				java.util.Date gettime2=new java.util.Date(rt.getTime());
				DateFormat sdf2=new SimpleDateFormat("hh:mm aa");
				rtime=sdf2.format(gettime2);}
				System.out.println(rs.getString(1));
				chat.put("rec-time", rtime);
				if(rdate==null)
					chat.put("rec-date", "");
				else
					chat.put("rec-date", rdate.toString());
				chat.put("time", time);
				chat.put("date", date.toString());
				chat.put("msg", rs.getString(1));
				
				chats.add(chat);
				i++;
				
			}
			st.close();
			con.close();
		}
		catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(chats);
		return chats;
	}
	
	public static int updatechat(String sender,String reciever,String txt) {
		String chat=null;
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="insert into messages (message,sender,reciever,sent_time) values (\""+txt+"\",\""+sender+"\",\""+reciever+"\",now())";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			int c=ps.executeUpdate();
			ps.close();
			con.close();
			return c;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public static int updateState(String cname) {
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		int c=0;
		String query="update users set msg_status=1 where uid=\""+cname+"\"";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			c=ps.executeUpdate();
			return c;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
public static int updateMyStatus(String user) {
	String url=AppConstants.url;
	String dbname=AppConstants.userName;
	String dbpass=AppConstants.password;
	String driver=AppConstants.driver;
	int c=0;
	String query="update users set msg_status=0 where uid=\""+user+"\"";		
	try {
		Class.forName(driver);
		
		Connection con=DriverManager.getConnection(url,dbname,dbpass);
//		System.out.println("Connected 	");
		PreparedStatement ps=con.prepareStatement(query);
		c=ps.executeUpdate();
		ps.close();
		con.close();
		return c;
	}
	catch(Exception e) {
		System.out.println(e);
	}
	return 0;
	}

	public static JSONArray recieveMessages(String user) {
		JSONArray chat_count= new JSONArray();
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select sender,count(sender),max(sent_time) max from messages where reciever=\""+user+"\" and isnull(recieved_time) and sent_time<now() group by sender order by max";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()) {
				JSONObject count = new JSONObject();
				count.put("cname",rs.getString(1));
				count.put("no",""+rs.getInt(2));
				chat_count.add(count);
			}
			st.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return chat_count;
	}
	
	public static void markAsRead(String sender,String reciever) {
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		int c=0;
		String query="update messages set recieved_time=now() where reciever=\""+sender+"\" and sender=\""+reciever+"\" and isnull(recieved_time)";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			c=ps.executeUpdate();
			ps.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public static int lastRecievedMessage(String sender, String reciever) {
		// TODO Auto-generated method stub
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select mid from messages where sender=\""+sender+"\" and reciever=\""+reciever+"\" and isnull(recieved_time)";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			int Return=0;
			if(rs.next()) {
				Return=rs.getInt(1);
			}
			st.close();
			con.close();
			return Return;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public static JSONArray recieveMessage(String sender, String reciever, int mid) {
		// TODO Auto-generated method stub
		JSONArray chats= new JSONArray();
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select message,sender,reciever,sent_time,recieved_time from messages where sender=\""+sender+"\" and reciever=\""+reciever+"\" and mid>="+mid;		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()) {
				JSONObject chat = new JSONObject();
				Date date,rdate;
				String time,rtime;
				String contact;				
				Time t,rt;
//				contact=rs.getString(2);
				t=rs.getTime(4);
				date=(rs.getDate(4));
				rt=rs.getTime(5);
				rdate=(rs.getDate(5));	
				chat.put("type", "reciever");
				java.util.Date gettime1=new java.util.Date(t.getTime());
				DateFormat sdf1=new SimpleDateFormat("hh:mm aa");
				time=sdf1.format(gettime1);
				java.util.Date gettime2=new java.util.Date(rt.getTime());
				DateFormat sdf2=new SimpleDateFormat("hh:mm aa");
				rtime=sdf2.format(gettime2);
				System.out.println(rs.getString(1));
				chat.put("rec-time", rtime);
				if(rdate==null)
					chat.put("rec-date","");
				else
					chat.put("rec-date", rdate.toString());
				chat.put("time", time);
				chat.put("date", date.toString());
				chat.put("msg", rs.getString(1));
				
				chats.add(chat);
			}
			st.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return chats;
	}
	public static int scheduleMessage(String sender, String reciever, String txt,String date) {
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String currentTime=AppConstants.getCurrentTime();
		if(AppConstants.compareDate(currentTime,date)==1) {
			return 0;
		}
		System.out.println(currentTime+"\n"+date);
//		System.out.println(new java.sql.Date(datetime./()));
		String query="insert into messages (message,sender,reciever,sent_time) values (\""+txt+"\",\""+sender+"\",\""+reciever+"\",\""+date+"\")";		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			int c=ps.executeUpdate();
			ps.close();
			con.close();
			return c;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
}
