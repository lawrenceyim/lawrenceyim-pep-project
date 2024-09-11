package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService = new AccountService();
    private MessageService messageService = new MessageService();

    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::messagesHandler);
        return app;
    }

    private void registerHandler(Context context) {
        Account account = context.bodyAsClass(Account.class);
        account = accountService.addAccount(account);
        if (account != null) {
            context.json(account).status(200);
            return;
        }
        context.status(400);
    }

    private void loginHandler(Context context) {
        Account account = context.bodyAsClass(Account.class);
        account = accountService.getAccountByLoginCredentials(account);
        if (account != null) {
            context.json(account).status(200);
            return;
        }
        context.status(401);
    }

    private void messagesHandler(Context context) {
        Message message = context.bodyAsClass(Message.class);
        message = messageService.addMessage(message);
        if (message != null) {
            context.json(message).status(200);
            return;
        }
        context.status(400);
    }
}