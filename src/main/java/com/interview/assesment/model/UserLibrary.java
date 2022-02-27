package com.interview.assesment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLibrary {

    private String name;
    private String firstName;
    private String memberSince;
    private String memberTill;
    private String gender;

}
