package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

    private List<Message> messages;

    public MessageWall_and_RemoteLogin_Impl() {
        messages = new ArrayList<Message>();
        messages.add(new Message_Impl("admin", "Bienvenido!"));
    }

    @Override
    public UserAccess connect(String usr, String passwd) {
        return new UserAccess_Impl(this, usr);
    }

    @Override
    public void put(String user, String msg) {
        messages.add(new Message_Impl(user, msg));
    }

    @Override
    public boolean delete(String user, int index) {
        try {
            messages.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Message getLast() {
        return messages.get(messages.size() - 1);
    }

    @Override
    public int getNumber() {
        return messages.size();
    }

    @Override
    public List<Message> getAllMessages() {
        return messages;
    }

    
}
