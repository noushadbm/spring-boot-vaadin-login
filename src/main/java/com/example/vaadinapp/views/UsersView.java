package com.example.vaadinapp.views;

import com.example.vaadinapp.model.User;
import com.example.vaadinapp.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.format.DateTimeFormatter;

@Route(value = "users", layout = MainLayout.class)
@PageTitle("Users | Vaadin App")
@PermitAll
public class UsersView extends VerticalLayout {

    private final UserService userService;

    public UsersView(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Users Management");
        title.getStyle().set("color", "var(--lumo-primary-color)");

        Grid<User> userGrid = new Grid<>(User.class, false);
        userGrid.setWidthFull();
        userGrid.getStyle()
                .set("margin-top", "var(--lumo-space-m)")
                .set("border", "1px solid var(--lumo-contrast-20pct)");

        userGrid.addColumn(User::getId)
                .setHeader("User ID")
                .setWidth("80px")
                .setFlexGrow(0);

        userGrid.addColumn(User::getUsername)
                .setHeader("Username")
                .setWidth("150px")
                .setFlexGrow(0);

        userGrid.addColumn(User::getName)
                .setHeader("Full Name")
                .setFlexGrow(1);

        userGrid.addColumn(user -> user.getDob() != null ? user.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "")
                .setHeader("Date of Birth")
                .setWidth("130px")
                .setFlexGrow(0);

        userGrid.addColumn(User::getRolesAsString)
                .setHeader("Roles")
                .setFlexGrow(1);

        add(title, userGrid);
    }
}