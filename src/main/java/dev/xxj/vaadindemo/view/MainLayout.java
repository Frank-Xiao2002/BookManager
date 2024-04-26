package dev.xxj.vaadindemo.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import dev.xxj.vaadindemo.security.SecurityService;

public class MainLayout extends AppLayout {
    private final SecurityService service;

    public MainLayout(SecurityService service) {
        this.service = service;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logoH1 = new H1("Vaadin CRM");
        logoH1.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        String u = service.getAuthenticatedUser().getUsername();
        Button logout = new Button("Logout " + u, e -> service.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logoH1, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logoH1);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Books!!", ListView.class),
                new RouterLink("Dashboard", DashboardView.class)
        ));
    }
}
