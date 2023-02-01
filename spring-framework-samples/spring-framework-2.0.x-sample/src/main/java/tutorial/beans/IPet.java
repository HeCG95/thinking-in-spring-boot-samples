package tutorial.beans;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:17
 * @since 1.0
 */
public interface IPet {
    void setName(String name);
    String getName();
    int getAge();
    void setAge(int age);
    void setSpecies(ISpecies sp);
    ISpecies getSpecies();
}
