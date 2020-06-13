package Testings.TestingsController;

import Controllers.LoginController;
import Views.LoginView;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginControllerTest {
    @Mock
    private LoginController loginController;


    @Mock
    private LoginView loginView;

    @Test
    public void setUp() {
        loginController = mock(LoginController.class);
        loginView=mock(LoginView.class);
        loginController.lview=loginView;
        // define return value for method getUniqueId()
        loginController.lview.login.doClick();

    }

}