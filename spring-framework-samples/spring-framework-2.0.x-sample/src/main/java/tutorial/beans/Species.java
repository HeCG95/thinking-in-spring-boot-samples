package tutorial.beans;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:18
 * @since 1.0
 */
public class Species implements ISpecies {
    private String name;

    public Species() {
    }

    public Species(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
