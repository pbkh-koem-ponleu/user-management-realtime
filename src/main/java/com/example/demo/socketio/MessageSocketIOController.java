package com.example.demo.socketio;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.example.demo.service.UserService;

@Component
public class MessageSocketIOController {
	private SocketIOServer socketIOServer;
	
	@Autowired
	UserService userService;
	
	@Autowired
	public MessageSocketIOController(SocketIOServer _socketIOServer) {
		this.socketIOServer = _socketIOServer;
	}
	
	@OnEvent("sendMessageButton")
	public void sendMessageButton(SocketIOClient client, UUID _session, String _message, AckRequest _ack) {
		System.out.println("session " + _session);
		this.socketIOServer.getClient((UUID)_session).sendEvent("messageEvent", _message);;
//		this.socketIOServer.getBroadcastOperations().sendEvent("messageEvent", _session);
		_ack.sendAckData("sent!");
	}
}
