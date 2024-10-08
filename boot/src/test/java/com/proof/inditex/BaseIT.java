package com.proof.inditex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class BaseIT {

  protected final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  protected MockMvc mockMvc;

  protected String toJson(Object obj) throws JsonProcessingException {
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    return objectMapper.writeValueAsString(obj);
  }

}
