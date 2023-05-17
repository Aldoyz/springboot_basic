package com.aldiichsantkj3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunStuffInsertModel {
    @JsonIgnore
    String id;
    @JsonIgnore
    String type;
    String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date releaseDate;
    Integer rating;
}
