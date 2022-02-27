package com.interview.assesment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BorrowedBook {

    private String userName;
    private String book;
    private LocalDate borrowedFrom;
    private LocalDate borowedTo;

}
