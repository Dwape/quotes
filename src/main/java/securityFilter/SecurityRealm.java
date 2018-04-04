package securityFilter;

import hibernate.ManageUser;
import model.User;
import org.securityfilter.realm.SimpleSecurityRealmBase;

public class SecurityRealm extends SimpleSecurityRealmBase {

    /**
     * Authenticate a user.
     *
     * Implement this method in a subclass to avoid dealing with Principal objects.
     *
     * @param username a username
     * @param password a plain text password, as entered by the user
     *
     * @return null if the user cannot be authenticated, otherwise a Pricipal object is returned
     */
    public boolean booleanAuthenticate(String username, String password) {
        User userAccount = ManageUser.verifyUser(username, password);
        return userAccount != null;
    }
}
