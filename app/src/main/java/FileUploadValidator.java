package crud.app;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import crud.core.model.FileUploadDto;

@Component
public class FileUploadValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return FileUploadDto.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FileUploadDto file = (FileUploadDto) target;
		if(file.getFile() == null || !(file.getFile().getSize() > 0)){
		    errors.rejectValue("file", "required.fileUpload");
		} else if (!(file.getFile().getContentType().matches("text(.*)"))) {
		    errors.rejectValue("file", "file.type");
		}
	}
	
}
