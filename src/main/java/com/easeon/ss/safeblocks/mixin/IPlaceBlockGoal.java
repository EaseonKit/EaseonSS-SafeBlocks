package com.easeon.ss.safeblocks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal", remap = false)
public interface IPlaceBlockGoal {}