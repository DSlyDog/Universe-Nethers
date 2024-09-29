//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whispwriting.universe_nethers;

import java.util.HashMap;
import java.util.Map;
import net.whispwriting.universe_nethers.events.EntityNetherPortalHandler;
import net.whispwriting.universe_nethers.events.NetherPortalHandler;
import net.whispwriting.universes.Universes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class UniverseNethers extends JavaPlugin {
    public Universes universes = (Universes)Universes.getPlugin(Universes.class);
    public Map<String, Location> entryPortals = new HashMap();

    public UniverseNethers() {
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new NetherPortalHandler(), this.universes);
        Bukkit.getPluginManager().registerEvents(new EntityNetherPortalHandler(), this.universes);
    }

    public void onDisable() {
    }
}
