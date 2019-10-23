package app.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.Message;
import app.model.Room;
import app.repository.UserRepository;
import app.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomSevice;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@GetMapping("/rooms")
	public String getRooms(Model model, Principal principal) {

		model.addAttribute("rooms", userRepository.findByName(principal.getName()).getRooms());

		return "rooms";
	}

	@PostMapping("/rooms")
	public String addRoom(Room room, @RequestParam("users.name") String users, Principal principal) {

		roomSevice.addRoom(room, userRepository.findByName(principal.getName()), userRepository.findByName(users));

		return "redirect:/rooms/" + room.getId();
	}

	@GetMapping("/rooms/{roomId}")
	public String getRoomById(@PathVariable long roomId, Model model) {

		Room room = roomSevice.loadRoomById(roomId);

		model.addAttribute("room", room);

		return "room";
	}

	@MessageMapping("/chats/{chat}")
	public void send(@DestinationVariable long chat, @Payload Message msg, Principal principal) throws Exception {

		msg.setSendTime(new Date());

		simpMessagingTemplate.convertAndSend("/topic/chats/" + chat, msg);

		msg.setSender(userRepository.findByName(principal.getName()));
		roomSevice.addMessageByRoomId(chat, msg);
	}

	@MessageMapping("/chats/stream")
	public void send(@Payload String img) throws Exception {

		simpMessagingTemplate.convertAndSend("/topic/chats/stream", img);
	}
}