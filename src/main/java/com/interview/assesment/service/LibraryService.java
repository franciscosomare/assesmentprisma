package com.interview.assesment.service;

import com.interview.assesment.model.UserLibrary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface LibraryService {

    List<UserLibrary> getUsersWhoBorrowedAtLeastABook() throws IOException;

    List<UserLibrary> getUsersWhoNotBorrowedAbook() throws IOException;

    List<UserLibrary> getUserWhoBorrowedABookInADate(LocalDate date) throws IOException;
}
