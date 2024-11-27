package com.example.photoz_clone.service;
import com.example.photoz_clone.model.Photo;
import com.example.photoz_clone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotozServices {

    private final PhotozRepository photozRepository;

    public PhotozServices(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> get() {
        return photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
         photozRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photozRepository.save(photo);
        return photo;
    }
}
