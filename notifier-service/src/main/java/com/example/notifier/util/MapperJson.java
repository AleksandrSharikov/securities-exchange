package com.example.notifier.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapperJson {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private  <T> T getValueFromJson(String json, String fieldName, Class<T> valueType) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(json);
            JsonNode fieldNode = rootNode.get(fieldName);
            if (fieldNode == null) {
                throw new IllegalArgumentException("Field " + fieldName + " not found in JSON");
            }
            return objectMapper.convertValue(fieldNode, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> mapperFromJsonToHashMap(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public long getUserId(String json) {
        return getValueFromJson(json, "user_id", Long.class);
    }

    public long getTemplateId(String json) {
        return getValueFromJson(json, "template_id", Long.class);
    }

    public String getServiceSender(String json) {
        return getValueFromJson(json, "serviceSender", String.class);
    }

    public String getSubjectMessage(String json) {
        return getValueFromJson(json, "subject", String.class);
    }

    public String createJson(String nameField, long valueField) {
        Map<String, Long> data = new HashMap<>();
        data.put(nameField, valueField);
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
