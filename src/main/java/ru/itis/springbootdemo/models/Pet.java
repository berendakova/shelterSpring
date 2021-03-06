package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootdemo.utils.FieldMatch;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String age;
    private String img;
    private String description;
    private String sex;
    private String breed;
    private String disease;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itis_user")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private StatusPet status;



}
