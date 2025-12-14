package com.surweesh.summa.photo.clone;

import java.util.*;
import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
public class PhotoController {

    private final PhotoService phService;
    private final PhotoRepository photoRepository;

    public PhotoController(PhotoService phService, PhotoRepository photoRepository) {
        this.phService = phService;
        this.photoRepository = photoRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photoz")
    public List<Photo> getAll() {
        return phService.getAll();
    }


    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo ph = phService.get(id);
        if (ph == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ph;
    }

    @DeleteMapping("/photoz/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        boolean removed = phService.delete(id);
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/photoz", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestPart("file") MultipartFile file) throws IOException {
        Photo ph = new Photo(
            UUID.randomUUID().toString(),
            file.getOriginalFilename()
        );
        ph.setData(file.getBytes());
        phService.save(ph);
        return "redirect:/";
    }

    @GetMapping("/photoz/{id}/data")
    public ResponseEntity<byte[]> getPhotoData(@PathVariable String id) {
    Photo photo = photoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG); // or PNG

    return new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);
}

}