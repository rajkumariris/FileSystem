package dev.raj.filesystem.Services;

import dev.raj.filesystem.Models.File;
import dev.raj.filesystem.Repository.FileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    FileRepository fileRepository;
        private final String directory = "C:\\Users\\dorka\\OneDrive\\Desktop\\projectfileproject";
    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    public String uploadFile(MultipartFile file) throws IOException {
        Path pathfile = Paths.get(directory, file.getOriginalFilename());
        Files.write(pathfile, file.getBytes());
        File fullfile = new File();
        fullfile.setName(file.getOriginalFilename());
        fullfile.setPathDirectory(pathfile.toString());
       File fileupload =  fileRepository.save(fullfile);
       return fileupload.getName();

    }

    public File download(Long id) throws IOException {
        File file =  fileRepository.findById(id).get();
        Path path = Paths.get(file.getPathDirectory());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        Path downloadedPath = Paths.get(directory, file.getName());
        Files.write(downloadedPath, resource.getByteArray());
        return file;
    }

    public Path fileshare(Long id) throws IOException {
        File file =  fileRepository.findById(id).get();
        Path path = Paths.get(file.getPathDirectory());
        return path;
    }
}
