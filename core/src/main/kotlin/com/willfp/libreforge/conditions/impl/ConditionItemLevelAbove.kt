package com.willfp.libreforge.conditions.impl

import com.gamingmesh.jobs.commands.list.level
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.placeholder.context.placeholderContext
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.ProvidedHolder
import com.willfp.libreforge.arguments
import com.willfp.libreforge.conditions.Condition
import com.willfp.libreforge.getProvider
import com.willfp.libreforge.levels.LevelTypes
import com.willfp.libreforge.levels.levels
import com.willfp.libreforge.triggers.Dispatcher
import com.willfp.libreforge.triggers.get
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object ConditionItemLevelAbove : Condition<NoCompileData>("item_level_above") {
    override val arguments = arguments {
        require("id", "You must specify the level ID to check for!")
        require("level", "You must specify the minimum level!")
    }

    override fun isMet(
        dispatcher: Dispatcher<*>,
        config: Config,
        holder: ProvidedHolder,
        compileData: NoCompileData
    ): Boolean {
        val item = holder.getProvider<ItemStack>() ?: return false
        val type = LevelTypes[config.getString("id")]
        val level = config.getIntFromExpression("level", dispatcher.get<Player>())

        return item.levels[type].level > level
    }
}
