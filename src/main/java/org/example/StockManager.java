package org.example;

import java.util.HashMap;
import java.util.Map;

public class StockManager {

    private final Map<String, Integer> stocks = new HashMap<>();

    public void setQuantity(String stock, int quantity) {
        stocks.put(stock, quantity);
    }

    public boolean hasStock(String product) throws UnknownProductException {
        return getQuantity(product) > 0;
    }

    public int getQuantity(String product) throws UnknownProductException {
        if (stocks.containsKey(product)) {
            return stocks.get(product);
        }
        throw new UnknownProductException();
    }

    public void destock(String product) throws UnknownProductException, InsufficientStockException {
        destock(product, 1);
    }

    public void destock(String product, int n) throws UnknownProductException, InsufficientStockException {
        if (getQuantity(product) >= n) {
            var currentStock = getQuantity(product);
            var updatedStock = currentStock - n;
            setQuantity(product, updatedStock);
        } else {
            throw new InsufficientStockException();
        }
    }
}
