package me.krob.epos.storage.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class User {
    private static final String STRING = "User [\n forename=%s,\n surname=%s, \n pin=%s \n]";

    String forename, surname;
    long pin;

    public User() {

    }

    @Override
    public String toString() {
        return String.format(STRING, forename, surname, pin);
    }
}
