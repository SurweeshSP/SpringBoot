package com.surweesh.summa.photo.clone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    private final Map<String, Photo> db = new HashMap<>();

    public Collection<Photo> getAll() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo save(Photo photo) {
        db.put(photo.getId(), photo);
        return photo;
    }

    public boolean delete(String id) {
        return db.remove(id) != null;
    }
}
