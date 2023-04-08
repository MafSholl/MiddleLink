package com.fs.middlelink.drone.repository;

import com.fs.middlelink.drone.models.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
}
