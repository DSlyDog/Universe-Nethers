//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whispwriting.universe_nethers.tasks;

import java.util.logging.Level;
import net.whispwriting.universe_nethers.UniverseNethers;
import net.whispwriting.universe_nethers.events.NetherPortalHandler;
import net.whispwriting.universes.Universes;
import org.bukkit.Bukkit;

public class EnableTask implements Runnable {
    private Universes universes = (Universes)Universes.getPlugin(Universes.class);
    private UniverseNethers plugin = (UniverseNethers)UniverseNethers.getPlugin(UniverseNethers.class);

    public EnableTask() {
    }

    public void run() {
        if (Bukkit.getPluginManager().isPluginEnabled(Universes.getPlugin(Universes.class))) {
            Bukkit.getLogger().log(Level.WARNING, "[U-Nethers] Universes does not appear to be running. This plugin can only run if Universes is running.");
        } else {
            this.universes.netherPerOverworld = this.universes.config.get().getBoolean("Universe-Nethers.nether-per-overworld");
            Bukkit.getPluginManager().registerEvents(new NetherPortalHandler(), UniverseNethers.getPlugin(UniverseNethers.class));
        }

    }
}
