package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.RoleOperations;

public class RoleDetails extends HttpServlet {
    private RoleOperations roleOps = new RoleOperations();

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
        throws ServletException, IOException {
       showRoleForm(response);
    }
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
        throws ServletException, IOException {
       showRoleForm(response);
    }
    
    public void showRoleForm(HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        roleOps.printRoleList();
        List<Integer> roleIds = roleOps.getRoleIdList();
        List<String> roleNames = roleOps.getRoleNameList();
        out.println("<title>Crud Application</title>"+
                    "<h1>Roles</h1>"+
                    "<form action='RoleOps' method='POST'>"+
                    "Create/Update/Delete/List Role:<br><br>"+
                    "Choose Role: <select name='roleId'>");
        for(int i = 0; i < roleIds.size(); i++){
            out.println("<option value='"+roleIds.get(i)+"'>"+ roleIds.get(i) +" - "+ roleNames.get(i) +"</option>");
        }
        out.println("</select><br>"+
                    "Enter new role name: <input type='text' max='20' name='roleName' size=20 placeholder='ROLENAME'><br><br>"+
                    "<button type='submit' name='action' value='CREATE'>CREATE</button>"+
                    "<button type='submit' name='action' value='UPDATE'>UPDATE</button>"+
                    "<button type='submit' name='action' value='LIST'>LIST ROLES</button>"+
                    "<button type='submit' name='action' value='DELETE'>DELETE</button><br><br>"+
                    "<button type='submit' name='action' value='BACK'>BACK TO MAIN</button>"+
                    "</form>");
        out.close();
    }
    
}
