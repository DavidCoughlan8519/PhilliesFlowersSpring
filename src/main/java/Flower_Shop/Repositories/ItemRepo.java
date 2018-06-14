package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Item;

public interface ItemRepo extends MongoRepository<Item, Integer>{}

