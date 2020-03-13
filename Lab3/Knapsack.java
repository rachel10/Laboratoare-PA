import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private double capacity;
    private List<Item> items;

    protected Knapsack() {
    }

    public Knapsack(double capacity) {
        this.capacity = capacity;
        items = new ArrayList<>();
    }

    public Knapsack(double capacity, List<Item> items) {
        this.capacity = capacity;
        items = new ArrayList<>();
        this.items = items;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getWeight() {
        int weight = 0;
        for (Item item : items) {
            weight += item.getWeight();
        }
        return weight;
    }

    public int getValue() {
        int value = 0;
        for (Item item : items) {
            value += item.getValue();
        }
        return value;
    }

    public static int compareByName(Item i1, Item i2) {
        return i1.getName().compareTo(i2.getName());
    }


    @Override
    public String toString() {
        return "Knapsack{" +
                "capacity=" + capacity +
                ", items=" + items +
                '}';
    }


}
