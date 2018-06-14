package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Basket;

public interface BasketRepo extends MongoRepository<Basket, Integer>{}
