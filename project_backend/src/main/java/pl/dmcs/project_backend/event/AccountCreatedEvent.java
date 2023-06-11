package pl.dmcs.project_backend.event;

import org.springframework.context.ApplicationEvent;
import pl.dmcs.project_backend.model.Account;

public class AccountCreatedEvent extends ApplicationEvent {

    private final Account user;

    public AccountCreatedEvent(Account user) {

        super(user);
        this.user = user;
    }
    public Account getUser() {
        return user;
    }
}
