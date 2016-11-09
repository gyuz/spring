package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import crud.core.service.PersonService;
import crud.core.service.RoleService;
import crud.core.service.DataParser;
import crud.core.model.PersonDto;
import crud.core.model.RoleDto;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class PersonController extends SimpleFormController {
	private PersonService personOps;
	private RoleService roleOps;
	private DataParser dataParser;
	private PersonDto personDto;
	private RoleDto roleDto;

	public PersonController() {
		setCommandClass(PersonDto.class);
		setCommandName("personDto");
	}

	public void setPersonService(PersonService personOps) {
		this.personOps = personOps;
	}
	
	public void setPersonDto(PersonDto personDto) {
	    this.personDto = personDto;
	}
	
	public void setRoleService(RoleService roleOps) {
		this.roleOps = roleOps;
	}
	
	public void setRoleDto(RoleDto roleDto) {
	    this.roleDto = roleDto;
	}
	
	public void setDataParser(DataParser dataParser) {
	    this.dataParser = dataParser;
	}
	
	@Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView("PersonDetails");
        String action = request.getParameter("action");
        String list = request.getParameter("list");
        String order = request.getParameter("order");
        int id = 0;
        List errMsgs = new ArrayList();
        List successMsgs = new ArrayList();
        if ("SEARCH".equals(action)) {
            id = dataParser.stringToInt(request.getParameter("personId"));
            if(id != 0){
                if(!personOps.idExist(id)){
                    errMsgs.add("ID#"+id+" does not exist!");
                    mav.addObject("errMsgs", errMsgs);
                    mav.setViewName("PersonMain");   
                } else {
                    personDto = personOps.getPersonDto();
                    mav.addObject("personDto", personDto);
                }
            } else {
                errMsgs.add("Enter ID number to search");
                mav.addObject("errMsgs", errMsgs);
                mav.setViewName("PersonMain"); 
            }  
        } else if ("LIST".equals(action) || "DELETE".equals(action)) {
            if("DELETE".equals(action)){
                id = dataParser.stringToInt(request.getParameter("personId"));
                personOps.delete(id);  
                successMsgs.add("Successfully deleted Person ID#"+id);
                mav.addObject("successMsgs", successMsgs); 
            }
            personDto = personOps.printPersonList(dataParser.stringToInt(list), dataParser.stringToInt(order));
            mav.addObject("personDto", personDto);  
            mav.setViewName("PersonList");    
        }     
        
        if(errMsgs.isEmpty() && "CREATE".equals(action) || "SEARCH".equals(action)){
            List titleList = personOps.printTitleList();
            List contactTypes = personOps.printTypeList();
            roleDto = roleOps.printRoleList();
            mav.addObject("titles", titleList);
            mav.addObject("typeList", contactTypes); 
            mav.addObject("roleDto", roleDto);   
        } 
        
        return mav;
    }
}
