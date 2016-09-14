package com.massoftind.rnd.firechatonetoone.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

	
	 public String getDateformat(String inputFotmat, String value, String formatValue)    {
			SimpleDateFormat formatter  = new SimpleDateFormat(inputFotmat);
			String format="";
			
			try {
				
				Date date = formatter.parse(value);
				String strDateFormat = formatValue;
				SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
				format=sdf.format(date);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return format;
		}

    public String getFormattedMonthInCaps(String inputFotmat, String value, String formatValue){
        SimpleDateFormat formatter  = new SimpleDateFormat(inputFotmat);
        //String format="";
        String mon = "";
        String year = "";
        String day = "";
        try {

            Date date = formatter.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            final String[] MONTHS = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
            mon=MONTHS[date.getMonth()];
            year = ""+calendar.get(Calendar.YEAR);
            day = ""+calendar.get(Calendar.DAY_OF_MONTH);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mon+" "+day+", "+year;
    }

        public String getFormattedYearMonth(String inputFotmat, String value, String formatValue){
        SimpleDateFormat formatter  = new SimpleDateFormat(inputFotmat);
        //String format="";
            String mon = "";
            String year = "";
        try {

            Date date = formatter.parse(value);
            /*String strDateFormat = formatValue;
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            format=sdf.format(date);*/
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //calendar.get(Calendar.DAY_OF_MONTH);

            final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            mon=MONTHS[date.getMonth()];
            year = ""+calendar.get(Calendar.YEAR);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mon+" "+year;
    }



    public String getFormattedYear(String inputFotmat, String value, String formatValue){

        SimpleDateFormat formatter  = new SimpleDateFormat(inputFotmat);
        String year = "";
        try {

            Date date = formatter.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = ""+calendar.get(Calendar.YEAR);
        }catch(Exception e){
            e.printStackTrace();
        }

        return year;
    }
    public String getFormattedDayMonthYear(String inputFotmat, String value, String formatValue){
        SimpleDateFormat formatter  = new SimpleDateFormat(inputFotmat);
        //String format="";
        String mon = "";
        String year = "";
        String day = "";
        try {

            Date date = formatter.parse(value);
            /*String strDateFormat = formatValue;
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            format=sdf.format(date);*/
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //calendar.get(Calendar.DAY_OF_MONTH);

            final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            mon=MONTHS[date.getMonth()];
            year = ""+calendar.get(Calendar.YEAR);
            day = ""+calendar.get(Calendar.DAY_OF_MONTH);
        }catch(Exception e){
            e.printStackTrace();
        }
        return day +" " +mon+" "+year;
    }
}
