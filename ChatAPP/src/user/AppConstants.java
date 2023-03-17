package user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppConstants {
	
	public static String userName = "test";
	public static String password = "";
	public static String url="jdbc:mysql://localhost:3306/chat";
	public static String driver="com.mysql.cj.jdbc.Driver";
	public static String getCurrentTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now); 
	}
	public static String TimeLaterHour(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();   
		now = now.plusHours(1);
		return dtf.format(now); 
	}
	
	public static String MinuteEarlier(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		now = now.minusMinutes(1);
		return dtf.format(now); 
	}
	
	public static int compareDate(String currentTime, String date) {
		int cmpFlag;
		for(int i=0;i<17;i++) {
			cmpFlag=Character.compare(currentTime.charAt(i),date.charAt(i)); 
			if(cmpFlag==0)
				continue;
			else if(cmpFlag>0)
				return 1;
			else
				return -1;
		}
		return 0;
	}
}

