<html>
    <head>
        <title>Crud Application</title>   
    </head>
    <body>
        <div id="body">
            <div id="heade">
                <h1>Person</h1>
            </div>   
            <div>
                <form action="personController" method="GET">   
                    <button type="submit" name="action" value="CREATE">CREATE NEW</button>
                    <br><br>   
                    Enter Person ID: <input type="number" name="personId" placeholder="##">
                    <button type="submit" name="action" value="SEARCH">SEARCH</button>   
                    <br><br>   
                    View Person List by:<br>   
                    <input type="radio" name="list" value="1" checked>GWA<br> 
                    <input type="radio" name="list" value="2">Last Name<br> 
                    <input type="radio" name="list" value="3">Date Hired<br>
                    <input type="radio" name="list" value="4">Person ID<br>
                    Sort By: <br>
                    <input type="radio" name="order" value="1" checked>Ascending
                    <input type="radio" name="order" value="2">Descending<br>
                    <input type="submit" name="action" value="LIST"><br><br>
                </form> 
                <a href="index.jsp"><button type="submit" name="action" value="BACK">BACK TO MAIN</button></a> 
            </div>
        </div>
    </body>
</html>
