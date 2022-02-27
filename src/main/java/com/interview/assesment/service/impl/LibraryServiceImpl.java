package com.interview.assesment.service.impl;

import com.interview.assesment.model.BorrowedBook;
import com.interview.assesment.model.UserLibrary;
import com.interview.assesment.service.LibraryService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final String CSV_BORROWED_FILE = "csv/borrowed.csv";
    private static final String CSV_USERS_FILE = "csv/user.csv";
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String EMPTY = "";

    @Override
    public List<UserLibrary> getUsersWhoBorrowedAtLeastABook() throws IOException {
        return mapSetUsersBorrowToList(getUserLibraries(), getUsersWithBorrowBook());
    }

    @Override
    public List<UserLibrary> getUsersWhoNotBorrowedAbook() throws IOException {
        return getUserWithNotBorrowBook(getUserLibraries(), getUsersWithBorrowBook());
    }

    @Override
    public List<UserLibrary> getUserWhoBorrowedABookInADate(LocalDate date) throws IOException {
        List<BorrowedBook> borrowedBooks = getBorrowedBooks();

        Set<String> booksDate = borrowedBooks.stream()
                .filter(x -> x.getBorrowedFrom().equals(date))
                .map(BorrowedBook::getUserName)
                .collect(Collectors.toSet());

        return mapSetUsersBorrowToList(getUserLibraries(), booksDate);
    }

    private List<BorrowedBook> getBorrowedBooks() throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.GERMANY);

        CSVReader csvReader = getCSVReader(CSV_BORROWED_FILE);

        List<String[]> readers = csvReader.readAll();
        List<BorrowedBook> borrowedBooks = new ArrayList<>();

        for (String[] read : readers) {
            if (read[0].length() > 1) {
                BorrowedBook borrowedBook = BorrowedBook.builder()
                        .userName(read[0])
                        .book(read[1])
                        .borrowedFrom(LocalDate.parse(read[2], formatter))
                        .borowedTo(LocalDate.parse(read[3], formatter))
                        .build();
                borrowedBooks.add(borrowedBook);
            }
        }

        return borrowedBooks;

    }

    private List<UserLibrary> getUserLibraries() throws IOException {
        CSVReader userCSVReader = getCSVReader(CSV_USERS_FILE);

        List<String[]> readers = userCSVReader.readAll();
        List<UserLibrary> userLibraries = new ArrayList<>();

        for (String[] read : readers) {
            if (read[0].length() > 1) {
                UserLibrary userLibrary = UserLibrary.builder()
                        .name(read[0])
                        .firstName(read[1])
                        .memberSince(read[2])
                        .memberTill(read[3])
                        .gender(read[4])
                        .build();
                userLibraries.add(userLibrary);
            }
        }
        return userLibraries;
    }

    private Set<String> getUsersWithBorrowBook() throws IOException {
        CSVReader borrowedCSVReader = getCSVReader(CSV_BORROWED_FILE);

        return borrowedCSVReader.readAll().stream()
                .map(book -> book[0])
                .filter(user -> !user.equals(EMPTY))
                .collect(Collectors.toSet());

    }

    private CSVReader getCSVReader(String csvBorrowedFile) throws FileNotFoundException {
        URL urlBorrowedCsv = Thread.currentThread().getContextClassLoader()
                .getResource(csvBorrowedFile);

        return new CSVReaderBuilder(new FileReader(Objects.requireNonNull(urlBorrowedCsv).getPath()))
                .withSkipLines(1)
                .build();
    }

    private List<UserLibrary> getUserWithNotBorrowBook(List<UserLibrary> userLibraries, Set<String> borrowedBooks) {
        return userLibraries.stream()
                .filter(x -> !borrowedBooks.contains(x.getName().concat(",").concat(x.getFirstName())) && x.getMemberTill().isEmpty())
                .collect(Collectors.toList());
    }

    private List<UserLibrary> mapSetUsersBorrowToList(List<UserLibrary> userLibraries, Set<String> borrowedBooks) {
        return userLibraries.stream()
                .filter(x -> borrowedBooks.contains(x.getName().concat(",").concat(x.getFirstName())))
                .collect(Collectors.toList());
    }

}
