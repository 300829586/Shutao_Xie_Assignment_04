package gameApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class InputHelper {

		public static java.sql.Date getDateInput(String input) throws ParseException{			
			Date newDate = new SimpleDateFormat("yyyy-mm-dd").parse(input); 
			java.sql.Date SQLDate = new java.sql.Date(newDate.getTime());
			return SQLDate;		
		}
}

