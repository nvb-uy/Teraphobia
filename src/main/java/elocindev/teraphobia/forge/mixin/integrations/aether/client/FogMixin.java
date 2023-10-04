package elocindev.teraphobia.forge.mixin.integrations.aether.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.mojang.blaze3d.systems.RenderSystem;

import elocindev.teraphobia.forge.client.TeraphobiaClientStates;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;

@Mixin(FogRenderer.class)
public class FogMixin {
    @Inject(method = "setupFog", at = @At("TAIL"))
	private static void eldritch_end_applyFog(Camera camera, FogRenderer.FogMode fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
		
		Minecraft client = Minecraft.getInstance();
		ClientLevel world = client.level;

		if (world == null || client.player == null || !camera.getFluidInCamera().equals(FogType.NONE)) return;

		if (world.dimension().equals(AetherDimensions.AETHER_LEVEL)
        && TeraphobiaClientStates.INFECTION) {
			RenderSystem.setShaderFogStart(Mth.lerp(1.0f, vanillaFogStart(viewDistance), 0f));
			RenderSystem.setShaderFogEnd(Mth.lerp(1.0f, viewDistance, viewDistance / 3));
		}
	}

	private static float vanillaFogStart(float viewDistance) {
		float f = Mth.clamp(64.0f, viewDistance / 10.0f, 4.0f);
		return viewDistance - f;
	}
}
