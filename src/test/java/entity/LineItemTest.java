package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineItemTest {
    private static final String NAME1 = "Tom";
    private static final int QUANTITY1 = 3;
    private static final Money PRICE1 = "price"; //this will change when ik how Money look
    private static final Tag TAG1 = null;

    @Test
    void validLineItemTest() {
        LineItem item = new LineItem(NAME1, QUANTITY1, PRICE1, TAG1);

        assertEquals(NAME1, item.getName());
        assertEquals(QUANTITY1, item.getQuantity());
        assertEquals(PRICE1, item.getPrice());
        assertEquals(TAG1, item.getTag());
    }

    @Test
    void invalidDataTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LineItem("", 1, PRICE, "Food");
        });
    }

    @Test
    void equalLineItemTest(){
        LineItem og = new LineItem(NAME1, QUANTITY1, PRICE1, TAG1);
        LineItem item = new LineItem("Tom", 3, "price", null); // change
        LineItem item1 = new LineItem("Sam", 3, "price", null);
        LineItem item2 = new LineItem("Tom", 10, "price", null);
        LineItem item3 = new LineItem("Tom", 3, "diff price", null);
        LineItem item4 = new LineItem("Tom", 3, "price", "Groceries");

        assertTrue(og.equals(og));
        assertTrue(og.equals(item));
        assertEquals(og.hashCode(), item.hashCode());
        assertFalse(og.equals(item1));
        assertFalse(og.equals(item2));
        assertFalse(og.equals(item3));
        assertFalse(og.equals(item4));

    }
}
