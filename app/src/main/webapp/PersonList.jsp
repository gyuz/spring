<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/tableFunctions.js'/>"></script>
        <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/> 
    </head>
    <body>
        <div> 
             <div>
                <c:if test="${!successMsgs.isEmpty()}">
                    <c:forEach items="${successMsgs}" var="msg">
                        <div class="success">${msg}</div>
                    </c:forEach>
                </c:if>
            </div>
            <div clas="header">
                <div>
                    <form:form action="/locale" method="GET">
                        <input type="hidden" name="page" value="person"/>
                        <input type="hidden" name="action" value="LIST"/>
                        <input type="hidden" name="list" value="${list}">
                        <input type="hidden" name="order" value="${order}">
                        <spring:message code="lbl.lang" text="LANGUAGE" /> : 
                        <button type="submit" class="lang" name="lang" value="en">
                            <spring:message code="lbl.en" text="ENGLISH" />
                        </button> | 
                        <button type ="submit" class="lang" name="lang" value="ch">
                            <spring:message code="lbl.ch" text="CHINESE" />
                        </button>
                    </form:form>
                </div>
                <div class="topright">
	                <form action="/logout" method="post" id="logoutForm">
		                <input type="hidden" name="${_csrf.parameterName}"
			                value="${_csrf.token}" />
			            <button type="submit" name="logout" class="lang"><spring:message code="lbl.logout" text="LOGOUT" /></button> 
	                </form>
                </div>
                <h1><spring:message code="lbl.personlist.hdr" text="Person List"/></h1>
            </div>
            <div class="content">
                <form:form name="personList" action="/person" method="GET">
                    <spring:message code="lbl.view.person.list" text="View Person List by:" />
                    <select name="list">
                        <c:choose>
                            <c:when test="${list == 1}">
                                <option value="1" checked><spring:message code="lbl.gwa" text="GWA" />
                            </c:when>
                            <c:when test="${list == 2}">
                                <option value="2" checked><spring:message code="lbl.lastname" text="LAST NAME" />
                            </c:when>
                            <c:when test="${list == 3}">
                                <option value="3" checked><spring:message code="lbl.datehired" text="DATE HIRED" />
                            </c:when>
                            <c:when test="${list == 4}">
                                <option value="4" checked><spring:message code="lbl.personid" text="PERSON ID" />
                            </c:when>
                        </c:choose>
                        <option value="1"><spring:message code="lbl.gwa" text="GWA" />
                        <option value="2"><spring:message code="lbl.lastname" text="LAST NAME" />
                        <option value="3"><spring:message code="lbl.datehired" text="DATE HIRED" />
                        <option value="4"><spring:message code="lbl.personid" text="PERSON ID" />
                    </select>
                    <spring:message code="lbl.sort" text="Sort by:" />  
                    <select name="order">
                        <c:choose>
                            <c:when test="${order == 1}">
                                <option value="1" checked><spring:message code="lbl.asc" text="Ascending" />
                            </c:when> 
                            <c:when test="${order == 2}">
                                <option value="2" checked><spring:message code="lbl.desc" text="Descending" />
                            </c:when> 
                        </c:choose>
                        <option value="1"><spring:message code="lbl.asc" text="Ascending" />
                        <option value="2"><spring:message code="lbl.desc" text="Descending" />
                    </select>
                    
                    <button type="submit" name="action" value="LIST"><spring:message code="lbl.list" text="LIST" /></button>
                    <br/><br/>
                    <input type="hidden" id="personId" name="personId">
                    
                    <table id="personList"> 
                        <tr>
                            <th><spring:message code="lbl.personid" text="PERSON ID" /></th>
                            <th><spring:message code="lbl.firstname" text="FIRST NAME" /></th>
                            <th><spring:message code="lbl.middlename" text="MIDDLE NAME" /></th>
                            <th><spring:message code="lbl.lastname" text="LAST NAME" /></th>
                            <th><spring:message code="lbl.title" text="TITLE" /></th>
                            <th><spring:message code="lbl.birthdate" text="BIRTH DATE" /></th>
                            <th><spring:message code="lbl.street" text="STREET" /></th>
                            <th><spring:message code="lbl.brgy" text="BRGY" /></th>
                            <th><spring:message code="lbl.city" text="CITY" /></th>
                            <th><spring:message code="lbl.zip" text="ZIP" /></th>
                            <th><spring:message code="lbl.gwa" text="GWA" /></th>
                            <th><spring:message code="lbl.employed" text="EMPLOYED" /></th>
                            <th><spring:message code="lbl.datehired" text="DATE HIRED" /></th>
                        </tr>
                        <c:forEach items="${personDto.personIdList}" var="person" varStatus="ctr">
                            <tr>
                                <input type="hidden" id="personId${ctr.index}" value="${personDto.personIdList.get(ctr.index)}">
                                <td>${personDto.personIdList.get(ctr.index)}</td>
                                <td>${personDto.firstNameList.get(ctr.index)}</td>
                                <td>${personDto.middleNameList.get(ctr.index)}</td>
                                <td>${personDto.lastNameList.get(ctr.index)}</td>
                                <td>${personDto.titleList.get(ctr.index)}</td>
                                <td>${personDto.birthDateList.get(ctr.index).toString("MM/dd/yyyy")}</td>
                                <td>${personDto.streetList.get(ctr.index)}</td>
                                <td>${personDto.brgyList.get(ctr.index)}</td>
                                <td>${personDto.cityList.get(ctr.index)}</td>
                                <td>${personDto.zipList.get(ctr.index)}</td>
                                <td>${personDto.gwaList.get(ctr.index)}</td>
                                <td>${personDto.employedList.get(ctr.index)}</td>
                                <c:choose>
                                    <c:when test="${personDto.dateHiredMap.containsKey(personDto.personIdList.get(ctr.index))}">
                                        <td>${personDto.dateHiredMap.get(personDto.personIdList.get(ctr.index)).toString("MM/dd/yyyy")} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <button type="submit" onclick="getSelectedValue('${ctr.index}')" name="action" value="SEARCH"><spring:message code="lbl.edit" text="EDIT" /></button>
                                    <button type="submit" onclick="getSelectedValue('${ctr.index}')" name="action" value="DELETE"><spring:message code="lbl.delete" text="DELETE" /></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br/><br/>
                </form:form>
                <div>
                    <form:form action="/redirect" method="GET">
                        <button type="submit" name="view" value="PersonMain"><spring:message code="lbl.back.person" text="BACK TO PERSON" /></button>
                        <button type="submit" name="view" value="index"><spring:message code="lbl.back.main" text="BACK TO MAIN" /></button>
                     </form:form>
                </div>
            </div> 
       </div>
    </body>
</html>
