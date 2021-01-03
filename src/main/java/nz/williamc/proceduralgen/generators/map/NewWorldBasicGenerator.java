package nz.williamc.proceduralgen.generators.map;

import nz.williamc.proceduralgen.exception.ProcGenException;
import nz.williamc.proceduralgen.generators.chunk.BlankChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class NewWorldBasicGenerator implements MapGenerator {
    final String worldName;

    public NewWorldBasicGenerator(final String worldName) {
        this.worldName = worldName;
    }

    World createBlankWorld() throws ProcGenException
    {
        if(Bukkit.getWorld(worldName) != null)
            throw new ProcGenException("world already exists");
        final WorldCreator creator = new WorldCreator(worldName);
        creator.generator(new BlankChunkGenerator());
        return Bukkit.createWorld(creator);
    }

    @Override
    public void generate() throws ProcGenException {
        final World world = createBlankWorld();

    }
}
