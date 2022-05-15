package com.biggergames.tbslapi.controller.match;


import com.biggergames.tbslapi.client.response.DefaultResponse;
import com.biggergames.tbslapi.validation.constraints.FileValueSource;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("MatchControllerTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final HttpHeaders headers = new HttpHeaders();

    @Nested
    @DisplayName("@PostMapping(\"/v1/match-score\")")
    class matchScore {

        @ParameterizedTest
        @FileValueSource(root = "v1/match-score/checkTheBodyExpression-HomeNotEmpty")
        @DisplayName("Check the body expression - HomeNotEmpty")
        void checkTheBodyExpressionHomeNotEmpty(final String content) throws Exception {
            MvcResult mvcResult = mockMvc.perform(post("/v1/match-score").contentType(APPLICATION_JSON)
                            .headers(headers)
                            .content(content))
                    .andExpect(status().isBadRequest()).andReturn();

            DefaultResponse defaultResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DefaultResponse.class);
            assertNotNull(defaultResponse.getTimestamp());
            assertEquals(HttpStatus.BAD_REQUEST.value(), defaultResponse.getStatus());
            assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), defaultResponse.getError());
            assertTrue(defaultResponse.getMessage().contains("home value cannot be empty"));
        }

        @ParameterizedTest
        @FileValueSource(root = "v1/match-score/checkTheBodyExpression-GuestNotEmpty")
        @DisplayName("Check the body expression - GuestNotEmpty")
        void checkTheBodyExpressionGuestNotEmpty(final String content) throws Exception {
            MvcResult mvcResult = mockMvc.perform(post("/v1/match-score").contentType(APPLICATION_JSON)
                            .headers(headers)
                            .content(content))
                    .andExpect(status().isBadRequest()).andReturn();

            DefaultResponse defaultResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DefaultResponse.class);
            assertNotNull(defaultResponse.getTimestamp());
            assertEquals(HttpStatus.BAD_REQUEST.value(), defaultResponse.getStatus());
            assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), defaultResponse.getError());
            assertTrue(defaultResponse.getMessage().contains("guest value cannot be empty"));
        }

        @ParameterizedTest
        @FileValueSource(root = "v1/match-score/checkTheBodyExpression-HScoreNotCorrect")
        @DisplayName("Check the body expression - HScoreNotCorrect")
        void checkTheBodyExpressionHScoreNotCorrect(final String content) throws Exception {
            MvcResult mvcResult = mockMvc.perform(post("/v1/match-score").contentType(APPLICATION_JSON)
                            .headers(headers)
                            .content(content))
                    .andExpect(status().isBadRequest()).andReturn();

            DefaultResponse defaultResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DefaultResponse.class);
            assertNotNull(defaultResponse.getTimestamp());
            assertEquals(HttpStatus.BAD_REQUEST.value(), defaultResponse.getStatus());
            assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), defaultResponse.getError());
            assertTrue(defaultResponse.getMessage().contains("hScore value must be bigger than 0"));
        }

        @ParameterizedTest
        @FileValueSource(root = "v1/match-score/checkTheBodyExpression-GScoreNotCorrect")
        @DisplayName("Check the body expression - GScoreNotCorrect")
        void checkTheBodyExpressionGScoreNotCorrect(final String content) throws Exception {
            MvcResult mvcResult = mockMvc.perform(post("/v1/match-score").contentType(APPLICATION_JSON)
                            .headers(headers)
                            .content(content))
                    .andExpect(status().isBadRequest()).andReturn();

            DefaultResponse defaultResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DefaultResponse.class);
            assertNotNull(defaultResponse.getTimestamp());
            assertEquals(HttpStatus.BAD_REQUEST.value(), defaultResponse.getStatus());
            assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), defaultResponse.getError());
            assertTrue(defaultResponse.getMessage().contains("gScore value must be bigger than 0"));
        }


        @ParameterizedTest
        @FileValueSource(root = "v1/match-score/success")
        @DisplayName("Success")
        void success(final String content) throws Exception {
            MvcResult mvcResult = mockMvc.perform(post("/v1/match-score").contentType(APPLICATION_JSON)
                            .headers(headers)
                            .content(content))
                    .andExpect(status().isOk()).andReturn();

            DefaultResponse defaultResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DefaultResponse.class);
            assertNotNull(defaultResponse.getTimestamp());
            assertEquals(HttpStatus.OK.value(), defaultResponse.getStatus());
            assertNull(defaultResponse.getError());
        }
    }
}

