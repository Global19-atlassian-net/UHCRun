package net.zyuiop.survivalgames.listeners;

import net.samagames.gameapi.events.FinishJoinPlayerEvent;
import net.samagames.gameapi.events.PreJoinPlayerEvent;
import net.samagames.gameapi.json.Status;
import net.zyuiop.survivalgames.SurvivalGames;
import net.zyuiop.survivalgames.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by vialarl on 16/01/2015.
 */
public class NetworkListener implements Listener {

    private Game game;

    public NetworkListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onPreJoin(PreJoinPlayerEvent event) {
        if (! SurvivalGames.ready)
            event.refuse(ChatColor.RED + "Map en génération.");
    }


    @EventHandler (ignoreCancelled = true)
    public void onFinishJoin(FinishJoinPlayerEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayer());
        if (player == null) {
            event.refuse(ChatColor.RED + "Une erreur s'est produite durant votre connexion au jeu.");
            return;
        }

        game.join(player);
    }

}
