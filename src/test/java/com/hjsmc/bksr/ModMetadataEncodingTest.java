package com.hjsmc.bksr;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModMetadataEncodingTest {
    @Test
    void generatedModDescriptionRemainsUtf8() throws IOException {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream("META-INF/mods.toml")) {
            assertNotNull(resource);
            String metadata = new String(resource.readAllBytes(), StandardCharsets.UTF_8);

            assertTrue(metadata.contains("为原版书本界面添加滚轮翻页，支持 Shift 加速。"), metadata);
        }
    }
}
