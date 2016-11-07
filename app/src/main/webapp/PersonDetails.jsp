<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
            <form:form name="personDetails" action="personSaveController" method="POST" commandNam="personDto">
            ID:  <input type="hidden" name="id" value="${personDto.id}">${personDto.id}<br/>
            Title: 
            <select name="title" required>
                <option value="${personDto.title}" checked>${personDto.title}</option>
                <c:forEach items="${titles}" var="title">
                    <option value="${title}"> ${title} </option>
                </c:forEach>
            </select>
            <br/>
            First Name: <input type="text" max="30" name="firstName" value="${personDto.firstName}" required><br/>
            Middle Name: <input type="text" max="15" name="middleName"  value="${personDto.middleName}"><br/>
            Last Name:  <input type="text" max="15" name="lastName" value="${personDto.lastName}" required><br/>
            BirthDate: <input type="date" name="birthDate" value="${personDto.birthDate.toString('MM/dd/yyyy')}" required placeholder="MM/DD/YYYY"><br/>
            Street: <input type="text" max="50" name="street" value="${personDto.street}" required><br/>
            Brgy: <input type="text" max="20" name="brgy" value="${personDto.brgy}" required><br/>
            City:  <input type="text" max="20" name="city" value="${personDto.city}" required><br/>
            Zip: <input type="number" name="zip" value="${personDto.zip}" required><br/>
            GWA: <input type="text" name="gwa" value="${personDto.gwa}"><br/>
            Employed: 
            <select name="employed" required>
                <option value="${personDto.employed}">${personDto.employed}</option>
                <option value="Y">Y</option>
                <option value="N">N</option>
            </select>
            <br/>
            <c:choose>
                <c:when test="${personDto.dateHiredMap.containsKey(personDto.id)}">
                    Date Hired: <input type="date" name="dateHired" value="${personDto.dateHiredMap.get(personDto.id).toString('MM/dd/yyyy')}" >
                </c:when>
                <c:otherwise>
                     Date Hired: <input type="date" name="dateHired" placeholder="MM/DD/YYYY">
                </c:otherwise>
            </c:choose>
            <div>
            <br/><br/>
            Contacts:
            <br/>
            <c:if test="${!personDto.personContactIds.isEmpty()}">
                <table id="contacts">
                    <tr>
                        <td>CONTACT ID</td>
                        <td>CONTACT TYPE</td>
                        <td>CONTACT DETAILS</td>
                    </tr>
                    <c:forEach var="contactId" items="${personDto.personContactIds}" varStatus="ctr">
                        <tr>
                            <td style="display:none;"><input type="text" id="${contactId}" name="peronContactIds" value="${contactId}" readonly/></td>
                            <td>${contactId}</td>
                            <td>
                                <select name="personContactTypes" required>
                                    <option value="${personDto.personContactTypes.get(ctr.index)}" checked>${personDto.personContactTypes.get(ctr.index)}</option>
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type}"> ${type} </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="text" name="personContactDetails" value="${personDto.personContactDetails.get(ctr.index)}"/></td>
                            <td><button type="button" onclick="deleteRow(this, 'contacts', 'personDetails', '${contactId}')">DELETE</button></td>
                        </tr>
                     </c:forEach>
                 </table>
            </c:if>
            <table id="newContacts">
                <c:if test="${personDto.personContactIds.isEmpty()}">
                    <tr>
                        <td>CONTACT ID</td>
                        <td>CONTACT TYPE</td>
                        <td>CONTACT DETAILS</td>
                    </tr>
                </c:if>
                <tr>
                    <td style="display:none;"><input type="text" name="peronContactIds"/></td>
                    <td></td>
                    <td>
                        <select name="personContactTypes">
                        <option disabled selected value> -- Contact Type -- </option>
                        <c:forEach items="${typeList}" var="type">
                            <option value="${type}"> ${type} </option>
                        </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="personContactDetails" placeholder="number/email" size=20></td>
                    <td><button type="button" onclick="deleteRow(this, 'newContacts', 'personDetails', '')">DELETE</button></td>
                </tr>   
            </table>
            <button type="button" onclick="addRow('newContacts')">Add Contact</button>
            <br/><br/>
            <div>
                Roles:
                <br/>
                <c:if test="${!personDto.personRoleIds.isEmpty()}">
                    <table id="roles">
                        <tr>
                            <td>ROLE ID</td>
                            <td>ROLE NAME</td>
                            <td>
                        </tr>
                        <c:forEach var="roleId" items="${personDto.personRoleIds}" varStatus="ctr">
                            <tr>
                                <td style="display:none;"><input type="text" id="${roleId}" name="personRoleIds" value="${roleId}" readonly/></td>
                                <td>${roleId}</td>
                                <td>
                                    <select name="personRoleNames" required>
                                    <option value="${personDto.personRoleNames.get(ctr.index)}" checked>${personDto.personRoleNames.get(ctr.index)}</option>
                                    <c:forEach items="${roleDto.roleNameList}" var="roleName">
                                        <option value="${roleName}"> ${roleName} </option>
                                    </c:forEach>
                                </select>
                                </td>
                                <td><button type="button" onclick="deleteRow(this, 'roles', 'personDetails', '${roleId}')">DELETE</button></td>
                                </tr>
                        </c:forEach>
                     </table>
               </c:if>
              <table id="newRoles">
                <c:if test="${personDto.personRoleIds.isEmpty()}">
                    <tr>
                        <td>ROLE ID</td>
                        <td>ROLE NAME</td>
                    </tr>
                </c:if>
                <tr>
                    <td style="display:none;"><input type="text" name="personRoleIds"/></td>
                    <td></td>
                    <td>
                        <select name="personRoleNames">
                            <option disabled selected value> -- Role -- </option>
                            <c:forEach items="${roleDto.roleNameList}" var="roleName">
                                <option value="${roleName}"> ${roleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><button type="button" onclick="deleteRow(this, 'newRoles', 'personDetails', '')">DELETE</button></td>
                 </tr>   
               </table>
               <button type="button" onclick="addRow('newRoles')">Add Role</button>
            </div>
            <br/><br/>
            <button type="submit">SAVE</button>
         </form:form>
         <br/>
         <div>
             <div>
                 <form:form action="personController" method="GET">
                        <input type="hidden" name="list" value="4">
                        <input type="hidden" name="order" value="1">
                        <button type="submit" name="action" value="LIST">GO TO LIST</button>
                 </form:form>
             </div>
             <a href="../PersonMain.jsp"><button type="button">BACK TO PERSON</button></a>
             <a href="../index.jsp"><button type="button">BACK TO MAIN</button></a>       
         </div>
       </div>
    </body>
</html>
