import java.util.ArrayList;
import java.util.List;

public class Greedy implements Algorithm {
    private Knapsack knapsack;
    private List<Item> list;

    private Greedy() {
        knapsack = new Knapsack();
        list = new ArrayList<>();
    }

    public Greedy(Knapsack knapsack, List<Item> list) {
        this();
        this.knapsack = knapsack;
        this.list = list;
    }

    public static int compareByValue(Item i1, Item i2) {
        if (i1.getValue() < i2.getValue()) {
            return 1;
        } else {
            if (i1.getValue() == i2.getValue()) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public void solution() {
        list.sort(Greedy::compareByValue);
        for (Item item : list) {
            if (knapsack.getCapacity() >= knapsack.getWeight() + item.getWeight()) {
                knapsack.addItem(item);
            }
        }
    }

    @Override
    public void printSolution() {
        System.out.println("Greedy solution: ");
        for (Item item : knapsack.getItems()) {
            System.out.println(item.getName() + " " + item.getValue() + " " + item.getWeight());
        }
        System.out.println("Knapsack value: " + knapsack.getValue());
        System.out.println("Knapsack weight: " + knapsack.getWeight());
        System.out.println();
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }


}
