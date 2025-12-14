package com.surweesh.summa.photo.clone;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    private final PhotoRepository repo;

    public PhotoService(PhotoRepository repo) {
        this.repo = repo;
    }

    public List<Photo> getAll() {
        return repo.findAll();
    }

    public Photo get(String id) {
        return repo.findById(id).orElse(null);
    }

    public Photo save(Photo photo) {
        return repo.save(photo);
    }

    public boolean delete(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}