package org.example.minitasks.dto;
import jakarta.validation.constraints.NotBlank;
public record CreateTaskDTO(@NotBlank String title) {}
