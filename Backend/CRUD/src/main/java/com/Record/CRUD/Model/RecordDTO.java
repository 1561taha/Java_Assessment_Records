package com.Record.CRUD.Model;

import lombok.Data;

@Data
public class RecordDTO {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private String categoryName;
    private Boolean active;
}
