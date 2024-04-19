package com.crazzy.coding.review.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BaseService {

    protected JsonNode generateJsonNode(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);

        return jsonNode;
    }

    protected <T> T generateObjectFromString(String json , Class<T> clazz){
        T t = null;
        ObjectMapper mapper = new ObjectMapper();
        t = mapper.convertValue(json,clazz);
        return t;
    }

    protected <T> T generateObjectFromNode(JsonNode jsonNode, Class<T> tClass) throws JsonProcessingException {
        T t = null;
        ObjectMapper mapper = new ObjectMapper();
        t = mapper.treeToValue(jsonNode, tClass);
        return t;
    }
}
