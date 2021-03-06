package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.services.file.DeleteFileService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.DownloadFileService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.FindFilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private FindFilesService findFilesService;
    @Autowired
    private DownloadFileService downloadFileService;
    @Autowired
    private DeleteFileService deleteFileService;


    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ModelAndView uploadFile(final Authentication authentication, final MultipartFile fileUpload){
        final String userName = authentication.getPrincipal().toString();

        try {
            uploadFileService.execute(fileUpload, userName);
            return new ModelAndView("redirect:/result?success=true&redirect=/home?file=true");
        }catch (final Exception exception){
            return new ModelAndView( "redirect:/result?redirect=/home?file=true&errorMessage=" + exception.getMessage());
        }
    }

    @GetMapping
    public String findFiles(final Authentication authentication, final Model model){
        final String userName = authentication.getPrincipal().toString();

        final List<File> files = findFilesService.execute(userName);
        model.addAttribute("files", files);

        return "redirect:home";
    }

    @GetMapping(value = "/{fileId}/download")
    public ResponseEntity<InputStreamSource> downloadFile(@PathVariable("fileId") final String fileId){
        final File file = downloadFileService.execute(Integer.valueOf(fileId));
        final InputStream inputStream = new ByteArrayInputStream(file.getFileData());
        final InputStreamResource resource = new InputStreamResource(inputStream);

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+file.getFileName())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(Long.parseLong(file.getFileSize()))
                .body(resource);
    }

    @RequestMapping(value = "/{fileId}/delete")//DeleteMapping
    public ModelAndView deleteFile(@PathVariable("fileId") final Integer fileId){
        deleteFileService.execute(fileId);
        return new ModelAndView("redirect:/result?success=true&redirect=/home?file=true");
    }

    @GetMapping("/result")
    public String getResult(){
        return "result";
    }
}
