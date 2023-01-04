package at.bmlv.test.demo.rest.exception;

import lombok.Getter;

@Getter
public class PersonNotFoundException extends NoStacktraceException {
    private final String name;

    public PersonNotFoundException(String name) {
        super("No name '" + name + "' found", null, false);
        this.name = name;
    }
}
