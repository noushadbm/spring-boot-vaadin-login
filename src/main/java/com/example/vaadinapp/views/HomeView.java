package com.example.vaadinapp.views;

import com.example.vaadinapp.security.SecurityService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.userdetails.UserDetails;

@Route("")
@PageTitle("Home | Vaadin App")
@PermitAll
public class HomeView extends VerticalLayout {

    private final SecurityService securityService;

    public HomeView(SecurityService securityService) {
        this.securityService = securityService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        UserDetails user = securityService.getAuthenticatedUser();

        H1 welcome = new H1("Welcome to Home Page!");
        Paragraph userInfo = new Paragraph("You are logged in as: " + user.getUsername());
        userInfo.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("color", "var(--lumo-primary-text-color)");

        Button logoutButton = new Button("Logout", event -> {
            securityService.logout();
        });
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(welcome, userInfo, logoutButton);
    }
}