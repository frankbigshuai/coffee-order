package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

@Repository
public class OrderRepository {
    private static final String DATABASE_NAME = "db.txt";
    private static final String NEW_LINE = System.lineSeparator();

    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
            case "decaf":
                beverage = new Decaf();
                break;
            case "espresso":
                beverage = new Espresso();
                break;
            case "houseblend":
                beverage = new HouseBlend();
                break;
            case "dark roast":
                beverage = new DarkRoast();
                break;
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.beverage()));
        }
        for(String condiment : order.condiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                   beverage = new Milk(beverage);
                   break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    break;
                case "soy":
                    beverage = new Soy(beverage);
                    break;
                case "whip":
                    beverage = new Whip(beverage);
                    break;
                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }
        Random random = new Random();
        Receipt receipt = new Receipt(random.nextInt(100) + 1,beverage.cost(), beverage.getDescription());

        Path path = Paths.get(DATABASE_NAME);
        String data = receipt.toLine();
        appendToFile(path, data + NEW_LINE);
        return receipt;
    }


}
