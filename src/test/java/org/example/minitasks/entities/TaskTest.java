package org.example.minitasks.entities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TaskTest {

    @Test
    void newTaskShouldNotBeCompleted() {
        Task tarea = new Task("Compran pan");

        assertFalse(tarea.isCompleted());

    }
}
