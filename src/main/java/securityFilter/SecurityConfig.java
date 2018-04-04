package securityFilter;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_NONUSER = "NON USER";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configure For "USER" Role.
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/manage_account");

        mapConfig.put(ROLE_USER, urlPatterns1);

        // Configure For "NONUSER" Role.
        List<String> urlPatterns2 = new ArrayList<>();

        /*urlPatterns2.add("/register");*/
        urlPatterns2.add("/login");

        mapConfig.put(ROLE_NONUSER, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
