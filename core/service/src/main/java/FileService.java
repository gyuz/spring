package crud.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public boolean save(MultipartFile file, String saveDirectory, String fileName);
}

