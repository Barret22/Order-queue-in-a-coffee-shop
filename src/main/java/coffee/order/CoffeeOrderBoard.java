package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private final List<Order> orders = new LinkedList<>();
    private int nextOrderNumber = 1;

    public void add(String customerName) {
        Order newOrder = new Order(nextOrderNumber++, customerName);
        orders.add(newOrder);
        logger.info("Додано нове замовлення: {}", newOrder);
    }

    public Order deliver() {
        if (orders.isEmpty()) {
            logger.warn("Спроба видати замовлення, але черга пуста");
            return null;
        }
        Order delivered = orders.remove(0);
        logger.info("Видано замовлення: {}", delivered);
        return delivered;
    }

    public Order deliver(int orderNumber) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderNumber() == orderNumber) {
                iterator.remove();
                logger.info("Видано замовлення за номером: {}", order);
                return order;
            }
        }
        logger.error("Замовлення з номером {} не знайдено", orderNumber);
        return null;
    }

    public void draw() {
        logger.info("Поточна черга замовлень:");
        System.out.println("Num | Name");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

