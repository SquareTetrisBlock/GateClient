package me.thef1xer.gateclient.modules.hud;

import me.thef1xer.gateclient.GateClient;
import me.thef1xer.gateclient.modules.Module;
import me.thef1xer.gateclient.settings.impl.RGBSetting;
import me.thef1xer.gateclient.settings.impl.EnumSetting;
import me.thef1xer.gateclient.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleList extends Module {

    private List<Module> modulesSorted;
    private final FontRenderer fr = Minecraft.getMinecraft().fontRenderer;

    public final EnumSetting color = new EnumSetting("List Color", "color", Color.values(), Color.STATIC);
    public final RGBSetting staticColor = new RGBSetting("Static Color", "static", 255, 255, 255);

    public ModuleList() {
        super("Module List", "modulelist", Module.ModuleCategory.HUD);
        addSettings(color, staticColor);
    }

    public void drawList(ScaledResolution sr) {
        int i = 0;
        for (Module module : this.modulesSorted) {
            if (module.drawOnHud.getValue()) {
                int hexColor = -1;
                switch (color.getCurrentValue().ordinal()) {
                    case 0:
                        hexColor = ColorUtil.RGBtoHex(staticColor.getRed(), staticColor.getGreen(), staticColor.getBlue());
                        break;
                    case 1:
                        hexColor = module.getModuleCategory().getColor();
                        break;
                    case 2:
                        int[] rainbow = ColorUtil.getRainbow(5, 0.1F * i);
                        hexColor = ColorUtil.RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
                        break;
                    case 3:
                        hexColor = module.getColour();
                        break;
                }
                fr.drawStringWithShadow(module.getName(), (float) (sr.getScaledWidth() - module.position.getX()), (float) module.position.getY(), hexColor);
                if(module.isEnabled()) {
                    module.position.interpolate(fr.getStringWidth(module.getName()) + 4, 4 + i * fr.FONT_HEIGHT);
                    i++;
                } else {
                    module.position.interpolate(-fr.getStringWidth(module.getName()), 4 + i * fr.FONT_HEIGHT);
                }
            }
        }
    }

    public void sortList() {
        modulesSorted = new ArrayList<>(GateClient.getGate().moduleManager.MODULE_LIST);
        modulesSorted.sort(Comparator.comparingInt(module -> fr.getStringWidth(((Module)module).getName())).reversed());
    }

    public enum Color {
        STATIC("Static"),
        CATEGORY("Category"),
        RAINBOW("Rainbow"),
        RANDOM("Random");

        private final String name;
        Color(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
