package com.wadeb.telemc.client;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;

import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

// Tyrus docs:
// https://eclipse-ee4j.github.io/tyrus-project.github.io/documentation/latest20x/index/

@ServerEndpoint("/websocket")
class TeleMCSocketServer extends Endpoint {
    
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.addMessageHandler(String.class, message -> {
            try {
                session.getBasicRemote().sendText("Echo: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

public class TeleMCModClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("telemc-client");
	private static final int WEBSOCKET_PORT = 8025;

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello TeleMC Client!");

		Server server = new Server("localhost", WEBSOCKET_PORT, "/websockets", null, TeleMCSocketServer.class);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("WebSocket server listening on port " + WEBSOCKET_PORT + ".");
	}
}
