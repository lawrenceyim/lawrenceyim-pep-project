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
            accountDao.getAccountById(message.posted_by) == null) 
        {
            return null;
        }
        return messageDao.insertMessage(message);
    }

    public Message deleteMessageById(int messageId) {
        Message messageToDelete = messageDao.getMessageById(messageId);
        if (messageToDelete == null) {
            return null;
        }
        messageDao.deleteMessageById(messageId);
        return messageToDelete;
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    public Message updateMessageById(Message message) {
        if (message.getMessage_text().isEmpty() ||
            message.getMessage_text().length() > 255 ||
            messageDao.getMessageById(message.getMessage_id()) == null) 
        {
            return null;
        }
        messageDao.updateMessageById(message);
        return messageDao.getMessageById(message.getMessage_id());
    }
}
