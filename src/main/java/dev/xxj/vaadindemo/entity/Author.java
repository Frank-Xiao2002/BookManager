package dev.xxj.vaadindemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    @Digits(integer = 3, fraction = 0)
    private Integer age;

    @Column(name = "email", length = 100)
    @Email
    private String email;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }
}