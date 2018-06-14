package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Statistic;

public interface StatisticRepo extends MongoRepository<Statistic, Integer>{}

