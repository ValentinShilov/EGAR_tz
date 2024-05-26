package org.example.egarapp.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
public class DocumentDto {
    int id;
    String name;
    LocalDateTime date;
    String type;

    public DocumentDto(int id, String name, String type) {
        this.id = id;
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        this.date = LocalDateTime.now();
        this.type = type;
    }
}
