package dev.raj.filesystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File extends BaseModel {

    String name;
    String pathDirectory;



}
