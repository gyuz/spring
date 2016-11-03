<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="js/tableFunctions.js"></script>  
    </head>
    <body>
        <div>
            <div>
                <h1>Create Person</h1>
            </div>       
            <div>
                <form action="crud/PersonOps" name="personForm" method="POST">
                    Title:  
                    <select name="title" required>
                        <option disabled selected value> -- Title -- </option>
                        <c:forEach items="titleList" var="title">
                            <option value="${title}"> ${title} + </option>
                        </c:forEach>
                    </select>
                    <br/>
                    First Name: <input type="text" name="firstName" required><br/>
                    Middle Name: <input type="text" name="middleName"><br/>
                    Last Name:  <input type="text" name="lastName" required><br/>
                    Birth Date: <input type="date" name="birthDate" required placeholder="MM/DD/YYYY"><br/>
                    Street: <input type="text" name="street" required><br/>
                    br/gy: <input type="text" name="br/gy" required><br/>
                    City:  <input type="text" name="city" required><br/>
                    Zip: <input type="number" name="zip" required><br/>
                    GWA: <input type="text" name="gwa"><br/>
                    Employed: 
                    <select name="employed" required>
                        <option disabled selected value> -- Status -- </option>
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                    </select>
                    <br/>
                    
                    Date Hired: <input type="date" name="dateHired" placeholder="MM/DD/YYYY"> 
                    <br/><br/>
                    Contacts:
                    <br/>
                    <table id="contacts">
                        <c:forEach var="i" begin="1" end="3">         
                            <tr>
                                <td>
                                    <select name="newContactType">
                                        <option disabled selected value> -- Contact Type -- </option>
                                        <c:forEach items="typeList" var="type">
                                            <option value="${type}"> ${type} </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                <input type="text" name="newContactDetail" placeholder="number/email" size=20></td>
                        </c:forEach>
                    </table>
                    <button type="button" onclick="addRow('contacts')">Add Contacts</button><br/><br/>
                
                    Roles:
                    <br/>
                    <table id="roles">
                        <c:forEach var="i" begin="1" end="3"> 
                            <tr>
                                <td>
                                    <select name="newRoleId">
                                        <option disabled selected value> -- Role -- </option>
                                        <% for(int i = 0; i < masterRoleList.size(); i++){ %>
                                            <option value="masterRoleList.get(i)+"> <%=masterRoleList.get(i)%> - <%=masterRoleNameList.get(i)%></option>
                                        <% } %>
                                    </select>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="button" onclick="addRolesRow('roles')">Add Roles</button>
                    <br/><br/>
                    <button type="submit" name="action" value="CREATE">SAVE</button><br/>
                </form>
            </div>
            <div>
                <form action="crud/PersonDispatch" method="GET">
                    <button type="submit" name="action" value="BACKP">BACK TO PERSON</button>
                </form>
            </div>
        </div>
    </body>
</html>
