package fileupload;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class test {

	public static void main(String[] args) {

		
	
		java.util.Date utilDate = new java.util.Date();
		long currentMilliseconds = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(currentMilliseconds);
		
		LocalDateTime today = LocalDateTime.now();
		 
		System.out.println("utilDate = " + utilDate);
		System.out.println("sqlDate = " + sqlDate);
	
		
		LocalTime time = LocalTime.now();
		System.out.println(LocalDate.now()+""+LocalTime.now());
		System.out.println("time = " + time);
		System.out.println("time = " + LocalDateTime.now());
	}

}
