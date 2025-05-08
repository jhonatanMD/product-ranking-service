package com.ws;

import com.ws.application.usecase.RankProductsUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@AutoConfigureMockMvc
class AppRunTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RankProductsUseCase rankProductsUseCase;

    static Stream<Arguments> rankingTestCases() {
        return Stream.of(
                arguments("{\"salesUnits\": 0.3, \"stockRatio\": 0.7}", 0.74),
                arguments("{\"salesUnits\": 0.5, \"stockRatio\": 0.5}", 0.67),
                arguments("{\"salesUnits\": 0.2, \"stockRatio\": 0.8}", 0.82)
        );
    }

    @ParameterizedTest
    @MethodSource("rankingTestCases")
    @DisplayName("Should return correct ranked product list for given criteria")
    void shouldReturnCorrectRankedProducts(String criteria, double expectedScore) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/rank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(criteria)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score")
                        .value(expectedScore));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when invalid criteria is provided")
    void shouldReturnBadRequestWhenInvalidCriteria() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/rank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"invalidCriteria\": 0.5}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when request body is missing")
    void shouldReturnBadRequestWhenRequestBodyIsMissing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products/rank")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
