package com.willfp.libreforge

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object EffectCollisionFixer : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun clearOnQuit(event: PlayerQuitEvent) {
        val player = event.player
        for ((holder, effects) in player.providedActiveEffects) {
            for (effect in effects) {
                effect.disable(player, holder)
            }
        }

        player.updateHolders()
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun scanOnJoin(event: PlayerJoinEvent) {
        val player = event.player

        player.updateHolders()

        plugin.scheduler.run {
            player.updateEffects()
        }
    }
}
