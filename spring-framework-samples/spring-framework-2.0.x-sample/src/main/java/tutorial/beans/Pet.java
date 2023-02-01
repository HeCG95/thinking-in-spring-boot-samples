package tutorial.beans;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:20
 * @since 1.0
 */
public class Pet implements IPet {
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
