package com.example.vaadinapp.views;

import com.example.vaadinapp.model.User;
import com.example.vaadinapp.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.time.format.DateTimeFormatter;

@Route(value = "users", layout = MainLayout.class)
@PageTitle("Users | Vaadin App")
@PermitAll
public class UsersView extends VerticalLayout {

    private final UserService userService;
    private Grid<User> userGrid;

    public UsersView(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        H2 title = new H2("Users Management");
        title.getStyle().set("color", "var(--lumo-primary-color)");

        Button addButton = new Button("+ Add User");
        addButton.addClickListener(e -> openAddUserDialog());

        header.add(title, addButton);
        add(header);

        userGrid = new Grid<>(User.class, false);
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

        add(userGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        userGrid.setItems(userService.getAllUsers());
    }

    private void openAddUserDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New User");
        dialog.setWidth("450px");

        TextField usernameField = new TextField("Username");
        usernameField.setWidthFull();
        usernameField.setRequired(true);

        TextField nameField = new TextField("Full Name");
        nameField.setWidthFull();
        nameField.setRequired(true);

        DatePicker dobPicker = new DatePicker("Date of Birth");
        dobPicker.setWidthFull();

        CheckboxGroup<String> rolesGroup = new CheckboxGroup<>();
        rolesGroup.setLabel("Roles");
        rolesGroup.setItems("USER", "ADMIN", "MANAGER", "EDITOR", "VIEWER");
        rolesGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        rolesGroup.setWidthFull();

        VerticalLayout formLayout = new VerticalLayout(usernameField, nameField, dobPicker, rolesGroup);
        formLayout.setSpacing(true);
        formLayout.setPadding(false);

        dialog.add(formLayout);

        Button saveButton = new Button("Save", e -> {
            String username = usernameField.getValue();
            String name = nameField.getValue();
            LocalDate dob = dobPicker.getValue();
            Set<String> roles = rolesGroup.getValue();

            if (username.isEmpty() || name.isEmpty()) {
                usernameField.setInvalid(true);
                nameField.setInvalid(true);
                return;
            }

            User newUser = new User(username, name, dob != null ? dob : LocalDate.now(), roles != null ? roles : new HashSet<>());
            userService.saveUser(newUser);
            refreshGrid();
            dialog.close();
        });

        Button cancelButton = new Button("Cancel", e -> dialog.close());

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        dialog.getFooter().add(buttonLayout);
        dialog.open();
    }
}