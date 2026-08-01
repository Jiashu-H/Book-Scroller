package com.hjsmc.bksr;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public final class BookScrollerConfigScreen extends Screen {
    private final Screen parent;
    private EditBox normalPages;
    private EditBox shiftPages;

    public BookScrollerConfigScreen(Screen parent) {
        super(Component.translatable("bksr.config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int center = this.width / 2;
        this.normalPages = createNumberBox(center - 40, this.height / 2 - 32, BookScrollerConfig.normalPages());
        this.shiftPages = createNumberBox(center - 40, this.height / 2 + 2, BookScrollerConfig.shiftPages());
        this.addRenderableWidget(this.normalPages);
        this.addRenderableWidget(this.shiftPages);
        this.addRenderableWidget(Button.builder(Component.translatable("bksr.config.save"), button -> saveAndClose())
                .bounds(center - 80, this.height / 2 + 42, 76, 20)
                .build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> onClose())
                .bounds(center + 4, this.height / 2 + 42, 76, 20)
                .build());
        this.setInitialFocus(this.normalPages);
    }

    private EditBox createNumberBox(int x, int y, int value) {
        EditBox box = new EditBox(this.font, x, y, 80, 20, Component.empty());
        box.setValue(Integer.toString(value));
        box.setMaxLength(2);
        box.setFilter(input -> input.isEmpty() || input.chars().allMatch(Character::isDigit));
        return box;
    }

    private void saveAndClose() {
        BookScrollerConfig.save(parse(normalPages.getValue(), BookScrollerConfig.normalPages()),
                parse(shiftPages.getValue(), BookScrollerConfig.shiftPages()));
        this.minecraft.setScreen(this.parent);
    }

    private static int parse(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 78, 0xFFFFFF);
        guiGraphics.drawString(this.font, Component.translatable("bksr.config.normal_pages"),
                this.width / 2 - 132, this.height / 2 - 26, 0xFFFFFF);
        guiGraphics.drawString(this.font, Component.translatable("bksr.config.shift_pages"),
                this.width / 2 - 132, this.height / 2 + 8, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == InputConstants.KEY_ESCAPE) {
            onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }
}
