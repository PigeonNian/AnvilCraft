package dev.dubhe.anvilcraft.event.fabric;

import dev.dubhe.anvilcraft.api.chargecollector.ChargeCollectorManager;
import dev.dubhe.anvilcraft.api.power.IPowerComponent;
import dev.dubhe.anvilcraft.api.power.PowerGrid;
import dev.dubhe.anvilcraft.block.entity.ChargeCollectorBlockEntity;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ServerBlockEntityEventListener {
    public static void init() {
        ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register(ServerBlockEntityEventListener::onLoad);
        ServerBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register(ServerBlockEntityEventListener::onUnload);
    }

    private static void onLoad(BlockEntity entity, ServerLevel level) {
        if (entity instanceof IPowerComponent component) {
            PowerGrid.addComponent(component);
        }
        if (entity instanceof ChargeCollectorBlockEntity chargeCollector) {
            ChargeCollectorManager.addChargeCollector(chargeCollector);
        }
    }

    private static void onUnload(BlockEntity entity, ServerLevel level) {
        if (entity instanceof IPowerComponent component) {
            PowerGrid.removeComponent(component);
        }
        if (entity instanceof ChargeCollectorBlockEntity chargeCollector) {
            ChargeCollectorManager.removeChargeCollector(chargeCollector);
        }
    }
}
