package Jade;


import java.util.List;

public class GameObject {

    private String name;
    private List<Component> components;

    public GameObject(String name) {
        this.name = name;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error casting component";
                }
            }
        }
        return null;
    }
}
