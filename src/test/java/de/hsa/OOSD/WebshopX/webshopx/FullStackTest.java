package de.hsa.OOSD.WebshopX.webshopx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FullStackTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnWebshopX() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("WebshopX")));
    }

    @Test
    public void shouldReturnMaria() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Maria mit Kind und einem")));

    }

    @Test
    public void shouldReturnMariaPage() throws Exception {
        ResultActions test = this.mockMvc.perform(get("/Maria%20mit%20Kind%20und%20einem%20Mönch")).andDo(print()).andExpect(status().isOk());
        test.andExpect(content().string(containsString("Article")));

    }

    @Test
    public void shouldReturnJosephAfterSearch() throws Exception {
        ResultActions test = this.mockMvc.perform(get("/?keyword=Joseph")).andDo(print()).andExpect(status().isOk());
        test.andExpect(content().string(containsString("Max")));


    }

}
