package me.krob.epos.storage.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private static final String STRING = "User [\n forename=%s,\n surname=%s, \n pin=%s \n]";

    String forename, surname;
    long pin;

    @Override
    public String toString() {
        return String.format(STRING, forename, surname, pin);
    }
}
