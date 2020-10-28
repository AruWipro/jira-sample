package com.api.jira.repository;

import com.api.jira.model.TicketRequest;
import com.api.jira.model.TicketResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class JiraRepository {
    private static AtomicLong id = new AtomicLong(0);
    private static Map<String,TicketResponse> dataStore = new HashMap<>();
    // Stores Project Type and the count of tickets associated with that
    private volatile static Map<String,Long> lookup = new HashMap<>();


     //ConcurrentMap<String,AtomicLong> lookup = new ConcurrentHashMap<>();
    public  TicketResponse addTicket(TicketRequest request) {
        //long ticketId = id.incrementAndGet();
        TicketResponse response = new TicketResponse();
        String projectType = request.getProjectType();
        long ticketId = getTicketId(request, projectType);
        response.setTitle(request.getProjectName());
        String jidNumber = projectType+"-"+ticketId;
        response.setTicketNumber(jidNumber);
        dataStore.put(jidNumber,response);
        return response;

    }

    private long getTicketId(TicketRequest request, String projectType) {
        synchronized (lookup) {
            if (lookup.get(projectType) != null) {
                lookup.put(projectType, lookup.get(projectType) + 1);
            } else {
                System.out.println("Empty..");
                lookup.put(request.getProjectType(), 1L);
            }
        }

        long ticketId = lookup.get(projectType);
        return ticketId;
    }

    public  Optional<TicketResponse> getTicketByTypeAndId(String type, long id) {
        String jiraID = type+"-"+id;
        return Optional.ofNullable(dataStore.get(jiraID));
    }
}

