package com.ums.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String cin; // unique id
    private String username;
    private String passwd;
    private UserState state;
    private Role role;
}
