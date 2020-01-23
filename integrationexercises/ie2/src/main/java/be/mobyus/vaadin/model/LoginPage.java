package be.mobyus.vaadin.model;

import com.vaadin.ui.*;
import com.vaadin.v7.ui.TextField;

public class LoginPage extends Template {
    @Override
    protected Component getBody() {

        VerticalLayout verticalLayout = new VerticalLayout();
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated){
                UI.getCurrent().getNavigator().navigateTo("SearchPage");
            }
        });
        verticalLayout.addComponent(loginForm);
        verticalLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        verticalLayout.setSizeFull();

        return verticalLayout;
    }

    private boolean authenticate(LoginForm.LoginEvent e){
       return true;
    }


}
