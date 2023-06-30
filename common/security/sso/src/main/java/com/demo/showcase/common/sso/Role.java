package com.demo.showcase.common.sso;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER,
    ADMIN;

    public static List<String> names(){
        return Arrays.stream(Role.values()).map(x -> x.name()).toList();
    }
}
