package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import java.io.IOException;

import crud.core.model.FileUploadDto;
import crud.core.service.FileService;

public class FileUploadController extends SimpleFormController{
	private FileUploadDto fileDto;
	private FileService fileOps; 
	
	public FileUploadController(){
		setCommandClass(FileUploadDto.class);
		setCommandName("fileDto");
	}
	
	public void setFileUploadDto(FileUploadDto fileDto){
	    this.fileDto = fileDto;
	}
	
	public void setFileService(FileService fileOps){
        this.fileOps = fileOps;
    }
 
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
		throws ServletException, IOException {
		fileDto = (FileUploadDto) command;
		MultipartFile multipartFile = fileDto.getFile();
		String fileName = multipartFile.getOriginalFilename();
        if(!errors.hasErrors()){
		    String saveDirectory = "/uploads/"; 	
		    String actualPath = request.getServletContext().getRealPath(saveDirectory);
		    if(!(fileOps.save(multipartFile, actualPath, fileName))){
		        errors.reject("upload.error");
		    }
		}
		return new ModelAndView("index","fileName", fileName);
	}
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
