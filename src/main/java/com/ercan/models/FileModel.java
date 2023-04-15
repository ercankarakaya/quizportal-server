package com.ercan.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "files")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileModel extends BaseModel{

    String name;
    String type;
    @Lob
    byte[] data;

    public FileModel(){

    }

    public FileModel(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
