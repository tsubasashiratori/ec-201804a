package payment_9;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {

	public static void main(String[] args) throws ParseException{
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd");
		String S = f.format(localDate);
		System.out.println(S);
		
		Date d = Date.valueOf(localDate);
		System.out.println(d);
		
	}

}
