package com.example.ecommerce.cart.web;

import com.example.ecommerce.cart.adapter.out.inmemory.InMemoryCartRepository;
import com.example.ecommerce.cart.usecase.AddItemToCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = com.example.ecommerce.cart.CartServiceApplication.class)
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    InMemoryCartRepository repo;

    @BeforeEach
    void setup() {
        // clear repository store via reflection because field is private; simple approach: create new repo instance not possible here
    }

    @Test
    void addItemAndGetCart() throws Exception {
        UUID user = UUID.randomUUID();
        var req = new java.util.HashMap<String, Object>();
        req.put("productId", UUID.randomUUID());
        req.put("quantity", 2);
        req.put("priceSnapshot", new BigDecimal("5.00"));

        mvc.perform(post("/carts/" + user + "/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isNoContent());

        mvc.perform(get("/carts/" + user))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.toString()))
                .andExpect(jsonPath("$.items[0].quantity").value(2));
    }
}

