<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
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
            <div>
                <h1>View Person</h1>       
            </div>
        <%
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
         %>    
         <form action="PersonOps" name="personForm" method="POST">
            ID:  <input type="hidden" name="personId" value="<%=id%>"><%=id%><br/>
            Title: 
            <select name="title" required>
                <option value="<%=title%>" checked><%=title%></option>
                <c:forEach items="titleList" var="title">
                    <option value="${title}"> ${title} + </option>
                </c:forEach>
            </select>
            <br/>
            First Name: <input type="text" max="30" name="firstName" value="<%=firstName%>" required><br/>
            Middle Name: <input type="text" max="15" name="middleName"  value="<%=middleName%>"><br/>
            Last Name:  <input type="text" max="15" name="lastName" value="<%=lastName%>" required><br/>
            BirthDate: <input type="date" name="birthDate" value="<%=birthDate.toString("MM/dd/yyyy")%>" required placeholder="MM/DD/YYYY"><br/>
            Street: <input type="text" max="50" name="street" value="<%=street%>" required><br/>
            Brgy: <input type="text" max="20" name="brgy" value="<%=brgy%>" required><br/>
            City:  <input type="text" max="20" name="city" value="<%=city%>" required><br/>
            Zip: <input type="number" name="zip" value="<%=zip%>" required><br/>
            GWA: <input type="text" name="gwa" value="<%=gwa%>"><br/>
            Employed: 
            <select name="employed" required>
                <option value="<%=employed%>"><%=employed%></option>
                <option value="Y">Y</option>
                <option value="N">N</option>
            </select>
            <br/>
            <% if(dateHired != null) { %>
                Date Hired: <input type="date" name="dateHired" value="<%=dateHired.toString("MM/dd/yyyy")%>" placeholder="MM/DD/YYYY">
            <% } else { %>
                Date Hired: <input type="date" name="dateHired" placeholder="MM/DD/YYYY">
            <% } %>
            <br/>
            <button type="submit" name="action" value="UPDATE">UPDATE DETAILS</button>
            <br/><br/>
            Contacts:
            <br/>
            <table border="1">
                <tr>
                    <td>Contact ID</td>
                    <td>Type</td>
                    <td>Details</td>
                </tr>
                <% for(int i = 0; i < contactIds.size(); i++){ %>
                    <tr>
                        <td><%=contactIds.get(i)%></td> 
                        <td><%=contactTypes.get(i)%></td>
                        <td><%=contactDetails.get(i)%></td>
                   </tr>
                <% } %>
            </table>
            <br/><br/>
            Select Contact ID: 
            <select name="contactId">
                <option disabled selected value> -- Contact ID -- </option>
                <c:forEach items="<%=contactIds%>" var="contact">
                    <option value="${contact}"> ${contact} </option>
                </c:forEach>
            </select>
            <button type="submit" name="action" value="DELCONT">DELETE CONTACT</button>
            <br/>
            Select Contact Type: 
            <select name="contactType">
                <c:forEach items="<%=typeList%>" var="type">
                    <option value="${type}"> ${type} </option>
                </c:forEach>
            </select>
            <br/>
            Enter Details: 
            <input type="text" max="20" name="contactDetail" size=20> 
            <br/> 
            <button type="submit" name="action" value="ADDCONT">ADD CONTACT</button>
            <button type="submit" name="action" value="UPDCONT">UPDATE CONTACT</button>
            <br/><br/>
                   
            Roles:
            <br/>
            <% if(roleIds.size() > 0) { %>
                <table border="1">
                    <tr>
                        <td>Role ID</td>
                        <td>Role Name</td>
                    </tr>
                    <% for(int i = 0; i < roleIds.size(); i++){ %>
                        <tr>
                            <td><%=roleIds.get(i)%></td> 
                            <td><%=roleNames.get(i)%></td>
                        </tr>
                    <% } %>
                 </table>
            <% } else { %>
                No Roles
            <% } %>
            <br/>
            Choose Role: 
            <select name="roleId">
                <% for(int i = 0; i < masterRoleList.size(); i++){ %>
                <option value="<%=masterRoleList.get(i)%>"> <%=masterRoleList.get(i)%>  -  <%=masterRoleNameList.get(i)%></option>
                <% } %>
            </select>
            <br/>
            <button type="submit" name="action" value="ADDROLE">ADD ROLE</button>
            <button type="submit" name="action" value="DELROLE">DELETE ROLE</button><br/><br/>
            <button type="submit" name="action" value="DELETE">DELETE</button><br/>
         </form>
             <% } else { %>
                ID does not Exist! 
             <% }
          } else { %>
            Invalid ID
         <% } %>
            
            <form action="PersonDispatch" method="GET">
                <input type="hidden" name="list" value="4">
                <input type="hidden" name="order" value="1">
                <button type="submit" name="action" value="LIST">GO TO LIST</button>
                <button type="submit" name="action" value="BACKP">BACK TO PERSON</button>
                <button type="submit" name="action" value="BACK">BACK TO MAIN</button>
            </form>
            
       </div>
    </body>
</html>
