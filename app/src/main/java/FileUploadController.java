package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestDataBinder;
import java.io.File;
import java.io.IOException;
import org.springframework.util.FileCopyUtils;
import crud.core.model.FileUploadDto;

public class FileUploadController extends SimpleFormController{
	private FileUploadDto fileDto;
	
	public FileUploadController(){
		setCommandClass(FileUploadDto.class);
		setCommandName("fileDto");
	}
	
	public void setFileUploadDto(FileUploadDto fileDto){
	    this.fileDto = fileDto;
	}
 
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
		throws ServletException, IOException {
        String fileName= "";
		String saveDirectory = "/uploads/"; 	
		String actualPath = request.getServletContext().getRealPath(saveDirectory);
		
		if(! new File(actualPath).exists()) {
            new File(actualPath).mkdir();
        }

        if(!errors.hasErrors()){
            /*
		    fileDto = (FileUploadDto) command;
		    MultipartFile multipartFile = fileDto.getFile();
		    File file = fileDto.getFile();
		    
		    if(file != null){
			    fileName = file.getOriginalFilename();
			    multipartFile.transferTo(new File(actualPath + File.separator + fileName));
		    }
		    */
		    fileDto = (FileUploadDto) command;
		    MultipartFile file = fileDto.getFile();
		    fileName = file.getOriginalFilename();
		    FileCopyUtils.copy(file.getBytes(), new File(actualPath + File.separator + fileName));
		}
		return new ModelAndView("index","fileName", fileName);
	}
}
