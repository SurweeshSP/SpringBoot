package com.surweesh.summa.photo.clone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, String> {
}