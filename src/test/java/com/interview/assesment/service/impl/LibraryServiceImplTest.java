package com.interview.assesment.service.impl;

import com.interview.assesment.model.UserLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LibraryServiceImplTest {

    private final LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Test
    public void shouldGetAllUsersWhoBorrewedAtLeastABookCorrectly() throws IOException {
        // when
        List<UserLibrary> userLibraries = libraryService.getUsersWhoBorrowedAtLeastABook();

        // then
        assertEquals(11, userLibraries.size());
    }

    @Test
    public void shouldGetAllUsersWhoNotBorrewedAtLeastABookCorrectly() throws IOException {
        // when
        List<UserLibrary> userLibraries = libraryService.getUsersWhoNotBorrowedAbook();

        // then
        assertEquals(0, userLibraries.size());
    }

    @Test
    public void shouldGetAllUsersWhoBorrewedAtLeastABookCorrectlyOnAGivenDate() throws IOException {
        // given
        LocalDate date = LocalDate.of(2008, 05, 14);
        // when
        List<UserLibrary> userLibraries = libraryService.getUserWhoBorrowedABookInADate(date);

        // then
        assertEquals(1, userLibraries.size());
        assertEquals("Chish", userLibraries.get(0).getName());
        assertEquals("Elijah", userLibraries.get(0).getFirstName());
    }

}