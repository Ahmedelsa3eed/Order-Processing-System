package csed.database.orderprocessingbackend.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

// Singleton design pattern
@Service
public class ActiveUserService {

    private static ActiveUserService instance = null;

    private static final HashMap<String, String[]> sessions = new HashMap<>();
    private static final HashMap<String, Long> users_Ids = new HashMap<>();
    private static final HashMap<String, String> emails = new HashMap<>();

    private ActiveUserService() {}

    public static ActiveUserService getInstance() {
        if(instance == null) {
            instance = new ActiveUserService();
        }
        return instance;
    }

    public String[] checkLogin(String sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId);
        }
        return new String[] {null, null};
    }

    public boolean checkIfEmailLoggedIn(String email) {
        return emails.containsKey(email);
    }

    public String getSessionID(String email) {
        return emails.get(email);
    }

    public void changeRole(String email, String role) {
        String sessionId = emails.get(email);
        sessions.replace(sessionId, new String[] {email, role});
    }

    public void login(String email, String role, Long id) {
        String newSessionId = UUID.randomUUID().toString();
        sessions.put(newSessionId, new String[]{email, role});
        emails.put(email, newSessionId);
        users_Ids.put(newSessionId, id);

        System.out.println("User " + email + " logged in with role " + role);

    }

    public String getEmailFromSessionId(String sessionId) {
        if (sessions.containsKey(sessionId))
            return sessions.get(sessionId)[0];
        return null;
    }

    public Long getUserIdFromSessionId(String sessionId){
        return users_Ids.get(sessionId);
    }

    public void deleteSession(String email){
        sessions.remove(emails.get(email));
        emails.remove(email);
    }
    public void logout(String sessionId) {
        emails.remove(getEmailFromSessionId(sessionId));
        sessions.remove(sessionId);
    }

}
