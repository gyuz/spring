package crud.core.service;

import org.springframework.web.multipart.MultipartFile;
import crud.core.model.FileUploadDto;
import crud.core.model.FileUpload;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;

public class FileOperations implements FileService {
   private FileUploadDto fileDto;
   private FileUpload file;
   
   public void setFileDto(FileUploadDto fileDto){
        this.fileDto = fileDto;
   }
   
   public void setFileUpload(FileUpload file){
        this.file = file; 
   }   
   
   public boolean save(MultipartFile multipartFile, String saveDirectory, String fileName){
		file.setFile(multipartFile);
		
		if(! new File(saveDirectory).exists()) {
            new File(saveDirectory).mkdir();
        }
        /*
		    fileDto = (FileUploadDto) command;
		    MultipartFile multipartFile = fileDto.getFile();
		    File file = fileDto.getFile();
		    
		    if(file != null){
			    fileName = file.getOriginalFilename();
			    multipartFile.transferTo(new File(actualPath + File.separator + fileName));
		    }
		    */
		    /*OutputStream out = null;
		    try {
                out = new FileOutputStream(actualPath + File.separator + fileName);
                BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = bis.read(buffer)) > 0) {
                    System.out.println(read);
                    out.write(buffer, 0, read);
                }
                bis.close();
                out.close();
            } catch (IOException ioe) {
                errors.reject("upload.error");
                out.close();
            }*/
            OutputStream out = null;
            try {
                out = new FileOutputStream(new File(saveDirectory + File.separator + fileName));
                out.write(multipartFile.getBytes()); 
                out.close();
            } catch (Exception e) {
                System.out.println("Error while saving file");
                return false;
            }    
       return true;
   }
}
