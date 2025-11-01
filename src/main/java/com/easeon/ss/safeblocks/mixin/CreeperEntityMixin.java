package com.easeon.ss.safeblocks.mixin;

import com.easeon.ss.safeblocks.Easeon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.block.BlockState;
import net.minecraft.world.World.ExplosionSourceType;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

    @Redirect(
        method = "explode",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)V"
        )
    )
    private void redirectExplosion(ServerWorld serverWorld, Entity entity, double x, double y, double z, float power, ExplosionSourceType explosionSourceType) {
        var behavior = new ExplosionBehavior() {
            @Override
            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                return !Easeon.instance.config.enabled;
            }
        };

        serverWorld.createExplosion(entity, null, behavior, x, y, z, power, false, explosionSourceType);
    }
}