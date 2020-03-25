package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.model.Protocolo;
import com.example.messagingstompwebsocket.service.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@EnableScheduling
public class ProtocoloController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private SimpUserRegistry simpUserRegistry;

	@Autowired
	private ProtocoloService protocoloService;

	@Scheduled(fixedRate = 5000)
	public void enviarUltimosProtocolos() {
		simpUserRegistry.getUsers().forEach((user) -> {
					user.getSessions().forEach((session) -> {
						session.getSubscriptions().forEach((subscription) -> {
							List<Protocolo> protocoloList = protocoloService.findTop10ByDestinoOrderByDataHoraDesc(subscription.getId());
							String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
							simpMessagingTemplate.convertAndSend(subscription.getDestination(), protocoloList);
						});
					});
				}
		);
	}


}
