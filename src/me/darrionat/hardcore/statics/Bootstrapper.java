package me.darrionat.hardcore.statics;

import me.darrionat.hardcore.Hardcore;
import me.darrionat.hardcore.repositories.ConfigRepository;
import me.darrionat.hardcore.repositories.DeadPlayerRepository;
import me.darrionat.hardcore.repositories.FileRepository;
import me.darrionat.hardcore.repositories.PlayerStatsRepository;
import me.darrionat.hardcore.services.DeathWorldService;
import me.darrionat.hardcore.services.MessageService;
import me.darrionat.hardcore.services.NaturalRegenerationService;
import me.darrionat.hardcore.services.PlayerStatusService;
import me.darrionat.hardcore.services.RevivalService;
import me.darrionat.hardcore.services.StatsService;

public class Bootstrapper {

	private static Bootstrapper instance;

	// Repositories
	private DeadPlayerRepository deadPlayerRepository;
	private PlayerStatsRepository playerStatsRepository;
	private ConfigRepository configRepository;
	private FileRepository fileRepository;
	// Services
	private DeathWorldService deathWorldService;
	private PlayerStatusService playerStatusService;
	private NaturalRegenerationService naturalRegenerationService;
	private RevivalService revivalService;
	private StatsService statsService;
	private MessageService messageService;

	private Bootstrapper() {
	}

	public void initialize(Hardcore plugin) {
		// Files setup first
		fileRepository = new FileRepository(plugin);
		// Repositories
		configRepository = new ConfigRepository(plugin);
		deadPlayerRepository = new DeadPlayerRepository(fileRepository);
		playerStatsRepository = new PlayerStatsRepository(fileRepository);
		// Services
		deathWorldService = new DeathWorldService(configRepository);
		naturalRegenerationService = new NaturalRegenerationService(configRepository);
		playerStatusService = new PlayerStatusService(deadPlayerRepository);
		revivalService = new RevivalService(deadPlayerRepository, playerStatsRepository);
		statsService = new StatsService(playerStatsRepository);
		this.messageService = new MessageService(fileRepository);
	}

	public static Bootstrapper getBootstrapper() {
		if (instance == null)
			instance = new Bootstrapper();
		return instance;
	}

	public DeathWorldService getDeathService() {
		return deathWorldService;
	}

	public PlayerStatusService getPlayerStatusService() {
		return playerStatusService;
	}

	public NaturalRegenerationService getNaturalRegenerationService() {
		return naturalRegenerationService;
	}

	public RevivalService getRevivalService() {
		return revivalService;
	}

	public StatsService getStatsService() {
		return statsService;
	}

	public MessageService getMessageService() {
		return messageService;
	}
}