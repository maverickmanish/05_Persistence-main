package com.backstreetbrogrammer.chapter07_serializationwithlistfields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarketDataWithListFieldsTest {

    private File serFile;
    private final List<String> mdProviders = List.of("REUT", "BBG", "CHIX");

    @BeforeEach
    void setUp() throws IOException {
        final var serPath = Path.of("src", "test", "resources", "MarketDataWithListFieldsTest.ser");
        serFile = serPath.toFile();
        if (!serFile.exists()) {
            final var success = serFile.createNewFile();
            assertTrue(success);
        }
    }

    @Test
    @DisplayName("Test basic serialization for Java POJO with list member fields")
    void testSerialize() throws IOException {
        final var marketData = new MarketDataWithListFields();
        marketData.setSecurityId("AAPL");
        marketData.setTime(10000L);
        marketData.setOpen(160.3D);
        marketData.setHigh(165.7D);
        marketData.setLow(157.2D);
        marketData.setClose(163.1D);
        marketData.setLast(161.9D);
        marketData.setLevelOne(true);
        marketData.setMdProviders(mdProviders);

        // serialize and write
        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(serFile)))) {
            System.out.println("Before Serialization: ");
            System.out.println(marketData);
            oos.writeObject(marketData);
        }
    }

    @Test
    @DisplayName("Test basic deserialization for Java POJO with list member fields")
    void testDeserialize() throws IOException, ClassNotFoundException {
        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(serFile)))) {
            final var fromSerialize = (MarketDataWithListFields) ois.readObject();
            System.out.println("After Serialization: ");
            System.out.println(fromSerialize);

            // assertions
            assertNotNull(fromSerialize);
            assertEquals("AAPL", fromSerialize.getSecurityId());
            assertEquals(10000L, fromSerialize.getTime());
            assertEquals(160.3D, fromSerialize.getOpen());
            assertEquals(165.7D, fromSerialize.getHigh());
            assertEquals(157.2D, fromSerialize.getLow());
            assertEquals(163.1D, fromSerialize.getClose());
            assertEquals(161.9D, fromSerialize.getLast());
            assertTrue(fromSerialize.isLevelOne());

            final var fromSerializeMdProviders = fromSerialize.getMdProviders();
            for (int i = 0; i < fromSerializeMdProviders.size(); i++) {
                assertEquals(mdProviders.get(i), fromSerializeMdProviders.get(i));
            }
        }
    }
}
