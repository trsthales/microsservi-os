package com.example.ecommerce.payment.web;

import com.example.ecommerce.payment.PaymentServiceApplication;
import com.example.ecommerce.payment.adapter.inmemory.InMemoryPaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PaymentServiceApplication.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    InMemoryPaymentRepository repo;

    @Test
    void postAndGet() throws Exception {
        Map<String, Object> req = new HashMap<>();
        req.put("orderId", "o-1");
        req.put("amount", new BigDecimal("12.50"));

        String body = mapper.writeValueAsString(req);

        mvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.startsWith("/payments/")))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }
}

