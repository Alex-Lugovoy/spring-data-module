package ru.edu.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String name;
    private String language;
    private String category; // history, it, health etc...
}
