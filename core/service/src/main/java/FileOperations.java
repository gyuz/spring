package crud.core.service;

import org.springframework.web.multipart.MultipartFile;
import crud.core.model.FileUploadDto;
import crud.core.model.FileUpload;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileOperations implements FileService {
   private FileUploadDto fileDto;
   private FileUpload file;
   
   @Autowired
   public void setFileDto(FileUploadDto fileDto){
        this.fileDto = fileDto;
   }
   
   public void setFileUpload(FileUpload file){
        this.file = file; 
   }   
   
   public boolean save(MultipartFile multipartFile, String saveDirectory, String fileName){
		file = new FileUpload();
		file.setFile(multipartFile);
		
		if(! new File(saveDirectory).exists()) {
            new File(saveDirectory).mkdir();
        }
        
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
