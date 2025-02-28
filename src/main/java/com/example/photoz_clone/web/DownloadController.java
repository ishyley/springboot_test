package com.example.photoz_clone.web;

import com.example.photoz_clone.model.Photo;
import com.example.photoz_clone.service.PhotozServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    
    
    private final PhotozServices photozServices;

    public DownloadController(PhotozServices photozServices) {
        this.photozServices = photozServices;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Photo photo = photozServices.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
