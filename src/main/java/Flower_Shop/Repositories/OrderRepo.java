package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Order;

public interface OrderRepo extends MongoRepository<Order, Integer>{}