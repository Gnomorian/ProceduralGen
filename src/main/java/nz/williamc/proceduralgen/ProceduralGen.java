package nz.williamc.proceduralgen;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import nz.williamc.proceduralgen.command.DeleteWorldCommand;
import nz.williamc.proceduralgen.command.NewWorldCommand;
import nz.williamc.proceduralgen.command.StartDungeonCommand;
import nz.williamc.proceduralgen.command.WorldSpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.print.Paper;

public final class ProceduralGen extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("ProceduralGen enable.");
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    void registerCommands() {
        Bukkit.getLogger().info("registering commands.");
        new StartDungeonCommand(this);
        new DeleteWorldCommand(this);
        new NewWorldCommand(this);
        new WorldSpawnCommand(this);
    }
}
