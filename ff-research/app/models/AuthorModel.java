package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorModel {
    private int id;
    private String firstName;
    private String lastName;
    public AuthorModel(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
