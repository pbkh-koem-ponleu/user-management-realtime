package com.example.demo.socketio;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Component
public class UserSocketIOController {
	private SocketIOServer socketIOServer;
	
	@Autowired
	UserService userService;
	
	//	private HashMap<String, String> userSession = new HashMap<String, String>();
	
	@Autowired
	public UserSocketIOController(SocketIOServer _socketIOServer) {
		this.socketIOServer = _socketIOServer;
	}
	
	@OnConnect
	public void onConnect(SocketIOClient _client) {
		System.out.println("Connected client: " + _client.getSessionId());
		this.socketIOServer.getBroadcastOperations().sendEvent("connectedEvent", _client.getSessionId());
	}
	
	
	
	@OnDisconnect
	public void onDisconnect(SocketIOClient _client) {
		System.out.println("Disconnected client: " + _client.getSessionId());
		System.out.println("all client: " + this.socketIOServer.getAllClients());
		this.socketIOServer.getBroadcastOperations().sendEvent("disconnect", _client.getSessionId());
	}
	
	@OnEvent("requestJoin")
	public void onRequest(SocketIOClient _client, String _name, String _password, AckRequest _ack) {
		User user = new User();
		user = userService.login(_name, _password);
		this.socketIOServer.getBroadcastOperations().sendEvent("requestEvent", user);
		_ack.sendAckData("join!");
	}
	
	@OnEvent("createUserButton")
	public void creatUser(SocketIOClient _client, User _user, AckRequest _ack) {
		System.out.println("create user......");
		User user = userService.create(_user);
		this.socketIOServer.getBroadcastOperations().sendEvent("createUserEvent", user);
		_ack.sendAckData("Created!");
	}
	
	@OnEvent("removeAllButton")
	public void removeAllButton(SocketIOClient client, String _message, AckRequest ack) {
		this.socketIOServer.getBroadcastOperations().sendEvent("removeAllEvent", userService.deleteUsers());
	}
	
	@OnEvent("removeIdButton")
	public void removeIdButton(SocketIOClient client, int _id, AckRequest _ack) {
		this.socketIOServer.getBroadcastOperations().sendEvent("removeIdEvent", userService.delete(_id));
		_ack.sendAckData("Remove!");
	}
	
	@OnEvent("updateButton")
	public void updateButton(SocketIOClient client, Long _id, User _user, AckRequest _ack) {
		this.socketIOServer.getBroadcastOperations().sendEvent("updateEvent", userService.update(_id, _user));
		_ack.sendAckData("update!");
	}
	
	
}