package nz.williamc.proceduralgen.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class WorldSpawnCommand extends CommandExecutorBase {
    public WorldSpawnCommand(JavaPlugin plugin) {
        super(plugin, "wspawn");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!isPlayer(sender) || args.length != 1)
            return false;

        final Player player = (Player)sender;
        final World world = Bukkit.getWorld(args[0]);
        if(world == null)
            return false;

        return player.teleport(world.getSpawnLocation());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!isPlayer(sender) || args.length != 1)
            return null;
        return getWorldNamesFiltered(args[0]);
    }
}
