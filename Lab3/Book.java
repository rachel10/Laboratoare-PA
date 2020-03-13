public class Book implements Item {
    private String name;
    private double weight;
    private int pageNumber;
    private double value;

    public Book(String name, double weight, double value) {
        this.name = name;
        this.pageNumber = (int) weight * 100;
        this.value = value;
        this.weight = weight;
    }

    public int getPageNumber() {
        return pageNumber;
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
        this.weight = weight;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
