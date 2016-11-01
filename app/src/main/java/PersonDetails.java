package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import org.joda.time.LocalDate;
import crud.core.service.PersonOperations;
import crud.core.service.RoleOperations;
import crud.core.service.DataParser;

public class PersonDetails extends HttpServlet {

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
                throws ServletException, IOException {
        populate(request, response);
    }
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
                throws ServletException, IOException {
        populate(request, response);
    }
    
    protected void populate(HttpServletRequest request,
                    HttpServletResponse response)
                  throws ServletException, IOException {
        PersonOperations personOps = new PersonOperations();
        RoleOperations roleOps = new RoleOperations();
        DataParser dataParser = new DataParser();
        int id = dataParser.stringToInt(request.getParameter("personId"));
        String firstName = "";
        String lastName = "";
        String middleName = "";
        String title = "";
        String street = "";
        String brgy = "";
        String city = "";
        int zip = 0;
        char employed = 'N';
        LocalDate birthDate = null;
        LocalDate dateHired = null;
        double gwa = 0.0;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<title>Crud Application</title>"+
                    "<h1>View Person</h1>");       
        
            if(id != 0){
                if(personOps.idExist(id)) {
                    personOps.printContactList();
                    personOps.printPersonRoleList();
                    roleOps.printRoleList();
                    firstName = personOps.getFirstName();
                    lastName = personOps.getLastName();
                    middleName = personOps.getMiddleName();
                    title = personOps.getTitle();
                    street = personOps.getStreet();
                    brgy = personOps.getBrgy();
                    city = personOps.getCity();
                    zip = personOps.getZip();
                    birthDate = personOps.getBirthDate();
                    dateHired = personOps.getDateHired();
                    gwa = personOps.getGwa();
                    employed = personOps.getEmployed();
                    List<String> titleList = personOps.printTitleList();
                    List<Integer> roleIds = personOps.getPersonRoleIds();
                    List<Integer> contactIds = personOps.getPersonContactIds();
                    List<String> roleNames = personOps.getPersonRoleNames();
                    List<String> contactTypes = personOps.getPersonContactTypes();
                    List<String> contactDetails = personOps.getPersonContactDetails();
                    List<String> typeList = personOps.printTypeList();
                    List<Integer> masterRoleList = roleOps.getRoleIdList();
                    List<String> masterRoleNameList = roleOps.getRoleNameList();
                    personOps.closeSession();
                    
                    out.println("<form action='PersonOps' name='personForm' method='POST'>"+
                                "ID:  <input type='hidden' name='personId' value='" + id + "'> " + id + "<br>" +
                                "Title: <select name='title' required>"+
                                "<option value='"+title+"' checked>"+title+"</option>");
                    for(int i = 0; i < titleList.size(); i++){
                        out.println("<option value='"+titleList.get(i)+"'>"+ titleList.get(i) + "</option>");
                    }
                    out.println("</select><br>"+
                    "First Name: <input type='text' max='30' name='firstName' value='" + firstName + "' required><br>"+
                    "Middle Name: <input type='text' max='15' name='middleName'  value='" + middleName + "'><br>"+
                    "Last Name:  <input type='text' max='15' name='lastName' value='" + lastName + "' required><br>"+
                    "BirthDate: <input type='date' name='birthDate' value='" + birthDate.toString("MM/dd/yyyy")  + "' required placeholder='MM/DD/YYYY'><br>"+
                    "Street: <input type='text' max='50' name='street' value='" + street + "' required><br>"+
                    "Brgy: <input type='text' max='20' name='brgy' value='" + brgy + "' required><br>"+
                    "City:  <input type='text' max='20' name='city' value='" + city + "' required><br>"+
                    "Zip: <input type='number' name='zip' value='" + zip + "' required><br>"+
                    "GWA: <input type='text' name='gwa' value='" + gwa + "'><br>"+
                    "Employed: <select name='employed' required>"+
                    "<option value='"+employed+"'>"+employed+"</option>"+
                    "<option value='Y'>Y</option>"+
                    "<option value='N'>N</option>"+
                    "</select><br>");
                    
                    if(dateHired != null) {
                        out.println("Date Hired: <input type='date' name='dateHired' value='" + dateHired.toString("MM/dd/yyyy") + "' placeholder='MM/DD/YYYY'>");
                    } else {
                        out.println("Date Hired: <input type='date' name='dateHired' placeholder='MM/DD/YYYY'>");
                    }
                     out.println("<br><button type='submit' name='action' value='UPDATE'>UPDATE DETAILS</button>");
                    
                    out.println("<br><br>Contacts:<br><table border='1'>"+
                                "<tr><td>Contact ID</td><td>Type</td><td>Details</td></tr>");
                    
                    for(int i = 0; i < contactIds.size(); i++){
                        out.println("<tr><td>"+contactIds.get(i)+"</td>"+ 
                                    "<td>"+contactTypes.get(i)+"</td>"+
                                    "<td>"+contactDetails.get(i)+"</td></tr>");
                    }
                    out.println("</table><br><br>"+
                                "Select Contact ID: <select name='contactId'>"+
                                "<option disabled selected value> -- Contact ID -- </option>");
                    for(int i = 0; i < contactIds.size(); i++){
                        out.println("<option value='"+contactIds.get(i)+"'>" + contactIds.get(i) + "</option>");
                    }
                    out.println("</select>"+
                    "<button type='submit' name='action' value='DELCONT'>DELETE CONTACT</button><br>"+
                    "Select Contact Type: <select name='contactType'>");
                    for(int i = 0; i < typeList.size(); i++){
                        out.println("<option value='"+typeList.get(i)+"'>"+ typeList.get(i) + "</option>");
                    }
        out.println("</select><br>"+
                    "Enter Details: <input type='text' max='20' name='contactDetail' size=20>"+ 
                    "<br><button type='submit' name='action' value='ADDCONT'>ADD CONTACT</button>"+
                    "<button type='submit' name='action' value='UPDCONT'>UPDATE CONTACT</button><br><br>"
                   );
                    out.println("Roles:<br>");
                    if(roleIds.size() > 0) {
                        out.println("<table border='1'>"+
                                    "<tr><td>Role ID</td><td>Role Name</td></tr>");
                        for(int i = 0; i < roleIds.size(); i++){
                            out.println("<tr><td>"+roleIds.get(i)+"</td>"+ 
                                        "<td>"+roleNames.get(i)+"</td></tr>");
                        }
                        out.println("</table>");
                    } else {
                        out.println("No Roles<br>");
                    }
                    out.println("Choose Role: <select name='roleId'>");
                    for(int i = 0; i < masterRoleList.size(); i++){
                        out.println("<option value='"+masterRoleList.get(i)+"'>"+ masterRoleList.get(i) + " - "+ masterRoleNameList.get(i) +"</option>");
                    }
                    out.println("</select><br>"+
                    "<button type='submit' name='action' value='ADDROLE'>ADD ROLE</button>"+
                    "<button type='submit' name='action' value='DELROLE'>DELETE ROLE</button><br><br>"+
                    "<button type='submit' name='action' value='DELETE'>DELETE</button><br>");
                    out.println("</form>");
                    
                } else {
                    out.println("ID does not Exist!");    
                }
            } else {
                out.println("Invalid ID");
            }
          
        out.println("<form action='PersonDispatch' method='GET'>"+
                    "<input type='hidden' name='list' value='4'>"+
                    "<input type='hidden' name='order' value='1'>"+
                    "<button type='submit' name='action' value='LIST'>GO TO LIST</button>"+
                    "<button type='submit' name='action' value='BACKP'>BACK TO PERSON</button>"+
                    "<button type='submit' name='action' value='BACK'>BACK TO MAIN</button>"+
                    "</form>");
                    
        out.close();
    }
   
    
}
