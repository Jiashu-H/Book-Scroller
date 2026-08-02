package com.hjsmc.bksr;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.gui.screens.inventory.LecternScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookPageNavigationScreenTest {
    @Test
    void recognizesReadableAndEditableBookScreens() {
        assertTrue(BookPageNavigation.supportsBookScrolling(BookViewScreen.class));
        assertTrue(BookPageNavigation.supportsBookScrolling(LecternScreen.class));
        assertTrue(BookPageNavigation.supportsBookScrolling(BookEditScreen.class));
    }

    @Test
    void ignoresOtherScreens() {
        assertFalse(BookPageNavigation.supportsBookScrolling(Screen.class));
    }
}
