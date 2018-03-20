package com.spring.boot.kafka.demo.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.kafka.demo.messaging.TopicWriter;
import com.spring.boot.kafka.demo.storage.MessageStorage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

@RestController
@RequestMapping(value = "/customer/details")
public class RestApiController {

    @Autowired
    TopicWriter producer;
    
    @Autowired
    MessageStorage storage;

    @GetMapping(value = "/getDetails")
    public String producer(@RequestParam("idNumber") String idNumber) {
        
        producer.send(idNumber);
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String messages = storage.toString();
        storage.clear();
        
        JSONObject response = new JSONObject(messages);
        return response.toString();
    
    }
}
