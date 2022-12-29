package ru.education.springadvancedapplication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;
import ru.education.springadvancedapplication.persistance.dto.BeanDto;
import ru.education.springadvancedapplication.service.BeanService;
import ru.education.springadvancedapplication.util.CSVUtil;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVBeanService<T> implements BeanService {
    @Value("${defaultFileName}")
    private String defaultFileName;
    private final ObjectMapper objectMapper;
    private final GenericWebApplicationContext context;
    private final Set<String> dynamicRegisterBeans;

    @Override
    public Map<String, Object> getAllRegisterBean() {
        return dynamicRegisterBeans.stream().collect(Collectors.toMap(element -> element, context::getBean));
    }

    @Override
    public void registerBeanFromFile(String fileName) {
        fileName = checkNullableFileName(fileName);
        var beanDtoList = CSVUtil.parseCsv(fileName);
        beanDtoList.forEach(this::registerBeanToContext);

    }

    private String checkNullableFileName(String fileName) {
        if (Objects.isNull(fileName) || fileName.isBlank()) {
            return defaultFileName;
        }
        return fileName;
    }

    @SneakyThrows
    private void registerBeanToContext(BeanDto beanDto) {
        var id = beanDto.getId();
        if(dynamicRegisterBeans.contains(id)){
            log.warn("Bean with name {} already exist",id);
            return;
        }
        var clazz = Class.forName(beanDto.getType());
        @SuppressWarnings("unchecked cast")
        var type = (Class<T>) clazz;
        var obj = convertObject(type, beanDto.getValue());
        context.registerBean(id, type, () -> obj);
        dynamicRegisterBeans.add(id);
    }

    @SneakyThrows
    private T convertObject(Class<T> clazz, String rawValue) {
        try {
            return objectMapper.readValue(rawValue, clazz);
        } catch (JsonProcessingException ex) {
            return objectMapper.convertValue(rawValue, clazz);
        }
    }
}
