import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class LoginAppTest {
    @Test
    public void testInvalidEmail() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "unknown@example.com", "password123");
        assertNull("Authentication should fail for an email that does not exist.", userName);
    }


    @Test
    public void testEmptyEmail() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "", "password123");
        assertNull("Authentication should fail for an empty email input.", userName);
    }


    @Test
    public void testSQLInjectionAttempt() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "johndoe@example.com' OR '1'='1", "password123");
        assertNull("Authentication should fail for an SQL injection attempt.", userName);
    }


    @Test
    public void testNullEmail() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, null, "password123");
        assertNull("Authentication should fail for a null email input.", userName);
    }




    @Test
    public void testWhitespaceEmail() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, " johndoe@example.com ", "password123");
        assertNull("Authentication should fail for emails with leading or trailing whitespace.", userName);
    }

    @Test
    public void testInvalidPassword() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "johndoe@example.com", "wrongpassword");
        assertNull("Authentication should fail for incorrect password.", userName);
    }


    @Test
    public void testEmptyPassword() throws Exception {
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "johndoe@example.com", "");
        assertNull("Authentication should fail for an empty password input.", userName);
    }
}
