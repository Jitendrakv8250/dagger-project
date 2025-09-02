package framework.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ResponseContext {
       private final ObjectMapper objectMapper;

       public ResponseContext(){
               this.objectMapper=new ObjectMapper();
               objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
       }

        public String asJson(Object obj) {
            try {
                return this.objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert object to JSON", e);
            }
        }
        public String asJson(String jsonString) {
            try {
                Object json = this.objectMapper.readValue(jsonString, Object.class);
                return this.objectMapper.writeValueAsString(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Invalid JSON string", e);
            }
        }
    }
