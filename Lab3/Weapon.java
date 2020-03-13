public class Weapon implements Item {
    private WeaponType type;
    private double weight;
    private double value;

    public Weapon(WeaponType type, double weight, double value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + getName() +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
