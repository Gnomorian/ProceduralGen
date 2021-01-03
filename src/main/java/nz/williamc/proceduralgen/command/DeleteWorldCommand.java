package nz.williamc.proceduralgen.command;

import com.ibm.jvm.dtfjview.tools.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class DeleteWorldCommand extends CommandExecutorBase {
    public DeleteWorldCommand(JavaPlugin plugin) {
        super(plugin, "delworld");
    }

    void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    void removePlayersFromWorld(final World world) {
        for(final Player player : world.getPlayers()) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return false;

        final World world = Bukkit.getWorld(args[0]);
        if(world == null)
            return false;

        removePlayersFromWorld(world);
        Bukkit.unloadWorld(world, true);
        Bukkit.getWorlds().remove(world);
        deleteDir(world.getWorldFolder());

        sender.sendMessage("Deleted the world " + world.getName());

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length != 1)
            return null;
        return getWorldNamesFiltered(args[0]);
    }
}
