package com.user;

import java.io.PrintWriter;
import com.user.Messages;
import user.AppConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.Time;
import java.sql.Date;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {

//	To verify Username and Password
	public static int verifyUser(String name,String pass) {
		
		
//		PrintWriter out=response.getWriter(); 
		
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select pwd from login where uid =\""+name+"\"";
//		System.out.println("Hi");
		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
//			System.out.println(rs);
//			boolean f=true;
			int Return;
			
			
			if(rs.next()) {
				if(rs.getString(1).equals(pass)) {
					Return=1;
				}
				else {
					Return=-1;
				}
			}
			else
			{
				Return=0;
			}
			st.close();
			con.close();
			return Return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;	
	}
	
	//To Verify Mail
	public static int verifyMail(String mail) {
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select count(*) from users where email =\""+mail+"\"";
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
//			System.out.println(rs);
//			boolean f=true;
			rs.next();
			int Return=rs.getInt(1);
//			System.out.print(Return);
			
			
//			if(rs.next()) {
//				Return=0;
//			}
//			else
//			{
//				Return=1;
//				
//			}
			st.close();
			con.close();
			return Return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	public static Map<String,Messages> getChats(String user){
		Map<String,Messages> msg=new HashMap<String,Messages>();
		Map<String,String> contactList=new HashMap<String,String>();
		String url=AppConstants.url;
		String currentDate=AppConstants.getCurrentTime();
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query=" select message,sender,reciever,sent_time,recieved_time from messages where (sender,reciever,sent_time) in(select sender,reciever,max(sent_time) from messages where (sender=\""+user+"\" or reciever=\""+user+"\") and sent_time<\""+currentDate+"\" group by sender,reciever) order by sent_time desc";
		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
//			System.out.println(rs);
//			boolean f=true;
			Messages m;
			int i=0;
			while(rs.next()) {
				m=new Messages();
				Date date;
				String time,text="";
				String contact;
				Time t,rt;
				date=(rs.getDate(4));
				t=rs.getTime(4);
				java.util.Date gettime1=new java.util.Date(t.getTime());
				DateFormat sdf1=new SimpleDateFormat("hh:mm aa");
				time=sdf1.format(gettime1);
/*			NO NEED       		date=(rs.getDate(5));
				rt=rs.getTime(5);
				java.util.Date gettime2=new java.util.Date(rt.getTime());
				DateFormat sdf2=new SimpleDateFormat("hh:mm aa");
				time=sdf2.format(gettime2);                      */
				if(rs.getString(2).equals(user)) {
//					date=(rs.getDate(4));
//					Time t=rs.getTime(4);
//					java.util.Date gettime=new java.util.Date(t.getTime());
//					DateFormat sdf=new SimpleDateFormat("hh:mm aa");
//					time=sdf.format(gettime);
					contact=rs.getString(3);
					text="<i class=\"material-icons icons\">done</i>";

				}
				else {
//					if(rs.getDate(5)==null) continue; 
//					date=(rs.getDate(5));					
//					Time t=rs.getTime(5);
//					java.util.Date gettime=new java.util.Date(t.getTime());
//					DateFormat sdf=new SimpleDateFormat("hh:mm aa");
//					time=sdf.format(gettime);
					contact=rs.getString(2);
					
				}
				text+=rs.getString(1);
				m.setContact(contact);
				m.setMessage(text);
				m.setTime(time);
				m.setDate(date);
				if(!(contactList.containsKey(contact))) {
					System.out.println("Contains No "+contact);
					contactList.put(contact,contact);
					msg.put(i+"",m);
				}
				
				System.out.println(msg.containsKey(contact));
				i++;
			}
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(msg);
		return msg;
	}
	
	public static JSONArray getLastRecieved(String sender) {
		JSONArray chats= new JSONArray();
//			List<HashMap<String, String>> chats= new JSONArray();
		String currentDate=AppConstants.getCurrentTime();
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
//		String query="select message,sender,reciever,sent_time,recieved_time from messages where (sender,reciever,sent_time) in (select sender,reciever,max(sent_time) from messages where (sender=\""+sender+" or reciever=\""+sender+"\") and isnull(recieved_time) and sent_time<\""+currentDate+"\" group by sender,reciever) order by sent_time desc";		
		String query="select message,sender,reciever,sent_time,recieved_time from messages where (sender,reciever,sent_time) in (select sender,reciever,max(sent_time) from messages where  reciever='"+sender+"' and isnull(recieved_time) and sent_time<'"+currentDate+"' group by sender,reciever) order by sent_time desc";
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//				System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			int i=1;
			while(rs.next()) {
//					if(rs.getDate(5)==null) continue;
				
				JSONObject chat = new JSONObject();
				Date date,rdate;
				String time,rtime=null;
				String contact;
								
				Time t,rt;
				date=(rs.getDate(4));
				contact=rs.getString(2);
				t=rs.getTime(4);
				java.util.Date gettime1=new java.util.Date(t.getTime());
				DateFormat sdf1=new SimpleDateFormat("hh:mm aa");
				time=sdf1.format(gettime1);
				rdate=(rs.getDate(5));
				contact=rs.getString(2);
				rt=rs.getTime(5);
				if(rt!=null) {
					java.util.Date gettime2=new java.util.Date(rt.getTime());
					DateFormat sdf2=new SimpleDateFormat("hh:mm aa");
					rtime=sdf2.format(gettime2);
				}
				chat.put("rec-time", rtime);
				if(rdate==null)
					chat.put("rec-date", "");
				else
					chat.put("rec-date", rdate.toString());
				chat.put("cname", contact);
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
	
	public static String dispContact(String user,String sugg) {
		String List="";
		String url=AppConstants.url;
		String dbname=AppConstants.userName;
		String dbpass=AppConstants.password;
		String driver=AppConstants.driver;
		String query="select uid from users where uid<>\""+user+"\" and uid like \"%"+sugg+"%\"";
		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			int i=0;
			if(rs.next()) {
			List+="<ul>";
			do {
				List+="<a><li>"+rs.getString(1)+"</li></a>";
				i++;
			}while(i<10 && rs.next());
			List+="</ul>";
			}
			else {
				List="<ul><a><li>No results</li></a></ul>";
			}
			st.close();
			con.close();
		}
		catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return List;
	}
	
	public static int checkMessage(String user) {
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String query="select msg_status from users where uid =\""+user+"\"";
		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			System.out.println(rs.getBoolean(1));
			int Return;
			if (rs.getBoolean(1)) Return=1;
			else Return=0;
			
			st.close();
			con.close();
			
			return Return;
		}
		catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	public static void scheduleTheFlag() {
		System.out.println("Scheduling");	
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String hourLater=AppConstants.TimeLaterHour();
		String query="update users set schedule_flag=1 where uid in (select sender from messages where sent_time>now() and sent_time<'"+hourLater+"') or uid in (select reciever from messages where sent_time>now() and sent_time<'"+hourLater+"')";
		
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public static int checkSchedule(String sender) {
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String query="select schedule_flag from users where uid =\""+sender+"\"";
		
		try {
			Class.forName(driver);
			
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			System.out.println(rs.getBoolean(1));
			int Return;
			if (rs.getBoolean(1)) Return=1;
			else Return=0;
			
			st.close();
			con.close();
			
			return Return;
		}
		catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void updateMsgFlag(String sender) {	
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String minuteEarlier=AppConstants.MinuteEarlier();
		String query="update users set msg_status=(select count(*) from messages where sent_time>'"+minuteEarlier+"' and sent_time<now() and reciever='"+sender+"' and isnull(recieved_time)) where uid='"+sender+"'";
				
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void updateScheduleFlag(String sender) {	
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String hourLater=AppConstants.TimeLaterHour();
		String query="update users set schedule_flag=(select count(*) from messages where sent_time<'"+hourLater+"' and sent_time>now() and reciever='"+sender+"' and isnull(recieved_time)) where uid='"+sender+"'";
				
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("Connected 	");
			PreparedStatement ps=con.prepareStatement(query);
			ps.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	public static void checkScheduleTime(String reciever,String sendDate) {
		String url=AppConstants.url;
		String dbname= AppConstants.userName;
		String dbpass= AppConstants.password;
		String driver=AppConstants.driver;
		String hourLater=AppConstants.TimeLaterHour();
		if(AppConstants.compareDate(hourLater,sendDate)==1) {
			String query="update users set schedule_flag=1 where uid='"+reciever+"'";
			try {
				Class.forName(driver);
				Connection con=DriverManager.getConnection(url,dbname,dbpass);
//				System.out.println("Connected 	");
				PreparedStatement ps=con.prepareStatement(query);
				ps.executeUpdate();
				
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	
}
