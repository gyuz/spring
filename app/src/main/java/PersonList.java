package crud.app;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.joda.time.LocalDate;
import crud.core.service.PersonOperations;
import crud.core.service.DataParser;

public class PersonList extends HttpServlet {
    private PersonOperations personOps;
    private DataParser dataParser;
    
    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        personOps = new PersonOperations();
        dataParser = new DataParser();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher;
         out.println("<head>" +
                    "<title>Crud Application</title>" + 
                    "<script type='text/javascript'>"+
                    "function getSelectedValue(rowid){"+
                    "   document.getElementById('personId').value=document.getElementById('personId'+rowid).value;"+
                    "   document.forms[0].sumbit();"+
                    "}"+
                    "</script></head>");
        
        String list = request.getParameter("list");
        String order = request.getParameter("order");
                
        personOps.printPersonList(dataParser.stringToInt(list), dataParser.stringToInt(order));
        List<Integer> personIds = personOps.getPersonIdList();
        List<String> firstNames = personOps.getFirstNameList();
        List<String> middleNames = personOps.getMiddleNameList();
        List<String> lastNames = personOps.getLastNameList();
        List<String> titles = personOps.getTitleList(); 
        List<LocalDate> birthDates = personOps.getBirthDateList();  
        Map<Integer, LocalDate> dateHires = personOps.getDateHiredMap();  
        List<String> streets = personOps.getStreetList();
        List<String> cities = personOps.getCityList();
        List<String> brgys = personOps.getBrgyList();
        List<Integer> zips = personOps.getZipList(); 
        List<Character> employed = personOps.getEmployedList();
        List<Double> gwas = personOps.getGwaList();
                
        out.println("<form action='PersonDispatch' method='GET'>");
        out.println("<input type='hidden' id='personId' name='personId'>"+
                    "<table border='1'>"+
                    "<tr><td>PERSON ID</td>"+
                    "<td>FIRST NAME</td>"+
                    "<td>MIDDLE NAME</td>"+
                    "<td>LAST NAME</td>"+
                    "<td>TITLE</td>"+
                    "<td>BIRTH DATE</td>"+
                    "<td>STREET</td>"+
                    "<td>BRGY</td>"+
                    "<td>CITY</td>"+
                    "<td>ZIP</td>"+
                    "<td>GWA</td>"+
                    "<td>EMPLOYED?</td>"+
                    "<td>DATE HIRED</td>"+
                    "</tr>");
        for(int i = 0; i < personIds.size(); i++){
            out.println("<tr>"+
                        "<input type='hidden' id='personId"+i+"' value='"+personIds.get(i)+"'>"+
                        "<td>" + personIds.get(i) + "</td>"+
                        "<td>" + firstNames.get(i) + "</td>"+
                        "<td>" + middleNames.get(i) + "</td>"+
                        "<td>" + lastNames.get(i) + "</td>"+
                        "<td>" + titles.get(i) + "</td>"+
                        "<td>" + birthDates.get(i).toString("MM/dd/yyyy")  + "</td>"+
                        "<td>" + streets.get(i) + "</td>"+
                        "<td>" + brgys.get(i) + "</td>"+
                        "<td>" + cities.get(i) + "</td>"+
                        "<td>" + zips.get(i) + "</td>"+
                        "<td>" + gwas.get(i) + "</td>"+
                        "<td>" + employed.get(i) + "</td>");
                                
             if(dateHires.containsKey(personIds.get(i))){
                out.println("<td>" + dateHires.get(personIds.get(i)).toString("MM/dd/yyyy")  + "</td>"); 
             } else {
                out.println("<td></td>"); 
             } 
                out.println("<td><button onclick='getSelectedValue("+i+")' name='action' value='SEARCH'>EDIT</button></td>");
                out.println("</tr>");
        }
        out.println("</table>"+
                    "<br><br>"+
                    "View Person List by:<br>"+
                    "<input type='radio' name='list' value='1' checked>GWA<br>"+
                    "<input type='radio' name='list' value='2'>Last Name<br>"+
                    "<input type='radio' name='list' value='3'>Date Hired<br>"+
                    "<input type='radio' name='list' value='4'>Person ID<br>"+
                    "Sort By: <br>"+
                    "<input type='radio' name='order' value='1' checked>Ascending"+
                    "<input type='radio' name='order' value='2'>Descending<br>"+
                    "<input type='submit' name='action' value='LIST'><br><br>"+
                    "<button type='submit' name='action' value='BACKP'>BACK TO PERSON</button>"+
                    "</form>"); 
        out.close();  
    }
    
}
