package nz.williamc.proceduralgen.command;

import nz.williamc.proceduralgen.generators.map.NewWorldBasicGenerator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StartDungeonCommand extends CommandExecutorBase {
    public StartDungeonCommand(JavaPlugin plugin) {
        super(plugin, "startDungeon");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            final String worldName = args[0];
            try {
                new NewWorldBasicGenerator(worldName).generate();
                final Player player = (Player)sender;
                player.setGameMode(GameMode.CREATIVE);
                player.teleport(new Location(Bukkit.getWorld(worldName), 0, 0, 0));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
