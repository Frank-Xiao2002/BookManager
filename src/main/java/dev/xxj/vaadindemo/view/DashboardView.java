package dev.xxj.vaadindemo.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import dev.xxj.vaadindemo.service.BookService;
import jakarta.annotation.security.PermitAll;

import java.util.Random;

@PermitAll
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout {
    private final BookService bookService;

    public DashboardView(BookService bookService) {
        this.bookService = bookService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getBookStats(), getAuthorCharts());
    }

    private Chart getAuthorCharts() {
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Random random = new Random();
        bookService.findAll(null).forEach(
                book -> dataSeries.add(new DataSeriesItem(book.getTitle(), book.getAmount()))
        );
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Component getBookStats() {
        Span stats = new Span(bookService.countBooks() + " books");
        stats.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.Margin.Top.MEDIUM
        );
        return stats;
    }


}
