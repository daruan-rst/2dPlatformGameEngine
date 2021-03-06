package Components;

import Jade.Component;
import Renderer.Texture;
import lombok.Getter;
import org.joml.Vector2f;
import org.joml.Vector4f;

@Getter
public class SpriteRenderer extends Component {

    private Vector4f color;
    private Vector2f[] texCoords;
    //(0,1)
    //(0,0)
    //(1,1)
    //(1,0)
    private Texture texture;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.texture= null;
    }

    public SpriteRenderer(Texture texture){
        this.texture = texture;
        this.color = new Vector4f(1,1,1,1);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float dt) {

    }

    public Vector2f[] getTexCoords() {
        Vector2f[] texCoords = {
            new Vector2f(1,1),
            new Vector2f(1,0),
            new Vector2f(0,0),
            new Vector2f(0,1)
        };
    return  texCoords;}
}
