package nz.williamc.proceduralgen.command;

import nz.williamc.proceduralgen.generators.NewWorldBasicGenerator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class NewWorldCommand extends CommandExecutorBase {
    public NewWorldCommand(JavaPlugin plugin) {
        super(plugin, "newworld");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1)
            return false;
        final String worldName = args[0];
        try {
            new NewWorldBasicGenerator(worldName).generate();
            sender.sendMessage("Created Empty world " + worldName);
        } catch (Exception e) {
            Bukkit.getLogger().severe(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
}
