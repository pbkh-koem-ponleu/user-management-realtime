package com.example.demo.socketio;

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
	}
	
	@OnEvent("requestJoin")
	public void onRequest(SocketIOClient _client, String _name, String _password, AckRequest _ack) {
		System.out.println("Connected client: " + _client.getSessionId());
		System.out.println("user name : " + _name);
		this.socketIOServer.getBroadcastOperations().sendEvent("requestEvent", userService.login(_name, _password));
		_ack.sendAckData("join!");
	}
	
	@OnEvent("createUserButton")
	public void creatUser(SocketIOClient _client, User _user, AckRequest _ack) {
		System.out.println("create user......");
		User user = userService.create(_user);
		this.socketIOServer.getBroadcastOperations().sendEvent("createUserEvent", user);
		_ack.sendAckData("Created!");
	}
	
//	@OnEvent("createButton")
//	public void createButton(SocketIOClient client, User _user, AckRequest ack){
//		System.out.println(_user);
//		this.socketIOServer.getBroadcastOperations().sendEvent("createEvent", _user);
//	}
	
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
	
	@OnEvent("sendMessageButton")
	public void sendMessageButton(SocketIOClient client, String _message, String _session, AckRequest _ack) {
		this.socketIOServer.getBroadcastOperations().sendEvent("messageEvent", _message);
		_ack.sendAckData("sent!");
	}
}
