//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whispwriting.universe_nethers.events;

import net.whispwriting.universe_nethers.UniverseNethers;
import net.whispwriting.universes.Universes;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class EntityNetherPortalHandler implements Listener {
    private UniverseNethers plugin = (UniverseNethers)UniverseNethers.getPlugin(UniverseNethers.class);

    public EntityNetherPortalHandler() {
    }

    @EventHandler
    public void onPortalUser(EntityPortalEvent event) {
        if (Universes.plugin.netherPerOverworld && event.getEntity().getLocation().getBlock().getType().name().equals("NETHER_PORTAL") && !(event.getEntity() instanceof Player) && Universes.plugin.netherPerOverworld) {
            String worldName = event.getFrom().getWorld().getName();
            String overWorldName;
            World world;
            if (worldName.contains("_nether")) {
                overWorldName = worldName.substring(0, worldName.lastIndexOf("_"));
                world = Bukkit.getWorld(overWorldName);
                event.getTo().setWorld(world);
            } else {
                overWorldName = event.getFrom().getWorld().getName() + "_nether";
                world = Bukkit.getWorld(overWorldName);
                if (world == null) {
                    event.setCancelled(true);
                } else {
                    event.getTo().setWorld(world);
                }
            }
        }

    }
}
