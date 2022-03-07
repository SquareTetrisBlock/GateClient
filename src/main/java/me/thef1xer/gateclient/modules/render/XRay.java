package me.thef1xer.gateclient.modules.render;

import me.thef1xer.gateclient.GateClient;
import me.thef1xer.gateclient.events.GetAmbientOcclusionLightValueEvent;
import me.thef1xer.gateclient.events.RenderBlockEvent;
import me.thef1xer.gateclient.events.SetOpaqueCubeEvent;
import me.thef1xer.gateclient.events.ShouldSideBeRenderedEvent;
import me.thef1xer.gateclient.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XRay extends Module {

    private float lastGamma;

    public XRay() {
        super("XRay", "xray", Module.ModuleCategory.RENDER);
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register(this);
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 10000F;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister(this);
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        if (!GateClient.getGate().moduleManager.getModule(FullBright.class).isEnabled()) {
            Minecraft.getMinecraft().gameSettings.gammaSetting = lastGamma;
        }
    }

    @SubscribeEvent
    public void onRenderModel(RenderBlockEvent event) {
        if (!isXrayBlock(event.getState().getBlock())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onSetOpaqueCube(SetOpaqueCubeEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onSideRendered(ShouldSideBeRenderedEvent event) {
        event.setShouldBeRendered(isXrayBlock(event.getBlockState().getBlock()));
    }

    @SubscribeEvent
    public void onGetAmbientOcclusion(GetAmbientOcclusionLightValueEvent event) {
        // If this isn't active, the blocks will look black
        event.setLightValue(1.0F);
    }

    public boolean isXrayBlock(Block block) {
        return block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE || block == Blocks.GOLD_BLOCK || block == Blocks.IRON_ORE ||
                block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE || block == Blocks.LAPIS_ORE || block == Blocks.COAL_ORE;
    }
}
