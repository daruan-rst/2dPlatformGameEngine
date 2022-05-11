package Jade;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main(){\n" +
            "    fColor = aColor;\n" +
            "    gl_Position=vec4(aPos,1.0);\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main(){\n" +
            "    color = fColor;\n" +
            "}f";

    private int vertexID, fragmentID, shaderProgram;

    public LevelEditorScene(){

    }

    @Override
    public void init(){
        //Compile and link the shaders

        //First load and compile vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        //Pass the shader source to the GPU
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        //check for errors in compilation process
        int sucess = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (sucess == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        //First load and compile fragment shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        //Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        //check for errors in compilation process
        sucess = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (sucess == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.err.println("ERROR: 'defaultShader.glsl'\n\tFragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }


    }

    @Override
    public void update(float dt) {

  }
}
