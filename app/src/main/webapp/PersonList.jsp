<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.joda.time.LocalDate" %>
<%@ page import="crud.core.service.RoleOperations" %>  
<%@ page import="crud.core.service.PersonOperations" %>
<%@ page import="crud.core.service.DataParser" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="js/tableFunctions.js"></script>  
    </head>
    <body>
        <div>
            <%
            PersonOperations personOps = new PersonOperations();
            DataParser dataParser = new DataParser();
            RequestDispatcher dispatcher;
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
            %>
                    
            <form action="PersonDispatch" method="GET">
                <input type="hidden" id="personId" name="personId">
                <table border="1">
                    <tr>
                        <td>PERSON ID</td>
                        <td>FIRST NAME</td>
                        <td>MIDDLE NAME</td>
                        <td>LAST NAME</td>
                        <td>TITLE</td>
                        <td>BIRTH DATE</td>
                        <td>STREET</td>
                        <td>BRGY</td>
                        <td>CITY</td>
                        <td>ZIP</td>
                        <td>GWA</td>
                        <td>EMPLOYED?</td>
                        <td>DATE HIRED</td>
                    </tr>
                    <% for(int i = 0; i < personIds.size(); i++){ %>
                    <tr>
                        <input type="hidden" id="personId<%=i%>" value="<%=personIds.get(i)%>">
                        <td><%=personIds.get(i)%></td>
                        <td><%=firstNames.get(i)%></td>
                        <td><%=middleNames.get(i)%></td>
                        <td><%=lastNames.get(i)%></td>
                        <td><%=titles.get(i)%></td>
                        <td><%=birthDates.get(i).toString("MM/dd/yyyy")%></td>
                        <td><%=streets.get(i)%></td>
                        <td><%=brgys.get(i)%></td>
                        <td><%=cities.get(i)%></td>
                        <td><%=zips.get(i)%></td>
                        <td><%=gwas.get(i)%></td>
                        <td><%=employed.get(i)%></td>
                        <% if(dateHires.containsKey(personIds.get(i))){ %>
                                <td><%=dateHires.get(personIds.get(i)).toString("MM/dd/yyyy")%> </td>
                        <% } else { %>
                                <td></td> 
                        <% } %> 
                        <td><button type="button" onclick="msg()" name="action" value="SEARCH">EDIT</button></td>
                    </tr>
                    <% } %>
                </table>
                <br/><br/>
                View Person List by:<br/>
                <input type="radio" name="list" value="1" checked>GWA<br/>
                <input type="radio" name="list" value="2">Last Name<br/>
                <input type="radio" name="list" value="3">Date Hired<br/>
                <input type="radio" name="list" value="4">Person ID<br/>
                Sort By: <br/>
                <input type="radio" name="order" value="1" checked>Ascending
                <input type="radio" name="order" value="2">Descending<br/>
                <input type="submit" name="action" value="LIST"><br/><br/>
                <button type="submit" name="action" value="BACKP">BACK TO PERSON</button>
            </form>
       </div>
    </body>
</html>
