package dev.xxj.vaadindemo.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.xxj.vaadindemo.entity.Book;
import dev.xxj.vaadindemo.service.BookService;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Books | Vaadin CRM")
public class ListView extends VerticalLayout {
    Grid<Book> grid = new Grid<>(Book.class);
    TextField filterText = new TextField();
    BookForm form;
    BookService service;

    public ListView(BookService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setVisible(false);
        form.setBook(null);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new BookForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveBook);
        form.addDeleteListener(this::deleteBook);
        form.addCloseListener(e -> closeEditor());
    }

    private void deleteBook(BookForm.DeleteEvent deleteEvent) {
        service.deleteBook(deleteEvent.getBook());
        updateList();
        closeEditor();
    }

    private void saveBook(BookForm.SaveEvent saveEvent) {
        service.saveBook(saveEvent.getBook());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("book-grid");
        grid.setSizeFull();
        grid.setColumns("title", "description", "amount");
        grid.setSortableColumns("title", "description", "amount");
        grid.addColumn(book -> book.getAuthor().getName()).setHeader("Author").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editBook(e.getValue()));
    }

    private void editBook(Book book) {
        if (book == null) {
            closeEditor();
        } else {
            form.setBook(book);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        Button addBookButton = new Button("Add book");
        addBookButton.addClickListener(e -> addBook());

        var toolbar = new HorizontalLayout(filterText, addBookButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addBook() {
        grid.asSingleSelect().clear();
        editBook(new Book());
    }

}