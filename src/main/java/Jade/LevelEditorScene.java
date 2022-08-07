package Jade;

import Components.Sprite;
import Components.SpriteRenderer;
import Components.Spritesheet;
import Util.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene {

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        Spritesheet sprites = AssetPool.getSpritesheet("Assets/Images/spritesheet.png");

        GameObject obj1 = new GameObject("Object1 ",
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

    public void update(float dt) {
        if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 100f * dt;
        }
        if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
            camera.position.y += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 100f * dt;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
