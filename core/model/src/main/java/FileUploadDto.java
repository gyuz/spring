package crud.core.model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;

@Component
public class FileUploadDto{
	
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
