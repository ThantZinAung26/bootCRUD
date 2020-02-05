package com.test.security.SpringSecurity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.security.RunAs;
import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository repository;

    @Before
    public void init() {
        Book book = new Book(1L, "Efective Java second Edition", "Joshua J. Bloch",new BigDecimal("199.15"));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(book));
    }

    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Efective Java second Edition")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("Joshua J. Bloch")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(199.15)));
    }

    @Test
    public void find_noLogin_401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
