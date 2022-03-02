package controller;

import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.HashMap;

public class SecurityController {
    private final DataAccessFacade dataAccessFacade;

    public SecurityController(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;
    }

    /**
     * Login with credential
     * @param userId user id
     * @param password password
     * @return True if login success, otherwise return false.
     */
    public Auth login(String userId, String password) {
        // Get all users
        HashMap<String, User> users = dataAccessFacade.readUserMap();
//        System.out.println(users.toString());

        User user = users.get(userId);
        if (user != null) {
            if ((user.getPassword().equals(password) && user.getId().equals(userId))) {
                return user.getAuthorization();
            }
        }

        return null;
    }
}
