package com.interview.assesment.controller;

import com.interview.assesment.model.UserLibrary;
import com.interview.assesment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "v1/library")
public class LybraryController {

    private final LibraryService libraryService;

    @Autowired
    public LybraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping(
            path = "/borrowed/users",
            produces = "application/json"
    )
    public ResponseEntity<List<UserLibrary>> getUsersWhoBorrowedAtLeastABook() throws IOException {
        return new ResponseEntity<>(libraryService.getUsersWhoBorrowedAtLeastABook(), HttpStatus.OK);
    }

    @GetMapping(
            path = "/not-borrowed/users",
            produces = "application/json"
    )
    public ResponseEntity<List<UserLibrary>> getUsersWhoNotBorrowedAbook() throws IOException {
        return new ResponseEntity<>(libraryService.getUsersWhoNotBorrowedAbook(), HttpStatus.OK);
    }

    @GetMapping(
            path = "/not-borrowed/users/date",
            produces = "application/json"
    )
    public ResponseEntity<List<UserLibrary>> getUserWithABorrowBookInADate(@RequestParam("date")
                                                                           @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate date) throws IOException {
        return new ResponseEntity<>(libraryService.getUserWhoBorrowedABookInADate(date), HttpStatus.OK);
    }

}
