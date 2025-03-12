import org.example.InsufficientStockException;
import org.example.StockManager;
import org.example.UnknownProductException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockManagerTest {

    private static StockManager stockManager;

    @BeforeAll
    public static void setUp() {
        stockManager = new StockManager();
    }

    @BeforeEach
    public void setQuantities(){
        stockManager.setQuantity("PRODUCT1", 10);
        stockManager.setQuantity("PRODUCT2", 0);
        stockManager.setQuantity("PRODUCT3", 5);
        stockManager.setQuantity("PRODUCT4", 1);
    }

    @Test
    void testHasStock() throws UnknownProductException {
        assertTrue(stockManager.hasStock("PRODUCT1"));
        assertTrue(stockManager.hasStock("PRODUCT3"));
        assertTrue(stockManager.hasStock("PRODUCT4"));
    }

    @Test
    void testHasNoStock() throws UnknownProductException {
        assertFalse(stockManager.hasStock("PRODUCT2"));
    }

    @Test
    void testGetQuantity() throws UnknownProductException {
        assertEquals(10, stockManager.getQuantity("PRODUCT1"));
        assertEquals(0, stockManager.getQuantity("PRODUCT2"));
        assertEquals(5, stockManager.getQuantity("PRODUCT3"));
        assertEquals(1, stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testUnknownProduct() {
        assertThrows(UnknownProductException.class,
                () -> stockManager.getQuantity("UNKNOWN_PRODUCT")
        );
        assertThrows(UnknownProductException.class,
                () -> stockManager.hasStock("UNKNOWN_PRODUCT")
        );
        assertThrows(UnknownProductException.class,
                () -> stockManager.destock("UNKNOWN_PRODUCT")
        );
    }

    @Test
    void testDestock() throws UnknownProductException, InsufficientStockException {
        stockManager.destock("PRODUCT1");
        assertEquals(9, stockManager.getQuantity("PRODUCT1"));

        stockManager.destock("PRODUCT3");
        assertEquals(4, stockManager.getQuantity("PRODUCT3"));

        stockManager.destock("PRODUCT4");
        assertEquals(0, stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testDestockInsufficientStock() {
        assertThrows(InsufficientStockException.class, () ->
            stockManager.destock("PRODUCT2")
        );
    }

    @Test
    void testNDestock() throws UnknownProductException, InsufficientStockException {
        stockManager.destock("PRODUCT1", 5);
        assertEquals(5, stockManager.getQuantity("PRODUCT1"));
        stockManager.destock("PRODUCT3", 3);
        assertEquals(2, stockManager.getQuantity("PRODUCT3"));
        stockManager.destock("PRODUCT4", 1);
        assertEquals(0, stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testNDestockInsufficientStock() {
        assertThrows(InsufficientStockException.class, () ->{
            stockManager.destock("PRODUCT1", 11);
        });
        assertThrows(InsufficientStockException.class, () ->{
            stockManager.destock("PRODUCT2", 1);
        });
    }

    @Test
    void testRestock(){
        fail("Not yet implemented");
    }

    @Test
    void testNRestock(){
        fail("Not yet implemented");
    }


}
