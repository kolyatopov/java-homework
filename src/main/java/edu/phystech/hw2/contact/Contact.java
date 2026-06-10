package edu.phystech.hw2.contact;

import java.util.regex.Pattern;

public record Contact(String username, String email) implements Comparable<Contact> {
    public static final String UNKNOWN_EMAIL = "unknown";

    private static final Pattern GMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");

    public Contact {
        if (username == null || username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }
        if (UNKNOWN_EMAIL.equals(email)) {
            // single-arg constructor path: no email validation
        } else {
            if (email == null || email.isBlank()) {
                throw new InvalidContactFieldException("email");
            }
            if (!GMAIL_PATTERN.matcher(email).matches()) {
                throw new InvalidContactFieldException("email");
            }
        }
    }

    public Contact(String username) {
        this(username, UNKNOWN_EMAIL);
    }

    @Override
    public int compareTo(Contact o) {
        return Integer.compare(username.length(), o.username.length());
    }
}