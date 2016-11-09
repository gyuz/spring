<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
    <head>
        <title>Crud Application</title>
        <script src="<c:url value='/js/formValidations.js'/>"></script> 
    </head>
    <body>
        <div> 
             <div style="color:blue;">
                <c:if test="${!successMsgs.isEmpty()}">
                    <c:forEach items="${successMsgs}" var="msg">
                        ${msg} <br/>
                    </c:forEach>
                </c:if>
            </div>   
            <form:form name="personList" action="/personController" method="GET">
                View Person List by:
                <select name="list">
                    <option value="1" checked>GWA<br/>
                    <option value="2">Last Name
                    <option value="3">Date Hired
                    <option value="4">Person ID
                </select>
                Sort By: 
                <select name="order">
                    <option value="1" checked>Ascending
                    <option value="2">Descending
                </select>
                
                <input type="submit" name="action" value="LIST"><br/><br/>
                <input type="hidden" id="personId" name="personId">
                
                <table border="1" style="width: fixed"> 
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
                                <button type="submit" onclick="getSelectedValue('${ctr.index}')" name="action" value="SEARCH">EDIT</button>
                                <button type="submit" onclick="getSelectedValue('${ctr.index}')" name="action" value="DELETE">DELETE</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br/><br/>
            </form:form>
            <div>
                <a href="../PersonMain.jsp"><button type="button">BACK TO PERSON</button></a>
                <a href="../index.jsp"><button type="button">BACK TO MAIN</button></a>
            </div>
       </div>
    </body>
</html>
