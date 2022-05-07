package Jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeToChanceScene = 2.0f;

    public LevelEditorScene(){
        System.out.println("Inside Level Editor scene");
    }

    @Override
    public void update(float dt) {

        System.out.println("" +(1.0/dt) +"FPS");

        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }

        if(changingScene && timeToChanceScene > 0){
            timeToChanceScene -= dt;
            Window.get().r -= dt*5.0f;
            Window.get().g -= dt*5.0f;
            Window.get().b -= dt*5.0f;
        } else if (changingScene) {
            Window.changeScene(1);
        }
    }
}
