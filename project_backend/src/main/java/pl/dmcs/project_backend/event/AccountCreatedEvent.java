package pl.dmcs.project_backend.event;

import org.springframework.context.ApplicationEvent;
import pl.dmcs.project_backend.model.Account;
import pl.dmcs.project_backend.model.Person;

public class AccountCreatedEvent extends ApplicationEvent {

    private final Account user;
    private final Person person;

    public AccountCreatedEvent(Account user, Person person) {

        super(user);
        this.user = user;
        this.person = person;
    }
    public Account getUser() {
        return user;
    }

    public Person getPerson() {
        return person;
    }
}
