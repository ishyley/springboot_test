package com.example.photoz_clone.web;

import com.example.photoz_clone.model.Photo;
import com.example.photoz_clone.service.PhotozServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {


    private final PhotozServices photozServices;

    public PhotozController(PhotozServices photozServices) {
        this.photozServices = photozServices;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get(){
        return photozServices.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = photozServices.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping ("/photoz/{id}")
    public void delete(@PathVariable Integer id){
        photozServices.remove(id);

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
      return photozServices.save(file.getOriginalFilename(), file.getContentType() ,file.getBytes());


    }
}
