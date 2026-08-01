package com.hjsmc.bksr;

import java.util.function.IntConsumer;

final class BookPageNavigation {
    private BookPageNavigation() {
    }

    static void turnPages(double scrollDelta, boolean shiftDown, int normalPages, int shiftPages,
                          IntConsumer directionConsumer) {
        if (scrollDelta == 0.0D) {
            return;
        }

        int pages = Math.max(1, shiftDown ? shiftPages : normalPages);
        int direction = scrollDelta > 0.0D ? -1 : 1;
        for (int page = 0; page < pages; page++) {
            directionConsumer.accept(direction);
        }
    }

    static int targetPage(int currentPage, int pageCount, int pageDelta) {
        if (pageCount <= 0) {
            return 0;
        }
        return Math.max(0, Math.min(pageCount - 1, currentPage + pageDelta));
    }
}
