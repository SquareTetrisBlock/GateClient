package me.thef1xer.gateclient.modules;

import me.thef1xer.gateclient.settings.Setting;
import me.thef1xer.gateclient.settings.impl.BooleanSetting;
import me.thef1xer.gateclient.util.render.Position;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module {
    private final String name;
    private final String id;
    private boolean enabled = false;
    private int keyBind;
    private final ModuleCategory moduleCategory;
    private final List<Setting> settings = new ArrayList<>();
    public Position position = new Position(0, 0, 1F);
    private int colour; //bri'ish

    public final BooleanSetting drawOnHud = new BooleanSetting("Draw on Hud", "drawonhud", true);

    public Module(String name, String id, ModuleCategory category) {
        this(name, id, Keyboard.KEY_NONE, category);
    }

    public Module(String name, String id, int keyBind, ModuleCategory category) {
        this.name = name;
        this.id = id;
        this.keyBind = keyBind;
        this.moduleCategory = category;
        this.addSettings(drawOnHud);
        this.colour = (int)(0xFFFFFF*Math.random());
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return id;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean set) {
        this.enabled = set;
        if (set) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void toggle() {
        setEnabled(!this.enabled);
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public int getKeyBind() {
        return this.keyBind;
    }

    public void setKeyBind(int key) {
        this.keyBind = key;
    }

    public int getColour() {
        return colour;
    }

    public ModuleCategory getModuleCategory() {
        return this.moduleCategory;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public enum ModuleCategory {
        //https://flatuicolors.com/
        COMBAT("Combat", 0xFFc0392b),
        HUD("HUD", 0xFF8e44ad),
        MOVEMENT("Movement", 0xFF3498db),
        PLAYER("Player", 0xFFe67e22),
        RENDER("Render", 0xFF16a085);

        private final String name;
        private final int color;
        ModuleCategory(String name, int color) {
            this.name = name;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public int getColor() {
            return color;
        }
    }
}
