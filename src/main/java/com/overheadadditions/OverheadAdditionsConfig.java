package com.overheadadditions;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("example")
public interface OverheadAdditionsConfig extends Config
{
	@ConfigSection(
			name = "Clan",
			description = "Settings for the Clan chat",
			closedByDefault = true,
			position = 1
	)
	String clanSection = "Clan";

	@ConfigSection(
			name = "Friends Chat",
			description = "Settings for the Friends chat",
			closedByDefault = true,
			position = 2
	)
	String FC = "FC";

	@ConfigSection(
			name = "GIM",
			description = "Settings for the Group Ironman chat",
			closedByDefault = true,
			position = 2
	)
	String GIMSection = "GIM";

	@Range(min = 1, max = 10)
	@ConfigItem(
		keyName = "duration",
		name = "Message Duration",
		description = "Overhead message duration is length of message x duration"
	)
	default int duration()
	{
		return 3;
	}

	@ConfigItem(
			keyName = "clan",
			name = "Show Clan",
			section = clanSection,
			description = "Display Clan Chat above head."
	)default boolean showClan() {return false;}
	@ConfigItem(
			keyName = "clanChatColor",
			name = "Color",
			section = clanSection,
			description = "Configures the text color for clan chat messages.",
			position = 8
	)
	default Color clanChatColor() {
		return null;
	}
	@ConfigItem(
			keyName = "friends",
			name = "Show FC",
			section = FC,
			description = "Display Group Iron Chat above head."
	)default boolean showFC() {return false;}
	@ConfigItem(
			keyName = "friendsChatColor",
			name = "Color",
			section = FC,
			description = "Configures the text color for clan GIM chat messages.",
			position = 8
	)
	default Color FCChatColor() {
		return null;
	}
	@ConfigItem(
			keyName = "GIM",
			name = "Show GIM",
			section = GIMSection,
			description = "Display Group Iron Chat above head."
	)default boolean showGIM() {return false;}
	@ConfigItem(
			keyName = "clanGimChatColor",
			name = "Color",
			section = GIMSection,
			description = "Configures the text color for GIM chat messages.",
			position = 8
	)
	default Color GimChatColor() {
		return null;
	}
}
