<html>
    <head>
        <title>Crud Application</title>   
    </head>
    <body>
        <div id="body">
            <div style="color:red;">
                <c:if test="${!errMsgs.isEmpty()}">
                    ${errMsgs.get(0)}
                </c:if>
            </div>
            <div id="header">
                <h1>Person</h1>
            </div>   
            <div>
                <form action="/personController" method="GET">   
                    <button type="submit" name="action" value="CREATE">CREATE NEW</button>
                    <br><br>   
                    Enter Person ID: <input type="number" name="personId" placeholder="##">
                    <button type="submit" name="action" value="SEARCH">SEARCH</button>   
                    <br><br>
                    <div>   
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
                        
                        <input type="submit" name="action" value="LIST">
                      </div>
                    <br><br>
                </form> 
                <a href="index.jsp"><button type="submit" name="action" value="BACK">BACK TO MAIN</button></a> 
            </div>
        </div>
    </body>
</html>
