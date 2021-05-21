package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RequiredArgsConstructor
@RestController
@Tag(name = "Asynchronous  messaging controller", description = "Use ActiveMQ queue")
@RequestMapping("/async")
public class ProducerController {
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    @PostMapping()
    @Operation(summary = "Post employee data via ActiveMQ message")
    public String publishMessage(@RequestBody Employee employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String employeeString = mapper.writeValueAsString(employee);
        jmsTemplate.convertAndSend(queue, employeeString);
        return employeeString;
    }
}
