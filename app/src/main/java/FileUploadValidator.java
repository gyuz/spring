package crud.app;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import crud.core.model.FileUploadDto;

public class FileUploadValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return FileUploadDto.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FileUploadDto file = (FileUploadDto) target;
		if(!(file.getFile().getSize() > 0) || file.getFile() == null){
			errors.rejectValue("file", "required.fileUpload");
		}
	}
	
}
