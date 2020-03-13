import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProblemGenerator {

    ProblemGenerator() {
    }

    private int randomInIntreval(int min, int max) {
        Random rand = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return rand.nextInt((max - min) + 1) + min;
    }

    private Item itemGenerator() {
        int typeOfItem = randomInIntreval(1, 3);
        Item item;
        if (typeOfItem == 1) {//book
            item = new Book("book" + randomInIntreval(1, 100), randomInIntreval(1, 100), randomInIntreval(1, 100));
            return item;
        }
        if (typeOfItem == 2) {//food
            item = new Food("food" + randomInIntreval(1, 100), randomInIntreval(1, 100));
            return item;
        }
        //weapon
        item = new Weapon(WeaponType.SWORD, randomInIntreval(1, 100), randomInIntreval(1, 100));
        return item;

    }

    public List<Item> listItemsGenerator() {
        int lenght = randomInIntreval(1, 100);
        List<Item> list = new ArrayList<>();
        for (int i = 1; i <= lenght; i++) {
            list.add(itemGenerator());
        }
        return list;
    }

    public Knapsack knapsackGenerator() {
        return new Knapsack(randomInIntreval(1, 100));
    }

}
