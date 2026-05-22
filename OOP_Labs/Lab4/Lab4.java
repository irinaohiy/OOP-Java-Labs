import java.util.Arrays;
import java.util.Comparator;

class Furniture {
    // 5 полів різних типів з інкапсуляцією (private)
    private final String name;
    private final String material;
    private final double price;
    private final double weight;
    private final int durabilityYears;

    public Furniture(String name, String material, double price, double weight, int durabilityYears) {
        this.name = name;
        this.material = material;
        this.price = price;
        this.weight = weight;
        this.durabilityYears = durabilityYears;
    }

    public String getName() { return name; }
    public String getMaterial() { return material; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }
    public int getDurabilityYears() { return durabilityYears; }

    @Override
    public String toString() {
        return String.format("Furniture{name='%s', material='%s', price=%.2f, weight=%.1f, durability=%d yrs}",
                name, material, price, weight, durabilityYears);
    }
}

public class Lab4 {
    public static void main(String[] args) {
        Furniture[] catalog = {
            new Furniture("Chair", "Wood", 45.0, 5.2, 5),
            new Furniture("Table", "Glass", 120.0, 15.0, 3),
            new Furniture("Sofa", "Fabric", 350.0, 45.0, 10),
            new Furniture("Stool", "Wood", 45.0, 3.0, 2) // Однакова ціна з Chair, але менша вага
        };

        // Багаторівневе сортування: за ціною (вгору), за вагою (вниз)
        Arrays.sort(catalog, new Comparator<Furniture>() {
            @Override
            public int compare(Furniture f1, Furniture f2) {
                int priceComp = Double.compare(f1.getPrice(), f2.getPrice());
                if (priceComp != 0) return priceComp;
                return Double.compare(f2.getWeight(), f1.getWeight()); // Спадання ваги
            }
        });

        System.out.println("Sorted catalog:");
        for (Furniture f : catalog) System.out.println(f);

        // Перевірка пошуку тотожного об'єкта
        Furniture target = new Furniture("Table", "Glass", 120.0, 15.0, 3);
        Furniture found = findIdentical(catalog, target);
        System.out.println("\nSearch result for identical object: " + (found != null ? "Found! " + found : "Not found."));
    }

    // Глибоке порівняння всіх 5 полів об'єкта
    public static Furniture findIdentical(Furniture[] catalog, Furniture target) {
        for (Furniture f : catalog) {
            if (f.getName().equals(target.getName()) &&
                f.getMaterial().equals(target.getMaterial()) &&
                Double.compare(f.getPrice(), target.getPrice()) == 0 &&
                Double.compare(f.getWeight(), target.getWeight()) == 0 &&
                f.getDurabilityYears() == target.getDurabilityYears()) {
                return f;
            }
        }
        return null;
    }
}