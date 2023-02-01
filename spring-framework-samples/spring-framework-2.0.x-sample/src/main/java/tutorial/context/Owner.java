package tutorial.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import tutorial.beans.IOwner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:23
 * @since 1.0
 */
public class Owner implements IOwner, ApplicationContextAware {
    private ApplicationContext ctx;
    private String name;
    private List pets = new ArrayList();

    public Owner() {
    }

    public Owner(String name, List pets) {
        this.name = name;
        this.pets = pets;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void feedPets() {
        double d = Math.random();
        FoodEvent ev = new FoodEvent(this, d < 0.5 ? FoodEvent.BREAKFAST : FoodEvent.DINNER);
        ctx.publishEvent(ev);
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
