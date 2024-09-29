//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whispwriting.universe_nethers.events;

import net.whispwriting.universe_nethers.UniverseNethers;
import net.whispwriting.universes.Universes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class NetherPortalHandler implements Listener {
    private UniverseNethers plugin = UniverseNethers.getPlugin(UniverseNethers.class);

    public NetherPortalHandler() {
    }

    @EventHandler
    public void onPortalUser(PlayerPortalEvent event) {
        if (event.getCause() == TeleportCause.NETHER_PORTAL && event.getCause() != TeleportCause.END_PORTAL && Universes.plugin.netherPerOverworld) {
            String worldName = event.getFrom().getWorld().getName();
            String overWorldName;
            World overWorld;
            if (worldName.contains("_nether")) {
                overWorldName = worldName.substring(0, worldName.lastIndexOf("_"));
                overWorld = Bukkit.getWorld(overWorldName);
                if (Universes.plugin.worldEntryPermissions && !event.getPlayer().hasPermission("Universes.universe." + overWorldName)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.DARK_RED + "You do not have permission to enter that world");
                    return;
                }

                if (Universes.plugin.toEntryPortal && this.plugin.entryPortals.containsKey(event.getPlayer().getName())) {
                    event.setTo((Location)this.plugin.entryPortals.get(event.getPlayer().getName()));
                }

                event.getTo().setWorld(overWorld);
            } else {
                overWorldName = event.getFrom().getWorld().getName() + "_nether";
                overWorld = Bukkit.getWorld(overWorldName);
                if (Universes.plugin.worldEntryPermissions && !event.getPlayer().hasPermission("Universes.universe." + overWorldName)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.DARK_RED + "You do not have permission to enter that world");
                    return;
                }

                if (overWorld == null) {
                    event.getPlayer().sendMessage(ChatColor.RED + "That portal has no destination");
                    event.setCancelled(true);
                } else {
                    event.getTo().setWorld(overWorld);
                    if (Universes.plugin.toEntryPortal) {
                        this.plugin.entryPortals.put(event.getPlayer().getName(), event.getFrom());
                    }
                }
            }
        }

    }

    public Location getReturnPoint(Location from, String type) {
        Location to = new Location(from.getWorld(), from.getX(), from.getY(), from.getZ(), from.getYaw(), from.getPitch());
        if (type.equals("nether")) {
            to.setX(to.getX() * 8.0);
            to.setZ(to.getZ() * 8.0);
        } else {
            to.setX(to.getX() / 8.0);
            to.setZ(to.getZ() / 8.0);
        }

        return to;
    }
}
