package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@EnableScheduling
@RequestMapping("/")
@CrossOrigin
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@GetMapping("/connect/{ramal}")
	public ResponseEntity<?> pegarRamal(@PathVariable Integer ramal) {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		simpMessagingTemplate.convertAndSend("/topic/messages/" + ramal, new Message("Servidor", "Último protocolo encontrado para o ramal " + ramal, time));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
