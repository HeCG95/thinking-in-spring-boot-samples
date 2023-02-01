package tutorial.context;

import org.springframework.context.ApplicationEvent;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 16:17
 * @since 1.0
 */
public class FoodEvent extends ApplicationEvent {
    public static final int BREAKFAST = 0;
    public static final int DINNER = 1;

    private int type;

    public FoodEvent(Object source, int t) {
        super(source);
        if (t < 0 || t > 1)
            t = 0;
        type = t;
    }
    public void setType(int t) {
        type = t;
    }
    public int getType() {
        return type;
    }
}
