package com.hjsmc.bksr;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod(BookScroller.MOD_ID)
@Mod.EventBusSubscriber(modid = BookScroller.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class BookScroller {
    public static final String MOD_ID = "bksr";

    public BookScroller(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, BookScrollerConfig.SPEC);
        MinecraftForge.registerConfigScreen(BookScrollerConfigScreen::new);
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onMouseScrolled(ScreenEvent.MouseScrolled.Pre event) {
        if (!(event.getScreen() instanceof BookViewScreen bookScreen) || event.getScrollDelta() == 0.0D) {
            return;
        }

        BookPageNavigation.turnPages(event.getScrollDelta(), Screen.hasShiftDown(),
                BookScrollerConfig.normalPages(), BookScrollerConfig.shiftPages(), direction -> {
                    int key = direction < 0 ? GLFW.GLFW_KEY_PAGE_UP : GLFW.GLFW_KEY_PAGE_DOWN;
                    bookScreen.keyPressed(key, 0, 0);
                });
        event.setCanceled(true);
    }
}
