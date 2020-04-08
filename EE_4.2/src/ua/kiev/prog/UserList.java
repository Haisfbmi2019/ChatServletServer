package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class UserList {
    private static final UserList usList = new UserList();

    private final List<User> listOfUsers = new LinkedList<>();

    private UserList() {
        listOfUsers.add(new User("user1", "111"));
        listOfUsers.add(new User("admin", "admin"));
    }

    public boolean checkUser(User us) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.getLogin().equals(us.getLogin()) && user.getPassword().equals(us.getPassword())) {
                user.setPresent("online");
                res = true;
            }
        }
        return res;
    }

    public boolean checkUserByLogin(User us) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                res = true;
            }
        }
        return res;
    }

    public boolean setOffline(User us) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                user.setPresent("offline");
                res = true;
            }
        }
        return res;
    }

    public boolean createChat(User us) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                user.setChatName(us.getChatName());
                res = true;
            }
        }
        return res;
    }

    public boolean deleteChat(String str) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.getChatName().equals(str)) {
                user.setChatName("no chat");
                res = true;
            }
        }
        return res;
    }

    public boolean logoutChat(String login) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                if (user.getChatName().equals("no chat")) {
                    break;
                } else {
                    user.setChatName("no chat");
                    res = true;
                }
            }
        }
        return res;
    }

    public boolean loginChat(String chatName, String login) {
        boolean res = false;
        for (User user : listOfUsers) {
            if (user.getChatName().equals(chatName)) {
                for (User us : listOfUsers) {
                    if (us.getLogin().equals(login)) {
                        us.setChatName(chatName);
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    public String checkLogin(String login) {
        String myChatName = "";
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                myChatName = user.getChatName();
            }
        }
        return myChatName;
    }

    public static UserList getInstance() {
        return usList;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
