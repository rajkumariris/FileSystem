package dev.raj.filesystem.Controllers;

import dev.raj.filesystem.Models.File;
import dev.raj.filesystem.Services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {

    FileService fileService;

    UserController userController;
       private final String directory = "C:\\Users\\dorka\\OneDrive\\Desktop\\DownloadedFiles";
    public FileController(FileService fileservice, UserController userController){
        this.fileService = fileservice;
        this.userController = userController;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @Nullable @RequestHeader("AUTH_TOKEN") String token,
                             @Nullable @RequestHeader("user_id") Long userId) throws Exception{
       if(token == null || userId == null) {
           throw new Exception("Token or userId is missing");
       }
       String status =  userController.validate(token , userId);
       if(status == "NOT_ACTIVE"){
           throw new Exception("User is not active");
       }
       String file2 =  fileService.uploadFile(file);
        return file2;
    }

    @GetMapping("/download/{id}")
    public String download(@PathVariable Long id,
                           @Nullable @RequestHeader("AUTH_TOKEN") String token,
                           @Nullable @RequestHeader("user id") Long userId) throws Exception {
        if(token == null || userId == null) {
            throw new Exception("Token or userId is missing");
        }
        String status =  userController.validate(token , userId);
        if(status == "NOT_ACTIVE"){
            throw new Exception("User is not active");
        }
        File file =  fileService.download(id);
        return "file downloaded successfully";

    }
    @GetMapping("/share/{id}")
    public Path fileshare(@PathVariable Long id,
                            @Nullable @RequestHeader("AUTH_TOKEN") String token,
                            @Nullable @RequestHeader("user id") Long userId) throws Exception {
        if(token == null || userId == null) {
            throw new Exception("Token or userId is missing");
        }
        String status =  userController.validate(token , userId);
        if(status == "NOT_ACTIVE"){
            throw new Exception("User is not active");
        }
        Path path =  fileService.fileshare(id);
        return path;
    }


}
