public class Food implements Item {
    private String name;
    private double weight;
    private double value;

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.value = weight * 2;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
