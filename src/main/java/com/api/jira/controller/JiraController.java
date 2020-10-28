package com.api.jira.controller;

import com.api.jira.exceptions.TicketNotFoundException;
import com.api.jira.model.TicketRequest;
import com.api.jira.model.TicketResponse;
import com.api.jira.repository.JiraRepository;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ticket")
@Api(value = "Swagger2DemoRestController", description = "REST APIs related to Student Entity!!!!")
public class JiraController {

    @Autowired
    public JiraRepository repo;
    @ApiOperation(value = "Post new tickets into the system ", response = TicketResponse.class, tags = "postTicket")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/", consumes = "application/json",produces = "application/json")
    public TicketResponse postTicket(@RequestBody TicketRequest request)
    {
        return repo.addTicket(request);
    }


    @GetMapping(path = "/{type}/{id}", produces = "application/json")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable String type,@PathVariable long id) throws TicketNotFoundException {
        TicketResponse response = repo.getTicketByTypeAndId(type,id)
                .orElseThrow( ()-> new TicketNotFoundException("Ticket not found.."));
            return ResponseEntity.ok().body(response);
    }

}

