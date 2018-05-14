package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

    private List<Message> messages;
    private HashMap<String, String> loginPairs;

    public MessageWall_and_RemoteLogin_Impl() {
        messages = new ArrayList<Message>();
        messages.add(new Message_Impl("admin", "Bienvenido!"));
        loginPairs = new HashMap<String, String>();
        loginPairs.put("victor", "aubin");
        loginPairs.put("admin", "admin");
        loginPairs.put("juan", "luis");
    }

    @Override
    public UserAccess connect(String usr, String passwd) throws Exception {
        if (loginPairs.containsKey(usr) && loginPairs.get(usr).equals(passwd)) {
            return new UserAccess_Impl(this, usr);
        } else {
            throw new Exception("bad credentials");
        }
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
