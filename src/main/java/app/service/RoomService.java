package app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Message;
import app.model.Room;
import app.model.User;
import app.repository.MessageRepository;
import app.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private MessageRepository msgRepository;

	public Room loadRoomById(long id) {
		return roomRepository.findById(id).get();
	}

	public boolean addRoom(Room room, User... user) {

		List<User> users = new ArrayList<>();
		users = Arrays.asList(user);

		room.setUsers(users);
		room.setMessages(new HashSet<Message>());

		roomRepository.save(room);

		return true;
	}

	public boolean addUserToRoom(User user, Room room) {
		room.getUsers().add(user);

		roomRepository.save(room);

		return true;
	}

	public boolean addMessageByRoomId(long roomId, Message msg) {

		Room room = roomRepository.findById(roomId).get();

		msg.setRoom(room);
		
		room.getMessages().add(msg);
		roomRepository.save(room);

		return true;
	}
}