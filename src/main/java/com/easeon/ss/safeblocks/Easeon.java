package com.easeon.ss.safeblocks;

import com.easeon.ss.core.api.common.base.BaseToggleModule;
import com.easeon.ss.core.api.definitions.enums.EventPhase;
import com.easeon.ss.core.api.events.EaseonEntitySpawn;
import com.easeon.ss.core.api.events.EaseonEntitySpawn.EntitySpawnTask;
import net.fabricmc.api.ModInitializer;

public class Easeon extends BaseToggleModule implements ModInitializer {
    public static Easeon instance;

    public Easeon() {
        instance = this;
    }

    @Override
    public void onInitialize() {
        logger.info("Initialized!");
    }
}