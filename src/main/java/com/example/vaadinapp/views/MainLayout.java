package com.example.vaadinapp.views;

import com.example.vaadinapp.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.springframework.security.core.userdetails.UserDetails;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 appName = new H1("Vaadin Application");
        appName.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        UserDetails user = securityService.getAuthenticatedUser();
        Span username = new Span(user != null ? user.getUsername() : "Guest");
        username.getStyle()
                .set("font-size", "var(--lumo-font-size-s)")
                .set("color", "var(--lumo-secondary-text-color)")
                .set("margin-right", "1rem");

        Button logoutButton = new Button("Logout", new Icon(VaadinIcon.SIGN_OUT));
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logoutButton.addClickListener(e -> securityService.logout());

        HorizontalLayout userSection = new HorizontalLayout(username, logoutButton);
        userSection.setAlignItems(FlexComponent.Alignment.CENTER);
        userSection.setSpacing(true);

        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");
        toggle.setTooltipText("Toggle menu");

        HorizontalLayout header = new HorizontalLayout(toggle, appName);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(appName);
        header.setWidthFull();
        header.getStyle()
                .set("padding", "var(--lumo-space-s) var(--lumo-space-m)");

        header.add(userSection);

        addToNavbar(header);
    }

    private void createDrawer() {
        SideNav nav = new SideNav();

        SideNavItem homeItem = new SideNavItem("Home", HomeView.class, VaadinIcon.HOME.create());
        SideNavItem dashboardItem = new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create());
        SideNavItem usersItem = new SideNavItem("Users", UsersView.class, VaadinIcon.USERS.create());
        SideNavItem settingsItem = new SideNavItem("Settings", SettingsView.class, VaadinIcon.COG.create());
        SideNavItem reportsItem = new SideNavItem("Reports", ReportsView.class, VaadinIcon.CHART.create());
        SideNavItem messagesItem = new SideNavItem("Messages", MessagesView.class, VaadinIcon.ENVELOPE.create());

        nav.addItem(homeItem);
        nav.addItem(dashboardItem);
        nav.addItem(usersItem);
        nav.addItem(settingsItem);
        nav.addItem(reportsItem);
        nav.addItem(messagesItem);

        addToDrawer(nav);
    }
}
