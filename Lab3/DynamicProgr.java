import java.util.ArrayList;
import java.util.List;

public class DynamicProgr implements Algorithm {
    private Knapsack knapsack;
    private List<Item> list;
    private double table[][];

    private DynamicProgr() {
        knapsack = new Knapsack();
        list = new ArrayList<>();
    }

    public DynamicProgr(Knapsack knapsack, List<Item> list) {
        this();
        this.knapsack = knapsack;
        this.list = list;
        table = new double[list.size() + 1][(int) knapsack.getCapacity() + 1];
    }

    private double max(double value1, double value2) {
        if (value1 > value2) {
            return value1;
        }
        return value2;
    }

    @Override
    public void solution() {
        for (int i = 1; i <= list.size(); i++) {
            for (int j = 1; j <= knapsack.getCapacity(); j++) {
                if (list.get(i - 1).getWeight() > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = max(table[i - 1][j], table[i - 1][j - (int) list.get(i - 1).getWeight()] + list.get(i - 1).getValue());
                }
            }
        }
        int w = (int) knapsack.getCapacity();
        for (int i = list.size(); i > 0; i--) {
            if (table[i][w] != table[i - 1][w]) {//elementul a fost adaugat in knapsack
                knapsack.addItem(list.get(i - 1));
                w = w - (int) list.get(i - 1).getWeight();
            }
            //else elementul NU a fost adaugat, deci NU il adaugam in lista, trecem la i-1, w (weight) ramane acelasi.
        }
    }

    @Override
    public void printSolution() {
        System.out.println("Dynamic Programming solution: ");
        for (Item item : knapsack.getItems()) {
            System.out.println(item.getName() + " " + "value: " + item.getValue() + " " + "weight: " + item.getWeight());
        }
        System.out.println("Knapsack value: " + knapsack.getValue());
        System.out.println("Knapsack weight: " + knapsack.getWeight());
        System.out.println();

    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

}
