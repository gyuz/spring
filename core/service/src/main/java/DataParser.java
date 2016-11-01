package crud.core.service;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class DataParser{
     public int stringToInt(String value){
        int n = 0;
        try{
            n = Integer.parseInt(value);
        } catch(NumberFormatException ne) {
            n = 0;
        }   
        return n;
    }
    
    public double stringToDouble(String value){
        double n = 0;
        try{
            n = Double.parseDouble(value);
        } catch(NumberFormatException ne) {
            n = 0;
        }   
        return n;
    }
    
    public LocalDate stringToDate(String value){
        LocalDate n = null;
        try{
            n = LocalDate.parse(value, DateTimeFormat.forPattern("MM/dd/yyyy"));
        } catch(IllegalArgumentException ne) {
            n = null;
        }   
        return n;
    }

}
