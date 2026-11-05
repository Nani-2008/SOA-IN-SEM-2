package com.example.ms1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdditionController.class)
@Import(AdditionService.class)
class AdditionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
        "10, 20, 30",
        "0, 0, 0",
        "-5, -3, -8",
        "-10, 15, 5",
        "100, 200, 300",
        "2147483647, 1, 2147483648",
        "-2147483648, -1, -2147483649"
    })
    void addsTwoNumbers(int a, int b, long expected) throws Exception {
        mockMvc.perform(get("/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
            .andExpect(status().isOk())
            .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/add", "/add?a=10", "/add?b=20",
        "/add?a=abc&b=20", "/add?a=10&b=abc",
        "/add?a=2147483648&b=1"})
    void rejectsMissingOrInvalidParameters(String url) throws Exception {
        mockMvc.perform(get(url))
            .andExpect(status().isBadRequest());
    }
}
