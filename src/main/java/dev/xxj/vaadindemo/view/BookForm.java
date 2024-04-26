package dev.xxj.vaadindemo.view;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;
import dev.xxj.vaadindemo.entity.Book;
import lombok.Getter;

public class BookForm extends FormLayout {
    TextField title = new TextField("Title");
    TextField author = new TextField("Author");
    TextField description = new TextField("Description");
    IntegerField amount = new IntegerField("Amount");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    BeanValidationBinder<Book> binder = new BeanValidationBinder<>(Book.class);

    public BookForm() {
        addClassName("book-form");

        amount.setMin(0);
        amount.setStepButtonsVisible(true);

        binder.forField(author).bind(
                book -> book.getAuthor().getName(),
                (book, author) -> book.getAuthor().setName(author)
        );
        binder.bindInstanceFields(this);

        add(
                title,
                description,
                author,
                amount,
                createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void setBook(Book book) {
        binder.setBean(book);
    }

    @Getter
    public static abstract class BookFormEvent extends ComponentEvent<BookForm> {
        private final Book book;

        protected BookFormEvent(BookForm source, Book book) {
            super(source, false);
            this.book = book;
        }
    }

    // Events
    public static class SaveEvent extends BookFormEvent {
        SaveEvent(BookForm source, Book book) {
            super(source, book);
        }
    }

    public static class DeleteEvent extends BookFormEvent {
        DeleteEvent(BookForm source, Book book) {
            super(source, book);
        }

    }

    public static class CloseEvent extends BookFormEvent {
        CloseEvent(BookForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}

