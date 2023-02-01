package tutorial.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:23
 * @since 1.0
 */
public class Owner implements IOwner {
    private String name;
    private List pets = new ArrayList();

    public Owner() {
    }

    public Owner(String name, List pets) {
        this.name = name;
        this.pets = pets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getPets() {
        return pets;
    }

    public void setPets(List pets) {
        this.pets.clear();
        this.pets.addAll(pets);
    }
}
