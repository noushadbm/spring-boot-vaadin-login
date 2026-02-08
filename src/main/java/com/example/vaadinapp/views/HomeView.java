package com.example.vaadinapp.views;

import com.example.vaadinapp.security.SecurityService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.userdetails.UserDetails;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Home | Vaadin App")
@PermitAll
public class HomeView extends VerticalLayout {

    public HomeView(SecurityService securityService) {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        UserDetails user = securityService.getAuthenticatedUser();

        H2 welcome = new H2("Welcome to Home Page!");
        Paragraph userInfo = new Paragraph("You are logged in as: " + user.getUsername());
        userInfo.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("color", "var(--lumo-primary-text-color)");

        Paragraph description = new Paragraph(
                "This is your home page. Use the menu on the left to navigate to different sections of the application."
        );

        add(welcome, userInfo, description);
    }
}