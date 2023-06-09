package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.enums.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingOutputDto {

    private Long id;

    private User booker;

    private Item item;

    private BookingStatus status;

    private LocalDateTime start;

    private LocalDateTime end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingOutputDto)) return false;
        return id != null && id.equals(((BookingOutputDto) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

