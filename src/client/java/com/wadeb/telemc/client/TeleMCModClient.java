package com.wadeb.telemc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;

public class TeleMCModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("telemc-client");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello TeleMC Client!");
	}
}
