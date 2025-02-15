package Jade;

import Components.Sprite;
import Components.SpriteRenderer;
import Components.Spritesheet;
import Renderer.Texture;
import Util.AssetPool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private Spritesheet sprites;
    private SpriteRenderer obj1Sprite;

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250, 0));

        sprites = AssetPool.getSpritesheet("Assets/Images/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 2);
//        SpriteRenderer obj1Sprite = new SpriteRenderer();
        obj1Sprite = new SpriteRenderer();
        obj1Sprite.setColor(new Vector4f(1,0,0,1));
        obj1.addComponent(obj1Sprite);
        this.addGameObjectToScene(obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2",
                new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 3);
        SpriteRenderer obj2SpriteRenderer = new SpriteRenderer();
        Sprite obj2Sprite = new Sprite();
        obj2Sprite.setTexture(AssetPool.getTexture("Assets/Images/blendImage2.png"));
        obj2SpriteRenderer.setSprite(obj2Sprite);
        obj2.addComponent(obj2SpriteRenderer);
        this.addGameObjectToScene(obj2);

//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();
//
//        String serialized = gson.toJson(obj1);
//        System.out.println(serialized);
//        GameObject obj  = gson.fromJson(serialized, GameObject.class);
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

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui(){
        ImGui.begin("Test window");
        ImGui.text("Some random text");
        ImGui.end();
    }
}
