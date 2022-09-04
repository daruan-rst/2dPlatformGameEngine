package Jade;

import Components.Sprite;
import Components.SpriteRenderer;
import Components.Spritesheet;
import Util.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private Spritesheet sprites;

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        sprites = AssetPool.getSpritesheet("Assets/Images/spritesheet.png");

        obj1 = new GameObject("Object1 ",
                            new Transform(
                                new Vector2f(100,100),
                                new Vector2f(256,256)));
        obj1.addComponent(new SpriteRenderer((sprites.getSprite(0))));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object2 ",
                new Transform(
                        new Vector2f(400,100),
                        new Vector2f(256,256)));
        obj1.addComponent(new SpriteRenderer((sprites.getSprite(10))));
        this.addGameObjectToScene(obj2);

    }

    private void loadResources(){
        AssetPool.getShader("Assets/Shaders/default.glsl");
        AssetPool.addSpritesheet("Assets/Images/spritesheet.png",
                            new Spritesheet(AssetPool.getTexture("Assets/Images/spritesheet.png"),
                                        16,16,26,0));
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
    public void update(float dt) {
        obj1.transform.position.x += 10 * dt;
        spriteFlipTimeLeft -= dt;
        if (spriteFlipTimeLeft <= 0){
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if (spriteIndex > 4){
                spriteIndex = 0;
            }
            obj1.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
