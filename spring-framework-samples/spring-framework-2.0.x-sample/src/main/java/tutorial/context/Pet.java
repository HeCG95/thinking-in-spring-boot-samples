package tutorial.context;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import tutorial.beans.IPet;
import tutorial.beans.ISpecies;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:20
 * @since 1.0
 */
public class Pet implements IPet, ApplicationListener {
    private String name;
    private int age;
    private ISpecies species;

    public Pet() {
    }

    public Pet(String name, int age, ISpecies species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public void onApplicationEvent(ApplicationEvent e) {
        if (e instanceof FoodEvent) {
            FoodEvent ev = (FoodEvent) e;
            Owner owner = (Owner) ev.getSource();
            String name = owner.getName();
            switch(ev.getType()) {
                case FoodEvent.BREAKFAST:
                    System.out.println("Hurrah, " + name +
                            " says it’s breakfast time");
                    break;
                case FoodEvent.DINNER:
                    System.out.println("Hurrah, " + name +
                            " says it’s dinner time");
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ISpecies getSpecies() {
        return species;
    }

    public void setSpecies(ISpecies species) {
        this.species = species;
    }
}
