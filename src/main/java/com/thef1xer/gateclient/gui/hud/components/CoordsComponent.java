package com.thef1xer.gateclient.gui.hud.components;

import com.thef1xer.gateclient.gui.hud.HUDComponent;
import com.thef1xer.gateclient.modules.render.HUDModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;

public class CoordsComponent extends HUDComponent {
    @Override
    protected boolean isEnabled() {
        return HUDModule.INSTANCE.renderCoords.getValue();
    }

    @Override
    public void componentAction() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        Vec3d pos = new Vec3d(Math.round(Minecraft.getMinecraft().player.posX * 100D) / 100D,
                Math.round(Minecraft.getMinecraft().player.posY * 100D) / 100D,
                Math.round(Minecraft.getMinecraft().player.posZ * 100D) / 100D);
        Vec3d netherPos = new Vec3d(Math.round(Minecraft.getMinecraft().player.posX * 12.5D) / 100D,
                Math.round(Minecraft.getMinecraft().player.posY * 100D) / 100D,
                Math.round(Minecraft.getMinecraft().player.posZ * 12.5D) / 100D);
        String coords = TextFormatting.GRAY + "XYZ: " + TextFormatting.RESET + pos.x + ", " + pos.y + ", " + pos.z;
        String nether = TextFormatting.GRAY + "Nether: " + TextFormatting.RESET + netherPos.x + ", " + netherPos.y + ", " + netherPos.z;

        fr.drawStringWithShadow(coords, 4, sr.getScaledHeight() - 2 * fr.FONT_HEIGHT - 4, 0xFFFFFF);
        fr.drawStringWithShadow(nether, 4, sr.getScaledHeight() - fr.FONT_HEIGHT - 4, 0xFFFFFF);
    }
}