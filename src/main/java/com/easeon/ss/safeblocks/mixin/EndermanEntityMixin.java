package com.easeon.ss.safeblocks.mixin;
import com.easeon.ss.safeblocks.Easeon;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin extends MobEntity {
    protected EndermanEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void removePickupGoal(CallbackInfo ci) {
        if (Easeon.instance.config.enabled) {
            this.goalSelector.getGoals().removeIf(goal ->
                goal.getGoal() instanceof IPickUpBlockGoal ||
                goal.getGoal() instanceof IPlaceBlockGoal
            );
        }
    }
}

//@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
//public abstract class EndermanEntityMixin extends Goal {
//
//    @Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
//    private void disableEndermanPickup(CallbackInfoReturnable<Boolean> cir) {
//        cir.setReturnValue(false);
//    }
//}