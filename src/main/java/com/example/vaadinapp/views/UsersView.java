package com.example.vaadinapp.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "users", layout = MainLayout.class)
@PageTitle("Users | Vaadin App")
@PermitAll
public class UsersView extends VerticalLayout {

    public UsersView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Users Management");
        Paragraph description = new Paragraph("Manage user accounts and permissions.");

        add(title, description);
    }
}