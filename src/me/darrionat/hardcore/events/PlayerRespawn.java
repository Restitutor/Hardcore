package me.darrionat.hardcore.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.utils.Utils;

public class PlayerRespawn implements Listener {
	private final int hours;
	private Hardcore plugin;

	public PlayerRespawn(Hardcore plugin, int hours) {
		this.hours = hours;
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void playerRespawnEvent(PlayerRespawnEvent e) {
		// Respawn after half a second to let player reach spawn
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			e.getPlayer().kickPlayer(
				Utils.chat(String.format("&cYou died! You can join again after %s hours!", hours)));
		}, 10);
	}
}
