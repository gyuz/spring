package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

public class PersonMain extends HttpServlet {
    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
        throws ServletException, IOException {
       showForm(response);
    }
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
        throws ServletException, IOException {
       showForm(response);
    }
    
    protected void showForm(HttpServletResponse response)
         throws ServletException, IOException {        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        out.println("<title>Crud Application</title>"+
                    "<h1>Person</h1>"+
                    "<form action='PersonDispatch' method='GET'>"+
                    "<button type='submit' name='action' value='CREATE'>CREATE NEW</button><br><br>"+
                    "Enter Person ID: <input type='number' name='personId' placeholder='##'>"+
                    "<button type='submit' name='action' value='SEARCH'>SEARCH</button>"+
                    "<br><br>"+
                    "View Person List by:<br>"+
                    "<input type='radio' name='list' value='1' checked>GWA<br>"+
                    "<input type='radio' name='list' value='2'>Last Name<br>"+
                    "<input type='radio' name='list' value='3'>Date Hired<br>"+
                    "<input type='radio' name='list' value='4'>Person ID<br>"+
                    "Sort By: <br>"+
                    "<input type='radio' name='order' value='1' checked>Ascending"+
                    "<input type='radio' name='order' value='2'>Descending<br>"+
                    "<input type='submit' name='action' value='LIST'><br><br>"+
                    "<button type='submit' name='action' value='BACK'>BACK TO MAIN</button>"+
                    "</form>");
        out.close();
    }
    
}
