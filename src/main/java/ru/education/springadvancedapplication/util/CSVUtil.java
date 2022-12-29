package ru.education.springadvancedapplication.util;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import ru.education.springadvancedapplication.persistance.dto.BeanDto;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class CSVUtil {

    private CSVUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static List<BeanDto> parseCsv(String filename) {
        var res = new ClassPathResource(filename);
        try (var inputStream = res.getInputStream()) {
            return new CsvToBeanBuilder<BeanDto>(new InputStreamReader(inputStream))
                    .withType(BeanDto.class)
                    .withIgnoreEmptyLine(true)
                    .withSeparator('|')
                    .withSkipLines(0)
                    .build()
                    .parse();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }
}
