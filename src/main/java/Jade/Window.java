package Jade;


import Util.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width;
    private int height;
    private String title;
    private static long glfwWindow;
    public float r,g,b,a;

    private static Window window = null;

    private static Scene currentScene = null;


    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "Platform";
        r=1;
        b=1;
        g=1;
        a=1;
    }

    public static void changeScene(int newScene){
        switch (newScene){
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                currentScene.start();
                break;
            default:
                assert false: "Unknown Scene '" + newScene + "'";
                break;
        }
    }

    public static Window get(){
       return Window.window == null ? Window.window = new Window() : window;
    }

    public static Scene getScene(){
        return get().currentScene;
    }

    public void run(){
        System.out.println("LWJGL " + Version.getVersion());
        init();
        loop();

        //Free the memory once our loop has ended
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate the GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(){
        //Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //Initializa GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initializa GLFW");
        }

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //Create The Window
        glfwWindow = glfwCreateWindow(this.width, this.height,this.title, NULL, NULL);
        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create The GLFW Window");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        //Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();

        Window.changeScene(0);
    }

    boolean fadeToBlack = false;

    public void loop(){
        //

        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1.0f;
        while(!glfwWindowShouldClose(glfwWindow)){
            // Poll events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt>=0){
            currentScene.update(dt);}

            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }

    }
}
