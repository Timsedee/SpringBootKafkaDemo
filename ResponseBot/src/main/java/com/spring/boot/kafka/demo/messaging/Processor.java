package com.spring.boot.kafka.demo.messaging;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    
    @Autowired
    TopicWriter writer;

    public String sendToResponseQueue(String content) {

        LOGGER.info("Reading Data from Topic = '{}'", content);
        
        Map<String, String> responseMap = new HashMap<>();
        
        if ("27858589".equals(content)) {
            responseMap.put("FirstName","David");
            responseMap.put("Surname","Setim");
            responseMap.put("IdNumber","27858589");
            responseMap.put("Status","00");
            responseMap.put("Message","Recond Was Found");
            
        }else{
            responseMap.put("Status","99");
            responseMap.put("Message","Recond Was Not Found");
        }

        JSONObject response = new JSONObject(responseMap);
        
        writer.send(response.toString());
        
        return response.toString();
    }

}
