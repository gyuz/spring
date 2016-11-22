<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="js/tableFunctions.js"></script>
        <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/>  
    </head>
    <body>
        <div>
            <div>
                <form:errors path="*"/>
                  <c:choose>
                    <c:when test="${!errMsgs.isEmpty()}">
                        <div>
                            <c:forEach items="${errMsgs}" var="err">
                                <div class="errorblock">${err}</div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <div class="success">SAVED</div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div> 
            <div clas="header">
                <c:choose>
                    <c:when test="${not empty personDto.id}">
                        <div>
                            <form:form action="/locale" method="GET">
                                <input type="hidden" name="page" value="person"/>
                                <input type="hidden" name="action" value="SEARCH"/>
                                <input type="hidden" name="personId" value="${personDto.id}">
                                <spring:message code="lbl.lang" text="LANGUAGE" /> : 
                                <button type="submit" class="lang" name="lang" value="en">
                                    <spring:message code="lbl.en" text="ENGLISH" />
                                </button> | 
                                <button type ="submit" class="lang" name="lang" value="ch">
                                    <spring:message code="lbl.ch" text="CHINESE" />
                                </button>
                            </form:form>
                        </div>
                        <h1><spring:message code="lbl.view.person" text="VIEW PERSON" /></h1>
                        <c:set var="personId" value="${personDto.id}"/>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <form:form action="/locale" method="GET">
                                <input type="hidden" name="page" value="PersonDetails"/>
                                <spring:message code="lbl.lang" text="LANGUAGE" /> : 
                                <button type="submit" class="lang" name="lang" value="en">
                                    <spring:message code="lbl.en" text="ENGLISH" />
                                </button> | 
                                <button type ="submit" class="lang" name="lang" value="ch">
                                    <spring:message code="lbl.ch" text="CHINESE" />
                                </button>
                            </form:form>
                        </div>
                        <h1><spring:message code="lbl.create.person" text="CREATE PERSON" /></h1>
                        <c:set var="personId" value="0"/>
                    </c:otherwise>
                </c:choose> 
                <div class="topright">
	                <form action="/logout" method="post" id="logoutForm">
		                <input type="hidden" name="${_csrf.parameterName}"
			                value="${_csrf.token}" />
			            <button type="submit" name="logout" class="lang"><spring:message code="lbl.logout" text="LOGOUT" /></button> 
	                </form>
                </div>      
            </div>
            <div class="content">
                <form:form name="personDetails" action="/personSave" method="POST" commandName="personDto">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <table id="person">
                    <tr>
                        <td><spring:message code="lbl.personid" text="PERSON ID" />:</td>
                        <td>
                            <input type="hidden" name="id" value="${personId}">
                            <c:if test="${personDto.id != 0}">
                                ${personDto.id}
                            </c:if>
                        </td>
                    </tr>
                   
                    <tr>
                        <td><spring:message code="lbl.title" text="TITLE" />:</td> 
                        <td><select name="title" required>
                            <option value="${personDto.title}" checked>${personDto.title}</option>
                            <c:forEach items="${titles}" var="title">
                                <option value="${title}"> ${title} </option>
                            </c:forEach>
                            </select>
                       </td>
                       
                    <tr>
                        <td><spring:message code="lbl.firstname" text="FIRST NAME" />:</td>
                        <td><input type="text" max="30" name="firstName" value="${personDto.firstName}" required/></td>
                        <td>
                            <font color="red"><form:errors path="firstName"/></font>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.middlename" text="MIDDLE NAME" />:</td>
                        <td><input type="text" max="15" name="middleName"  value="${personDto.middleName}"></td>
                        <td>
                            <font color="red"><form:errors path="middleName"/></font>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.lastname" text="LAST NAME" />:</td>
                        <td><input type="text" max="15" name="lastName" value="${personDto.lastName}" required></td>
                        <td>
                            <font color="red"><form:errors path="lastName"/></font>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.birthdate" text="BIRTH DATE" />:</td> 
                        <td><input type="date" name="birthDate" value="${personDto.birthDate.toString('MM/dd/yyyy')}" required placeholder="MM/DD/YYYY"></td>
                        <td>
                            <font color="red"><form:errors path="birthDate"/></font>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.street" text="STREET" />:</td>
                        <td><input type="text" max="50" name="street" value="${personDto.street}" required></td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.brgy" text="BRGY" />:</td>
                        <td><input type="text" max="20" name="brgy" value="${personDto.brgy}" required></td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.city" text="CITY" />:</td>
                        <td><input type="text" max="20" name="city" value="${personDto.city}" required></td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.zip" text="ZIP" />:</td>
                        <td><input type="number" name="zip" value="${personDto.zip}" required></td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.gwa" text="GWA" />:</td>
                        <td><input type="number" name="gwa" value="${personDto.gwa}" step="any"></td>
                    </tr>
                    <tr>
                        <td><spring:message code="lbl.employed" text="EMPLOYED" />:</td>
                        <td>
                            <select name="employed" required>
                                <option value="${personDto.employed}">${personDto.employed}</option>
                                <option value="Y">Y</option>
                                <option value="N">N</option>
                            </select>
                       </td>
                    </tr> 
                    
                    <tr>
                        <td><spring:message code="lbl.datehired" text="DATE HIRED" />:</td>
                        <td>
                            <c:choose>
                                <c:when test="${personDto.employed eq 'Y'.charAt(0)}">
                                     <input type="date" name="dateHired" value="${personDto.dateHired.toString('MM/dd/yyyy')}" >
                                </c:when>
                                <c:otherwise>
                                     <input type="date" name="dateHired" placeholder="MM/DD/YYYY">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <font color="red"><form:errors path="dateHired"/></font>
                        </td>
                    </tr>
                </table>
                <br/><br/>
                <div class="contactGrp">
                    <form:errors path="PersonContactDetails" cssClass="errorblock" element="div"/>
                    <form:errors path="PersonContactTypes" cssClass="errorblock" element="div"/>
                    <h3><spring:message code="lbl.contacts" text="Contacts" /></h3>
                    <br/>
                    <table id="contactHdr">
                        <tr>
                            <th style="width: 100px;"><spring:message code="lbl.id" text="ID" /></th>
                            <th style="width: 250px;"><spring:message code="lbl.ctype" text="CONTACT TYPE" /></th>
                            <th style="width: 250px;"><spring:message code="lbl.cdetails" text="CONTACT DETAILS" /></th>
                            <th></th>
                       </tr>
                    </table>
                    <c:if test="${!personDto.personContactIds.isEmpty()}">
                        <table id="contacts">
                            <c:forEach var="contactId" items="${personDto.personContactIds}" varStatus="ctr">
                                <c:if test="${not empty contactId}">
                                    <tr>
                                        <td style="display:none;"><input type="text" id="${contactId}" name="personContactIds" value="${contactId}" readonly/></td>
                                        <td style="width: 100px;">${contactId}</td>
                                        <td style="width: 250px;">
                                            <input type="text" name="personContactTypes"  style="width: 250px;" readonly value="${personDto.personContactTypes.get(ctr.index)}" />
                                        </td>
                                        <td style="width: 250px;"><input type="text" name="personContactDetails" value="${personDto.personContactDetails.get(ctr.index)}"/></td>
                                        <td style="width: 100px;"><button type="button" onclick="deleteRow(this, 'contacts', 'personDetails', '${contactId}')"><spring:message code="lbl.delete" text="DELETE" /></button></td>
                                    </tr>
                                 </c:if>
                             </c:forEach>
                         </table>
                    </c:if>
                    <table id="newContacts">
                        <tr style="display:none;">
                            <td style="display:none;"><input type="text" name="personContactIds"/></td>
                            <td style="width: 100px;"></td>
                            <td style="width: 250px;">
                                <select name="personContactTypes"  style="width: 250px;">
                                <option checked value="0"> -- Contact Type -- </option>
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type}"> ${type} </option>
                                </c:forEach>
                                </select>
                            </td>
                            <td style="width: 250px;"><input type="text" name="personContactDetails" placeholder="number/email" size=20></td>
                            <td style="width: 100px;"><button type="button" onclick="deleteRow(this, 'newContacts', 'personDetails', '')"><spring:message code="lbl.delete" text="DELETE" /></button></td>
                        </tr>
                    </table>
                    <br/>
                    <button type="button" onclick="addRow('newContacts', '0', 'person')"><spring:message code="lbl.add.contact" text="ADD CONTACT" /></button>
               </div>
               <br/><br/>
                <div class="roleGrp">
                    <h3><spring:message code="lbl.role" text="ROLE" /></h3>
                    <br/>
                    <table id="roleHdr">
                        <tr>
                            <th style="width: 150px;"><spring:message code="lbl.id" text="ID" /></th>
                            <th style="width: 200px;"><spring:message code="lbl.rolename" text="ROLE NAME" /></th>
                            <th></th>
                        </tr>
                    </table>
                    <c:if test="${!personDto.personRoleIds.isEmpty()}">
                        <table id="role">
                            <c:forEach var="roleId" items="${personDto.personRoleIds}" varStatus="ctr">
                                <c:if test="${not empty roleId}">
                                    <tr>
                                        <td style="display:none;">
                                            <input type="text" id="${roleId}" name="personRoleIds" value="${roleId}" />
                                        </td>
                                        <td style="width: 150px;">${roleId}</td>
                                        <td style="width: 200px;">
                                            <input type="text" name="personRoleNames" style="width: 200px;" readonly value="${personDto.personRoleNames.get(ctr.index)}" />
                                        </td>
                                        <td style="width: 100px;"><button type="button" onclick="deleteRow(this, 'role', 'personDetails', '${roleId}')"><spring:message code="lbl.delete" text="DELETE" /></button></td>
                                    </tr>
                                </c:if> 
                            </c:forEach>
                         </table>
                   </c:if>
                  <table id="newRoles">
                     <tr style="display:none;">
                        <td style="display:none;"><input type="text" name="personRoleIds"/></td>
                        <td style="width: 150px;"></td>
                        <td style="width: 200px;">
                            <select name="personRoleNames"  style="width: 200px;">
                                <option disabled selected value> -- Role -- </option>
                                <c:forEach items="${roleDto.roleNameList}" var="roleName">
                                    <option value="${roleName}">${roleName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width: 100px;"><button type="button" onclick="deleteRow(this, 'newRoles', 'personDetails', '')"><spring:message code="lbl.delete" text="DELETE" /></button></td>
                     </tr>
                   </table>
                   <br/>
                   <button type="button" onclick="addRow('newRoles', '0', 'person')"><spring:message code="lbl.add.role" text="ADD ROLE" /></button>
                </div>
                <br/><br/>
                <button type="submit" value="SAVE"><spring:message code="lbl.save" text="SAVE" /></button>
             </form:form>
             <br/>
             <div>
                 <div>
                     <form:form action="/person" method="GET">
                            <input type="hidden" name="list" value="${list}">
                            <input type="hidden" name="order" value="${order}">
                            <button type="submit" name="action" value="LIST"><spring:message code="lbl.go.list" text="GO TO LIST" /></button>
                     </form:form>
                 </div>
                 <form:form action="/redirect" method="GET">
                     <button type="submit" name="view" value="PersonMain"><spring:message code="lbl.back.person" text="BACK TO PERSON" /></button>
                    <button type="submit" name="view" value="index"><spring:message code="lbl.back.main" text="BACK TO MAIN" /></button>
                 </form:form>     
             </div>
         </div>
       </div>
    </body>
</html>
