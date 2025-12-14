package com.surweesh.summa.photo.clone;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class DownloadController {

    private final PhotoService photoService;

    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {

        Photo photo = photoService.get(id);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] data = photo.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(data.length);
        headers.set(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + photo.getFileName() + "\""
        );


        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
