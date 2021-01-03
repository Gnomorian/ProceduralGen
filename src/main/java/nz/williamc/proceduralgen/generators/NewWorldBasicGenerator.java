package nz.williamc.proceduralgen.generators;

import nz.williamc.proceduralgen.BlankChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class NewWorldBasicGenerator implements MapGenerator {
    final String worldName;

    public NewWorldBasicGenerator(final String worldName) {
        this.worldName = worldName;
    }

    World createBlankWorld() throws Exception
    {
        if(Bukkit.getWorld(worldName) != null)
            throw new Exception("world already exists");
        final WorldCreator creator = new WorldCreator(worldName);
        creator.generator(new BlankChunkGenerator());
        return Bukkit.createWorld(creator);
    }

    @Override
    public void generate() throws Exception {
        final World world = createBlankWorld();

    }
}
