package crud.app;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import crud.core.service.PersonOperations;
import crud.core.service.PersonDto;

@SuppressWarnings("deprecation")
public class PersonController extends SimpleFormController {
	private PersonOperations personOps;

	public PersonController() {
		setCommandClass(PersonDto.class);
		setCommandName("person");
	}

	public void setPersonOperations(PersonOperations personOps) {
		this.personOps = personOps;
	}
    /*
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		PersonDto person = (PersonDto) command;
		personOps.createNewPerson(person);
		return new ModelAndView("success", "person", person);

	}*/
}
