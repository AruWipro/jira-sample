package com.api.jira;


import com.api.jira.model.TicketRequest;
import com.api.jira.model.TicketResponse;
import com.api.jira.repository.JiraRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class JiraCounterTest {

   // @Test
    public void testSummationWithConcurrency() throws InterruptedException {
        int numberOfThreads = 20;
        ExecutorService service = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        JiraRepository repo = new JiraRepository();
        TicketRequest request = new TicketRequest("Test","Custom Descriptopn");
        request.setProjectType("DEV");

        TicketRequest request2 = new TicketRequest("Jist","Custom Descriptopn");
        request2.setProjectType("QA");
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < numberOfThreads/2; i++) {
            service.submit(() -> {
                TicketResponse res = repo.addTicket(request);
                System.out.println("res id is"+res.getTicketNumber());
                latch.countDown();
            });
        }

        for (int j = numberOfThreads/2 + 1; j < numberOfThreads; j++) {
            service.submit(() -> {
                TicketResponse res = repo.addTicket(request2);
                System.out.println("res2 id is"+res.getTicketNumber());
                latch.countDown();
            });
        }
        latch.await();
        //assertEquals(numberOfThreads, res.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        new JiraCounterTest().testSummationWithConcurrency();
    }
}


