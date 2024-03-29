package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.FileDB;
import org.devellopement.pfeback.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;


@Service
public class FileStorageService {
    @Autowired
    FileDBRepository fileDBRepository;

    //store a file (image)
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB fileDb = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(fileDb);
    }
}
