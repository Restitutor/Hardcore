package me.darrionat.hardcore;

import org.bukkit.plugin.java.JavaPlugin;

import me.darrionat.hardcore.statics.Bootstrapper;
import me.darrionat.hardcore.utils.Utils;

public class Hardcore extends JavaPlugin {

	@Override
	public void onEnable() {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(this);
		bootstrapper.getNaturalRegenerationService().disableNaturalRegeneration();
	}

	public void log(String s) {
		this.getLogger().info(Utils.chat(s));
	}
}
