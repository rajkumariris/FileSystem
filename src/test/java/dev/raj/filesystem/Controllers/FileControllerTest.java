package dev.raj.filesystem.Controllers;

import dev.raj.filesystem.Models.File;
import dev.raj.filesystem.Repository.FileRepository;
import dev.raj.filesystem.Services.FileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
class FileControllerTest {

    @Mock
    private FileService fileService;
    @InjectMocks
    private FileController fileController;

    @Mock
    private UserController userController;

    @Mock
    private FileRepository fileRepository;
  @Test
    void uploadFile() throws Exception {
      when(userController.validate("token", 1L)).thenReturn("ACTIVE");
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        when(fileService.uploadFile(any(MultipartFile.class))).thenReturn("test.txt");
        String response = fileController.uploadFile(file, "token", 1L);
        assertEquals("test.txt", response);
    }
    @Test
    void download() throws Exception {
        when(fileRepository.findById(any())).thenReturn(java.util.Optional.of(new File()));
        when(userController.validate("token", 1L)).thenReturn("ACTIVE");
        when(fileService.download(any())).thenReturn(new File());
        String response = fileController.download(1L, "token", 1L);
        assertEquals("file downloaded successfully", response);

    }

    @Test
    void fileshare() throws Exception {
        when(userController.validate("token", 1L)).thenReturn("ACTIVE");
        when(fileRepository.findById(any())).thenReturn(java.util.Optional.of(new File()));
        when(fileService.fileshare(any())).thenReturn(Path.of("test.txt"));
        Path path1 =  fileController.fileshare(1L, "token", 1L);
        assertNotNull(path1);
    }


}