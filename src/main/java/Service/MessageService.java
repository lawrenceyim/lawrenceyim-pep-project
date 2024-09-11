package Service;

import DAO.AccountDao;
import DAO.MessageDao;
import java.util.List;
import Model.Message;

public class MessageService {
    private AccountDao accountDao;
    private MessageDao messageDao;

    public MessageService() {
        accountDao = new AccountDao();
        messageDao = new MessageDao();
    }

    public Message addMessage(Message message) {
        if (message.getMessage_text().isEmpty() ||
                message.getMessage_text().length() > 255 ||
                accountDao.getAccountById(message.posted_by) == null) {
            return null;
        }
        return messageDao.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }
}
