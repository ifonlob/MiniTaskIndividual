package org.example.minitasks.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Builder.Default
    @Column
    private boolean completed = false;

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }
}