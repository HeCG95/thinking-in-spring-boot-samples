package tutorial.beans;

import java.util.List;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:17
 * @since 1.0
 */
public interface IOwner {
    void setName(String name);
    String getName();
    void setPets(List pets);
    List getPets();
}
