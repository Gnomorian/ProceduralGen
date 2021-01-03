package nz.williamc.proceduralgen.command;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutorBase implements CommandExecutor, TabCompleter {
    final String commandName;
    final JavaPlugin myPlugin;
    protected CommandExecutorBase(final JavaPlugin plugin, final String commandName) {
        this.commandName = commandName;
        this.myPlugin = plugin;
        register();
    }
    private void register() {
        Bukkit.getLogger().info("Registering " + commandName + " handler.");
        final PluginCommand command = myPlugin.getCommand(commandName);
        command.setExecutor(this);
        command.setTabCompleter(this);
    }

    protected boolean isPlayer(final CommandSender sender)
    {
        return sender instanceof Player;
    }

    protected List<String> getWorldNamesFiltered(final String pattern)
    {
        final ArrayList<String> worldNames = new ArrayList<>();
        Bukkit.getWorlds().stream().forEach(w -> {
            final String worldName = w.getName();
            if(worldName.contains(pattern))
                worldNames.add(worldName);
        });
        return worldNames;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        assert false : "command executors must implement this function";
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
