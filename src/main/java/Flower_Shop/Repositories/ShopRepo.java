package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Shop;

public interface ShopRepo extends MongoRepository<Shop, Integer>{}
