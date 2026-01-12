package com.jiedev.taskup.domain.dto;

import com.jiedev.taskup.domain.entity.TaskPriority;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateTaskRequestDTO(
        @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
        @Length(max = 255, message = ERROR_MESSAGE_TITLE_LENGTH)
        String title,

        @Length(max = 1000, message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        String description,

        @Nullable
        @FutureOrPresent(message = ERROR_MESSAGE_DUE_DATE_FUTURE)
        LocalDate dueDate,

        @NotNull(message = ERROR_MESSAGE_TASK_PRIORITY)
        TaskPriority priority
) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH =
            "Title must be between 1 and 255 characters only.";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH =
            "Description can only have up to 1000 characters.";
    private static final String ERROR_MESSAGE_DUE_DATE_FUTURE =
            "Due date must be future.";
    private static final String ERROR_MESSAGE_TASK_PRIORITY =
            "Task Priority must be provided";
}
