package me.thef1xer.gateclient.managers;

import me.thef1xer.gateclient.modules.Module;
import me.thef1xer.gateclient.modules.combat.Criticals;
import me.thef1xer.gateclient.modules.combat.KillAura;
import me.thef1xer.gateclient.modules.hud.*;
import me.thef1xer.gateclient.modules.player.AutoArmor;
import me.thef1xer.gateclient.modules.combat.AutoTotem;
import me.thef1xer.gateclient.modules.player.AutoDisconnect;
import me.thef1xer.gateclient.modules.player.Freecam;
import me.thef1xer.gateclient.modules.movement.*;
import me.thef1xer.gateclient.modules.render.*;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public final List<Module> MODULE_LIST = new ArrayList<>();

    public void init() {
        // Combat
        MODULE_LIST.add(new AutoTotem());
        MODULE_LIST.add(new Criticals());
        MODULE_LIST.add(new KillAura());
        // HUD
        MODULE_LIST.add(new ArmorHUD());
        MODULE_LIST.add(new ClickGuiModule());
        MODULE_LIST.add(new Coords());
        MODULE_LIST.add(new ModuleList());
        MODULE_LIST.add(new Watermark());
        // Movement
        MODULE_LIST.add(new Flight());
        MODULE_LIST.add(new GuiMove());
        MODULE_LIST.add(new Jesus());
        MODULE_LIST.add(new NoFall());
        MODULE_LIST.add(new NoSlow());
        MODULE_LIST.add(new SafeWalk());
        MODULE_LIST.add(new Speed());
        MODULE_LIST.add(new Sprint());
        MODULE_LIST.add(new Velocity());
        // Player
        MODULE_LIST.add(new AutoArmor());
        MODULE_LIST.add(new AutoDisconnect());
        MODULE_LIST.add(new Freecam());
        // Render
        MODULE_LIST.add(new EntityESP());
        MODULE_LIST.add(new StorageESP());
        MODULE_LIST.add(new FullBright());
        MODULE_LIST.add(new Nametags());
        MODULE_LIST.add(new NoOverlay());
        MODULE_LIST.add(new ShulkerViewer());
        MODULE_LIST.add(new Tracers());
        MODULE_LIST.add(new XRay());
    }

    public Module getModule(Class<? extends Module> clazz) {
        return MODULE_LIST.stream().filter(module -> module.getClass().equals(clazz)).collect(Collectors.toList()).get(0);
    }

}
