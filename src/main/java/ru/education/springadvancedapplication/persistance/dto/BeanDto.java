package ru.education.springadvancedapplication.persistance.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanDto {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String type;

    @CsvBindByPosition(position = 2)
    private String value;
}
