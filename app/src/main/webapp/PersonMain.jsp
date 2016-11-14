<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Crud Application</title>
        <link href="<c:url value='/css/styles.css' />" rel="stylesheet"/>   
    </head>
    <body>
        <div id="body">
            <div>
                <c:if test="${!errMsgs.isEmpty()}">
                    <div>
                        <c:forEach items="${errMsgs}" var="err">
                            <div class="errorblock">${err}</div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
            <div clas="header">
                <div>
                    <form:form action="/locale" method="GET">
                        <input type="hidden" name="page" value="PersonMain"/>
                        <spring:message code="lbl.lang" text="LANGUAGE" /> : <button type="submit" class="lang" name="lang" value="en"><spring:message code="lbl.en" text="ENGLISH" /></button> | 
                                  <button type ="submit" class="lang" name="lang" value="ch"><spring:message code="lbl.ch" text="CHINESE" /></button>
                    </form:form>
                </div>
                <br/>
                <h1><spring:message code="lbl.person" text="PERSON" /></h1>
            </div>   
            <div class="content">
                <form:form action="/personController" method="GET">   
                    <button type="submit" name="action" value="CREATE"><spring:message code="lbl.create" text="CREATE" /></button>
                    <br><br>   
                    <spring:message code="lbl.enter.personId" text="Enter Person ID:" /> <input type="number" name="personId" placeholder="##">
                    <button type="submit" name="action" value="SEARCH"><spring:message code="lbl.search" text="SEARCH" /></button>   
                    <br><br>
                    <div>   
                        <spring:message code="lbl.view.person.list" text="View Person List by:" />
                        <select name="list">
                            <option value="1" checked><spring:message code="lbl.gwa" text="GWA" />
                            <option value="2"><spring:message code="lbl.lastname" text="Last Name" />
                            <option value="3"><spring:message code="lbl.datehired" text="Date Hired" />
                            <option value="4"><spring:message code="lbl.personid" text="PERSON ID" />
                        </select>
                        <spring:message code="lbl.sort" text="Sort by:" /> 
                        <select name="order">
                            <option value="1" checked><spring:message code="lbl.asc" text="Ascending" />
                            <option value="2"><spring:message code="lbl.desc" text="Descending" />
                        </select>
                        
                        <button type="submit" name="action" value="LIST"><spring:message code="lbl.list" text="LIST" /></button>
                      </div>
                </form:form> 
                <br/><br/>
                <form:form action="/redirect" method="GET">
                    <button type="submit" name="view" value="index"><spring:message code="lbl.back.main" text="BACK TO MAIN" /></button>
                </form:form>
            </div>
        </div>
    </body>
</html>
