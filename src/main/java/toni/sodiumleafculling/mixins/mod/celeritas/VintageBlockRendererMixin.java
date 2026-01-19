package toni.sodiumleafculling.mixins.mod.celeritas;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.embeddedt.embeddium.impl.model.light.LightPipeline;
import org.embeddedt.embeddium.impl.render.chunk.compile.ChunkBuildBuffers;
import org.embeddedt.embeddium.impl.render.chunk.compile.buffers.ChunkModelBuilder;
import org.embeddedt.embeddium.impl.render.chunk.terrain.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.taumc.celeritas.impl.render.terrain.compile.pipeline.VintageBlockRenderer;
import org.taumc.celeritas.impl.world.cloned.CeleritasBlockAccess;
import toni.sodiumleafculling.LeafCulling;
import toni.sodiumleafculling.LeafCullingMode;
import toni.sodiumleafculling.config.LeafCullingConfig;

import java.util.List;

@Mixin(value = VintageBlockRenderer.class, remap = false, priority = 100)
public abstract class VintageBlockRendererMixin {
    @Shadow
    private IBlockState currentState;

    @Shadow
    private CeleritasBlockAccess currentBlockAccess;

    @Shadow
    protected abstract void renderQuadList(ChunkModelBuilder defaultBuffer, ChunkBuildBuffers buffers, Material material, BlockPos pos, EnumFacing cullFace,
                                           LightPipeline lighter, net.minecraft.client.renderer.color.IBlockColor colorProvider, Vec3d offset, List<BakedQuad> quads
    );

    @Redirect(
            method = "renderBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/taumc/celeritas/impl/render/terrain/compile/pipeline/VintageBlockRenderer;renderQuadList(Lorg/embeddedt/embeddium/impl/render/chunk/compile/buffers/ChunkModelBuilder;Lorg/embeddedt/embeddium/impl/render/chunk/compile/ChunkBuildBuffers;Lorg/embeddedt/embeddium/impl/render/chunk/terrain/material/Material;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lorg/embeddedt/embeddium/impl/model/light/LightPipeline;Lnet/minecraft/client/renderer/color/IBlockColor;Lnet/minecraft/util/math/Vec3d;Ljava/util/List;)V"
            )
    )
    private void redirect$renderQuadList(VintageBlockRenderer instance, ChunkModelBuilder defaultBuffer, ChunkBuildBuffers buffers, Material material, BlockPos pos,
                                         EnumFacing cullFace, LightPipeline lighter, IBlockColor colorProvider, Vec3d offset, List<BakedQuad> quads) {
        if (currentState.getBlock() instanceof BlockLeaves) {
            boolean isSolid = LeafCullingConfig.cullingMode == LeafCullingMode.SOLID || LeafCullingConfig.cullingMode == LeafCullingMode.SOLID_AGGRESSIVE;
            if (LeafCulling.surroundedByLeaves(currentBlockAccess, pos) && isSolid) {
                Material solidMaterial = buffers.getRenderPassConfiguration().getMaterialForRenderType(BlockRenderLayer.SOLID);
                ChunkModelBuilder solidBuffer = buffers.get(solidMaterial);

                this.renderQuadList(solidBuffer, buffers, solidMaterial, pos, cullFace, lighter, colorProvider, offset, quads);

                return;
            }
        }

        this.renderQuadList(defaultBuffer, buffers, material, pos, cullFace, lighter, colorProvider, offset, quads);
    }
}