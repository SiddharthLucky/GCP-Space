package org.springpoc.cloudruncrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springpoc.cloudruncrud.model.Item;
import org.springpoc.cloudruncrud.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {
//    private final List<Item> items = new ArrayList<>();
//    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ItemRepository itemRepository;

    // Create
    public Item createItem(Item item) {
//        item.setId(counter.incrementAndGet());
//        items.add(item);
        itemRepository.save(item);
        return item;
    }

    // Read All
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Read by ID
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findAll().stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    // Update
    public Optional<Item> updateItem(Long id, Item itemDetails) {
        return getItemById(id).map(item -> {
            item.setName(itemDetails.getName());
            item.setDescription(itemDetails.getDescription());
            itemRepository.save(item);
            return item;
        });
    }

    // Delete
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
