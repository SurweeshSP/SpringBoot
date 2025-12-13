package com.surweesh.summa.photo.clone;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    private String id;
    @NotEmpty
    private String fileName;
    @JsonIgnore
    private byte[] data;

    public Photo() {
        // required by Jackson
    }

    public Photo(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
