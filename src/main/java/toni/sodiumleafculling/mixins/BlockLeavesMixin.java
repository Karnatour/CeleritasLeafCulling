package toni.sodiumleafculling.mixins;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import toni.sodiumleafculling.LeafCulling;
import toni.sodiumleafculling.LeafCullingMode;
import toni.sodiumleafculling.config.LeafCullingConfig;


@Mixin(value = BlockLeaves.class, priority = 100)
public class BlockLeavesMixin {

    @Inject(
            method = "shouldSideBeRendered",
            at = @At("HEAD"),
            cancellable = true
    )
    private void inject$shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos selfPos, EnumFacing facing, CallbackInfoReturnable<Boolean> cir) {
         if (blockState.getBlock() instanceof BlockLeaves) {
             if (LeafCullingConfig.cullingMode == LeafCullingMode.HOLLOW) {
                 if (LeafCulling.shouldCullSide(blockAccess, selfPos, facing, 2)) {
                     cir.setReturnValue(false);
                     return;
                 }
             }

             if (LeafCullingConfig.cullingMode == LeafCullingMode.SOLID || LeafCullingConfig.cullingMode == LeafCullingMode.SOLID_AGGRESSIVE) {
                 boolean cullSelf = LeafCulling.surroundedByLeaves(blockAccess, selfPos);

                 BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
                 neighborPos.setPos(selfPos).move(facing);
                 boolean cullOther = LeafCulling.surroundedByLeaves(blockAccess, neighborPos);
                 if (cullSelf) {
                     if (cullOther) {
                         cir.setReturnValue(false);
                         return;
                     }
                 } else {
                     if (cullOther) {
                         cir.setReturnValue(false);
                     }
                 }
             }
         }
    }
}
