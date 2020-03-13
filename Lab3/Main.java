import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Item> list;
        list = new ArrayList<>();

        Item book1 = new Book("Dragon Rising", 3, 5);
        list.add(book1);
        Item book2 = new Book("A Blade in the Dark", 3, 5);
        list.add(book2);
        Item food1 = new Food("Cabbage", 2);
        list.add(food1);
        Item food2 = new Food("Rabbit", 2);
        list.add(food2);
        Item weapon = new Weapon(WeaponType.SWORD, 5, 10);
        list.add(weapon);


      /*  knapsack.addItem(weapon);
        knapsack.addItem(book1);
        knapsack.addItem(food1);
        List<Item> listKnapsack = new ArrayList<>();
        listKnapsack = knapsack.getItems();
        listKnapsack.sort(Knapsack::compareByName);
        for (Item item : listKnapsack) {
            System.out.println(item);
        }
        System.out.println("Total weight: " + knapsack.getWeight() + ", total value: " + knapsack.getValue());
       */
//        Knapsack knapsack = new Knapsack(10);
//
//        Algorithm algorithm1=new Greedy(knapsack,list);
//        algorithm1.solution();
//        algorithm1.printSolution();
//
//
//        Knapsack knapsack1 = new Knapsack(10);
//
//        Algorithm algorithm2 =new DynamicProgr(knapsack1,list);
//        algorithm2.solution();
//        algorithm2.printSolution();

        ProblemGenerator problemGenerator = new ProblemGenerator();

        Knapsack knapsack1 = problemGenerator.knapsackGenerator();

        Knapsack knapsack2 = new Knapsack(knapsack1.getCapacity());

        List<Item> items1 = problemGenerator.listItemsGenerator();
        List<Item> items2 = new ArrayList<>();

        items2.addAll(items1);

        System.out.println("Capacitatea totala: " + knapsack1.getCapacity() + " " + knapsack2.getCapacity());

        Algorithm algorithm1 = new Greedy(knapsack1, items1);
        Algorithm algorithm2 = new DynamicProgr(knapsack2, items2);
        long timeToStartGreedy = System.currentTimeMillis();
        algorithm1.solution();
        //algorithm1.printSolution();
        long timeToEndGreedy = System.currentTimeMillis();


        System.out.println("Greedy algorithm: time " + (timeToEndGreedy - timeToStartGreedy) + " value of knapsack: " + algorithm1.getKnapsack().getValue() + " weight of knapsack: " + algorithm1.getKnapsack().getWeight());


        long timeToStartDP = System.currentTimeMillis();
        algorithm2.solution();
        //algorithm2.printSolution();
        long timeToEndDP = System.currentTimeMillis();

        System.out.println("DP algorithm: time " + (timeToEndDP - timeToStartDP) + " value of knapsack: " + algorithm2.getKnapsack().getValue() + " weight of knapsack: " + algorithm2.getKnapsack().getWeight());
    }
}
