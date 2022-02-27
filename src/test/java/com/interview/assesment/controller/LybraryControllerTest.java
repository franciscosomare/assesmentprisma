package com.interview.assesment.controller;

import com.interview.assesment.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LybraryController.class)
class LybraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    public void shouldCallgetUsersWhoBorrowedAtLeastABookEndPointCorrectly() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/v1/library/borrowed/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void shouldCallgetUsersWhoNotBorrowedAbookCorrectly() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/v1/library/not-borrowed/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void shouldCallgetUsersWhoNotBorrowedAbookCorrectly2() throws Exception {
        // when
        ResultActions resultActions = mockMvc.perform(get("/v1/library/not-borrowed/users/date?date=05/14/2008")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }
}