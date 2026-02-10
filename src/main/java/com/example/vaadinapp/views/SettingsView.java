package com.example.vaadinapp.views;

import com.example.vaadinapp.service.ThemeService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "settings", layout = MainLayout.class)
@PageTitle("Settings | Vaadin App")
@PermitAll
public class SettingsView extends VerticalLayout {

    private final ThemeService themeService;

    public SettingsView(ThemeService themeService) {
        this.themeService = themeService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Settings");
        title.getStyle().set("color", "var(--lumo-primary-color)");

        VerticalLayout themeSection = new VerticalLayout();
        themeSection.getStyle().set("background", "var(--lumo-base-color)")
                .set("padding", "var(--lumo-space-m)")
                .set("border-radius", "var(--lumo-border-radius)")
                .set("border", "1px solid var(--lumo-contrast-20pct)");

        H3 themeTitle = new H3("Theme Settings");
        themeTitle.getStyle().set("margin-top", "0");

        Paragraph themeDesc = new Paragraph("Choose your preferred color theme for the application.");

        RadioButtonGroup<String> themeGroup = new RadioButtonGroup<>();
        themeGroup.setLabel("Select Theme");
        themeGroup.setItems("Light", "Dark");
        themeGroup.setValue("Light");
        themeGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        themeGroup.setWidthFull();
        themeGroup.addValueChangeListener(e -> {
            String selectedTheme = e.getValue();
            UI ui = getCurrentUI();
            if (ui != null) {
                if ("Dark".equals(selectedTheme)) {
                    themeService.setTheme(ui, "dark");
                    applyDarkTheme();
                } else {
                    themeService.setTheme(ui, "light");
                    applyLightTheme();
                }
            }
        });

        themeSection.add(themeTitle, themeDesc, themeGroup);

        VerticalLayout preferencesSection = new VerticalLayout();
        preferencesSection.getStyle()
                .set("margin-top", "var(--lumo-space-l)")
                .set("background", "var(--lumo-base-color)")
                .set("padding", "var(--lumo-space-m)")
                .set("border-radius", "var(--lumo-border-radius)")
                .set("border", "1px solid var(--lumo-contrast-20pct)");

        H3 prefsTitle = new H3("Application Preferences");
        prefsTitle.getStyle().set("margin-top", "0");

        Paragraph prefsDesc = new Paragraph("Manage your application preferences and settings.");

        add(title, themeSection, preferencesSection);
    }

    private UI getCurrentUI() {
        return getUI().orElse(null);
    }

    private void applyDarkTheme() {
        UI ui = getCurrentUI();
        if (ui != null) {
            ui.getElement().setAttribute("theme", "dark");
        }
    }

    private void applyLightTheme() {
        UI ui = getCurrentUI();
        if (ui != null) {
            ui.getElement().setAttribute("theme", "light");
        }
    }
}