package com.hjsmc.bksr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookPageNavigationTest {
    @Test
    void scrollUpTurnsConfiguredPagesBackward() {
        List<Integer> directions = new ArrayList<>();

        BookPageNavigation.turnPages(1.0D, false, 2, 4, directions::add);

        assertEquals(List.of(-1, -1), directions);
    }

    @Test
    void scrollDownWithShiftTurnsConfiguredPagesForward() {
        List<Integer> directions = new ArrayList<>();

        BookPageNavigation.turnPages(-1.0D, true, 2, 4, directions::add);

        assertEquals(List.of(1, 1, 1, 1), directions);
    }

    @Test
    void zeroScrollDoesNotTurnPages() {
        List<Integer> directions = new ArrayList<>();

        BookPageNavigation.turnPages(0.0D, true, 2, 4, directions::add);

        assertEquals(List.of(), directions);
    }

    @Test
    void targetPageClampsToBookBounds() {
        assertEquals(0, BookPageNavigation.targetPage(1, 10, -3));
        assertEquals(9, BookPageNavigation.targetPage(8, 10, 3));
        assertEquals(4, BookPageNavigation.targetPage(2, 10, 2));
    }
}
