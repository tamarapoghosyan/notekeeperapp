package com.example.lenovo.notekeeperapp.utils.user;

import com.example.lenovo.notekeeperapp.pojos.User;
import com.example.lenovo.notekeeperapp.utils.StorageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 06.05.2017.
 */
public class FileUserStorage extends UserStorage{
    private static final String USERS_LIST_KEY = "files.ttt";


    private UsersList usersList;

    public FileUserStorage() {
        super();

        usersList = (UsersList) StorageHelper.deserialize(USERS_LIST_KEY);

        if(usersList == null){
            usersList = new UsersList();
        }
    }

    public void registerUser(User user, UserFoundListener actionListener){
        if(!checkUsernameAvailable(user.getUsername())){
            notifyUserFound(null, actionListener);
        }

        getUsers().add(user);
        StorageHelper.serialize(USERS_LIST_KEY, usersList);
        notifyUserFound(user, actionListener);
    }

    protected List<User> getUsers(){
        return usersList.getUsers();
    }

    public void checkAndGetUser(String userName, String password, UserFoundListener actionListener){
        for (User u : usersList.getUsers()){
            if(u.getUsername().equalsIgnoreCase(userName) && u.getPassword().equals(password)){
                notifyUserFound(u, actionListener);
                return;
            }
        }

        notifyUserFound(null, actionListener);
    }

    private boolean checkUsernameAvailable(String username){
        for (User u : usersList.getUsers()){
            if(u.getUsername().equalsIgnoreCase(username)){
                return false;
            }
        }

        return true;
    }

    public void findUserByUsername(String username, UserFoundListener actionListener){
        for (User u : usersList.getUsers()){
            if(u.getUsername().equalsIgnoreCase(username)){
                notifyUserFound(u, actionListener);
                return;
            }
        }

        notifyUserFound(null, actionListener);
    }

    public void findUserById(long id, UserFoundListener actionListener){
        for (User u : usersList.getUsers()){
            if(u.getId() == id){
                notifyUserFound(u, actionListener);
                return;
            }
        }

        notifyUserFound(null, actionListener);
    }

    private static class UsersList implements Serializable {
        static final long serialVersionUID = -1;

        private List<User> users;

        UsersList() {
            users = new ArrayList<>();
        }

        List<User> getUsers() {
            return users;
        }
    }
}
