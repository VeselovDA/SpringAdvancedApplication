package ru.education.springadvancedapplication.persistance.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    int id;
    String name;
    String status;
    String comment;

}
