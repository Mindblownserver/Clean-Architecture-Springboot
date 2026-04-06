package com.kharrat.cleanarchitecturetest.core.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String cin; // unique id
    private String username;
    private String pswdHash;
    private String state;
    private Role role;
}
