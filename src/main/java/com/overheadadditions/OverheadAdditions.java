package com.overheadadditions;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "Overhead Expansion"
)
public class OverheadAdditions extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverheadAdditionsConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
	}
	private String getColorHex(Color color)
	{
		if (color == null)
		{
			return null;
		}

		return String.format("%02x%02x%02x",
				color.getRed(),
				color.getGreen(),
				color.getBlue());
	}
	@Subscribe
	public void onChatMessage(ChatMessage event) {
		String cleanedName = event.getName().replace('\u00A0', ' ');
		String cleanedMessage = cleanString(event.getMessage());

		switch (event.getType()) {
			case CLAN_GIM_CHAT:
				if (config.showGIM()) {
					String playerName = cleanedName.replaceAll("<img=\\d+>", "").trim();
					for (Player player : client.getTopLevelWorldView().players()) {
						if (player.getName() != null && player.getName().equalsIgnoreCase(playerName)) {
							if (config.GimChatColor() != null) {
								player.setOverheadText("<col=" + getColorHex(config.GimChatColor()) + ">" + cleanedMessage + "</col>");
							} else player.setOverheadText(cleanedMessage);
						}
						player.setOverheadCycle(Math.max(120, cleanedMessage.length() * config.duration()));

					}
				}
				break;
			case CLAN_CHAT:
			case CLAN_GUEST_CHAT:
				if (config.showClan()) {
					String playerName = cleanedName.replaceAll("<img=\\d+>", "").trim();
					for (Player player : client.getTopLevelWorldView().players()) {
						if (player.getName() != null && player.getName().equalsIgnoreCase(playerName)) {
							if (config.clanChatColor() != null) {
								player.setOverheadText("<col=" + getColorHex(config.clanChatColor()) + ">" + cleanedMessage + "</col>");
							} else player.setOverheadText(cleanedMessage);
						}
						player.setOverheadCycle(Math.max(120, cleanedMessage.length() * config.duration()));

					}
				}
				break;
			case FRIENDSCHAT:
				if (config.showFC()) {
					String playerName = cleanedName.replaceAll("<img=\\d+>", "").trim();
					for (Player player : client.getTopLevelWorldView().players()) {
						if (player.getName() != null && player.getName().equalsIgnoreCase(playerName)) {
							if (config.FCChatColor() != null) {
								player.setOverheadText("<col=" + getColorHex(config.FCChatColor()) + ">" + cleanedMessage + "</col>");
							} else player.setOverheadText(cleanedMessage);
						}
						player.setOverheadCycle(Math.max(120, cleanedMessage.length() * config.duration()));

					}
				}
				break;
		}
	}
	private String cleanString(String message)
	{
		return message.replaceAll("CA_ID:\\d+\\|", "").replace("<br>", "\n").replace("<lt>", "<").replace("<gt>", ">").replace('\u00A0', ' ').replace("<at>", "@");
	}
	@Provides
	OverheadAdditionsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OverheadAdditionsConfig.class);
	}
}
