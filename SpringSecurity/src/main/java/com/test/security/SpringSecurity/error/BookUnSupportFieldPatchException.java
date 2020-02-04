package com.test.security.SpringSecurity.error;

import java.util.Set;

public class BookUnSupportFieldPatchException extends RuntimeException {
    public BookUnSupportFieldPatchException(Set<String> key) {
        super("Filed " + key.toString() + " update is not allow");
    }
}
