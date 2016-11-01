package crud.app;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.RoleOperations;
import crud.core.service.DataParser;

public class RoleOps extends HttpServlet {
    private RoleOperations roleOps;
    private DataParser dataParser;
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName").trim();
        roleOps = new RoleOperations();
        dataParser = new DataParser();
        int id = 0;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        RequestDispatcher dispatcher;
        
        if ("BACK".equals(action)) {
            response.sendRedirect("../index.html");
        } else {
            if ("UPDATE".equals(action)) {
                id = dataParser.stringToInt(roleId);            
                if(id != 0){
                    if(roleName.equals("")) {
                        out.println("Update failed! Blank role name");
                    } else if(roleOps.idExist(id)){
                        if(!roleOps.update(id, roleName)){
                            out.println("Update failed! Role already exist");
                        } else {
                            out.println("Role Updated");
                        } 
                    } else {
                        out.println("ID does not exist");
                    }   
                } else {
                  out.println("Invalid ID");
                } 
            } else if ("CREATE".equals(action)) {
                if(roleName.equals("")) {
                    out.println("Creation failed. No role name value.");
                } else if(!roleOps.isDuplicate(roleName)) {
                    roleOps.addRoleName(roleName);
                    out.println("Role "+roleName+" created.");
                } else {
                    out.println("Creation failed. Role already exist");
                }
            } else if ("DELETE".equals(action)) {
                id = dataParser.stringToInt(roleId);         
                if(id != 0){
                    if(!roleOps.delete(id)){
                         out.println("Role ID could not be deleted!<br>ID may not exist or role is still associated with a person.");
                    } else {
                        out.println("ID "+id+" deleted");
                    }   
                } else {
                  out.println("Invalid ID");
                } 
            } else if("LIST".equals(action)) {
                roleOps.printRoleList();
                List<Integer> roleIds = roleOps.getRoleIdList();
                List<String> roleNames = roleOps.getRoleNameList();
                out.println("<table>"+
                            "<tr><td>ROLD ID</td><td>ROLE NAME</td></tr>");
                for(int i = 0; i < roleIds.size(); i++){
                    out.println("<tr><td>" + roleIds.get(i) + "</td>"+
                                "<td>" + roleNames.get(i) + "</td></tr>");
                }
                out.println("</table>");
            }
            
            dispatcher = request.getRequestDispatcher("RoleDetails");
            dispatcher.include(request, response);
        }
        out.close();
    }
}
