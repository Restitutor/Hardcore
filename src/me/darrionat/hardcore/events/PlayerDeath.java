package me.darrionat.hardcore.events;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Date;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.PlayerStatusService;

public class PlayerDeath implements Listener {

	private Hardcore plugin;
	private MessageService messageService;
	private PlayerStatusService playerStatusService;
	private DeathWorldService deathWorldService;
	private final int hours;

	public PlayerDeath(Hardcore plugin, MessageService messageService, PlayerStatusService playerStatusService,
			DeathWorldService deathWorldService, int hours) {
		this.plugin = plugin;
		this.messageService = messageService;
		this.playerStatusService = playerStatusService;
		this.deathWorldService = deathWorldService;
		this.hours = hours;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Date expires = new Date(System.currentTimeMillis() + 1000 * 3600 * hours);
		String name = e.getEntity().getName();
		Bukkit.getBanList(BanList.Type.NAME).addBan(name, "Died in Hardcore", expires, null);
		plugin.log(String.format("Banned %s on death.", name));
	}
}
