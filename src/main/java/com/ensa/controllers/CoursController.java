package com.ensa.controllers;

import com.ensa.entity.Cours;
import com.ensa.services.CoursService;
import com.ensa.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/api/cours")
public class CoursController {

    private CoursService coursService;
    private FileStorageService fileStorageService;

    @Autowired
    public CoursController(CoursService coursService, FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
        this.coursService = coursService;
    }


    @PostMapping
    public ResponseEntity<?> addCours(@RequestParam("libelle")String libelle,@RequestParam("module")String module
            ,@RequestParam("content") MultipartFile url){

        String fileName = null;
        try {
            fileName = fileStorageService.storeFile(url);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/cours/downloadCours/")
                    .path(fileName)
                    .toUriString();

            Cours c = coursService.addCours(libelle,module,fileDownloadUri);
            return new ResponseEntity<>(c,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/downloadCours/{fileName}")
    public ResponseEntity<Resource> downloadCours(@PathVariable String fileName, HttpServletRequest request){

        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);
            String contentType = null;
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) contentType = "application/octet-stream";
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCours(@PathVariable Long id){
        try {
            Cours c  = coursService.deleteCours(id);
            return new ResponseEntity<>(c,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCours(@PathVariable Long id){
        try {

            Cours c  = coursService.getCours(id);
            return new ResponseEntity<>(c,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getALlcours(){
        return new ResponseEntity<>(coursService.getAllCours(),HttpStatus.OK);
    }

}
