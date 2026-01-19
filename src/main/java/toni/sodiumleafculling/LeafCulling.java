package toni.sodiumleafculling;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import toni.sodiumleafculling.config.LeafCullingConfig;

public class LeafCulling {
    private static final EnumFacing[] VALUES = EnumFacing.values();

    public static boolean surroundedByLeaves(IBlockAccess view, BlockPos pos) {
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        boolean isAggressiveMode = LeafCullingConfig.cullingMode == LeafCullingMode.SOLID_AGGRESSIVE;
        for (EnumFacing dir : VALUES) {
            if (isAggressiveMode && (dir == EnumFacing.DOWN || dir == EnumFacing.UP))
                continue;

            mpos.setPos(pos).move(dir);
            IBlockState blockstate = view.getBlockState(mpos);

            if (blockstate.getBlock() instanceof BlockLeaves)
                continue;

            if (!blockstate.isSideSolid(view, mpos, dir.getOpposite()))
                return false;
        }
        return true;
    }

    public static boolean shouldCullSide(IBlockAccess view, BlockPos pos, EnumFacing facing, int depth) {
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        mpos.setPos(pos).move(facing);

        IBlockState firstState = view.getBlockState(mpos);
        if (firstState.getBlock() == Blocks.AIR || !(firstState.getBlock() instanceof BlockLeaves))
            return false;

        for (int i = 2; i <= depth; i++) {
            mpos.setPos(pos).move(facing, i);
            if (!(view.getBlockState(mpos).getBlock() instanceof BlockLeaves)) {
                return false;
            }
        }
        return true;
    }
}
