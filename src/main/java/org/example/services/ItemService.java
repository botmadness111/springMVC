package org.example.services;

import org.example.models.Item;
import org.example.models.Person;
import org.example.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findByNameLike(String name){
        return itemRepository.findByNameLike(name);
    }

    public List<Item> findByOwner(Person owner){
        return itemRepository.findByOwner(owner);
    }
}
