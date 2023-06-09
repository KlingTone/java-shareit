package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.comments.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService service;

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("id владельца для запроса getAllItemsByOwner: {}", userId);
        List<ItemDto> allItems = service.getAllItemsByOwner(userId);
        log.info("Результат запроса getAllItemsByOwner: {}", allItems);
        return allItems;
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@RequestHeader("X-Sharer-User-Id") long userId,
                           @PathVariable long id) {
        log.info("id для запроса getItem: {}", id);
        ItemDto item = service.getItem(userId, id);
        log.info("Результат запроса getItem: {}", item);
        return item;
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String text) {
        log.info("text для запроса searchItem: {}", text);
        List<ItemDto> result = service.searchItem(text);
        log.info("Результат запроса searchItem: {}", result);
        return result;
    }

    @PostMapping()
    public ItemDto addItem(@RequestBody ItemDto item,
                           @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("item и id владельца для запроса addItem: {}, {}", item, userId);
        ItemDto result = service.addItem(item, userId);
        log.info("Результат запроса addItem: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable long id, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Удаление предмета с id {} и id владельца {} начато", id, userId);
        service.deleteItem(id, userId);
        log.info("Удаление предмета с id {} и id владельца {} завершено", id, userId);
    }

    @PatchMapping("/{id}")
    public ItemDto patchItem(@PathVariable long id,
                             @RequestBody ItemDto patch,
                             @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("patchItem: id {}, item {}, userId {} патч начат", id, patch, userId);
        ItemDto itemDto = service.patchItem(id, patch, userId);
        log.info("Патч завершен. Результат: {}", itemDto);
        return itemDto;
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@PathVariable long itemId,
                                 @RequestHeader("X-Sharer-User-Id") long userId,
                                 @RequestBody CommentDto commentDto) {
        log.info("addComment: id {}, commentDto {}, userId {} патч начат", itemId, commentDto, userId);
        CommentDto result = service.addComment(itemId, userId, commentDto);
        log.info("Добавление комметария завершено. Результат: {}", result);
        return result;
    }
}
