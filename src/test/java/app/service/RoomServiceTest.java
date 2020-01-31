package app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import app.model.Room;
import app.model.User;
import app.repository.MessageRepository;
import app.repository.RoomRepository;

@SpringBootTest
class RoomServiceTest {

	@Autowired
	RoomService roomService;

	@MockBean
	private RoomRepository roomRepository;

	@MockBean
	private MessageRepository msgRepository;

	@Test
	public void addRoomTest() {
		Room room = new Room();
		User user = new User();

		assertTrue(roomService.addRoom(room, user));
		assertNotNull(room.getUsers());
		assertNotNull(room.getMessages());

		verify(roomRepository, times(1)).save(room);

	}
}
