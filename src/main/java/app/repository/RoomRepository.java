package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}