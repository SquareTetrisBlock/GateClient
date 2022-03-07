package me.thef1xer.gateclient.managers;

import me.thef1xer.gateclient.GateClient;
import me.thef1xer.gateclient.modules.hud.Coords;
import me.thef1xer.gateclient.modules.hud.ModuleList;
import me.thef1xer.gateclient.modules.hud.Watermark;
import me.thef1xer.gateclient.modules.render.NoOverlay;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUDManager {

    private ModuleList moduleList;
    private Coords coords;
    private Watermark watermark;
    private NoOverlay noOverlay;

    public void init() {
        moduleList = (ModuleList) GateClient.getGate().moduleManager.getModule(ModuleList.class);
        coords = (Coords) GateClient.getGate().moduleManager.getModule(Coords.class);
        watermark = (Watermark) GateClient.getGate().moduleManager.getModule(Watermark.class);
        noOverlay = (NoOverlay) GateClient.getGate().moduleManager.getModule(NoOverlay.class);
        moduleList.sortList();
    }

    @SubscribeEvent
    public void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            if (moduleList.isEnabled()) {
                moduleList.drawList(event.getResolution());
            }

            if (coords.isEnabled()) {
                coords.drawCoords(event.getResolution());
            }

            if (watermark.isEnabled()) {
                watermark.drawWatermark();
            }
        }

        if (noOverlay.isEnabled()) {
            if (event instanceof RenderGameOverlayEvent.Pre) {
                if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO || event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
                    event.setCanceled(false);
                    event.setCanceled(false);
                }
            }
        }
    }
}
