package crud.app;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import crud.core.model.FileUploadDto;
import crud.core.service.FileService;

@Controller
public class FileUploadController {
	private FileUploadDto fileDto;
	private FileService fileOps; 
	private FileUploadValidator fileValidator;
	
	@Autowired
	public void setFileUploadDto(FileUploadDto fileDto){
	    this.fileDto = fileDto;
	}
	
	@Autowired
	public void setFileService(FileService fileOps){
        this.fileOps = fileOps;
    }
    
    @Autowired
    public void setFileUploadValidator(FileUploadValidator fileValidator){
        this.fileValidator = fileValidator;
    }
 
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	protected String uploadFile(HttpServletRequest request, Model model, @ModelAttribute("fileDto") FileUploadDto fileDto, BindingResult result) {
	    fileValidator.validate(fileDto, result);
		
        if(!result.hasErrors()){
            MultipartFile multipartFile = fileDto.getFile();
		    String fileName = multipartFile.getOriginalFilename();
		    String saveDirectory = "/uploads/"; 	
		    String actualPath = request.getServletContext().getRealPath(saveDirectory);
		    if(!(fileOps.save(multipartFile, actualPath, fileName))){
		        result.reject("upload.error");
		    }
		    model.addAttribute("fileName", fileName);
		}
		return "index";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
