package Flower_Shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Flower_Shop.entities.Staff;


public interface StaffRepo  extends MongoRepository<Staff, Integer>{}
