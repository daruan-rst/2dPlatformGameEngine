#type vertex
#version 330 core
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCoords;
layout (location=3) in float aTexId;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 fColor;
out vec2 fTexCoords;
out float fTexId;

void main()
{
    fColor = aColor;
    fTexCoords = aTexCoords;
    fTexId = aTexId;

    gl_Position = uProjection * uView * vec4(aPos, 1.0);
}

    #type fragment
    #version 330 core


in vec4 fColor;
in vec2 fTexCoords;
in float fTexId;

uniform sampler2D uTextures[8];

out vec4 color;

void main ()
{
    switch (int(fTexId)) {
        case 0:
        color = fColor;
        break;
        case 1:
        color = fColor * texture(uTextures[1], fTexCoords);
        break;
        case 2:
        color = fColor * texture(uTextures[2], fTexCoords);
        break;
        case 3:
        color = fColor * texture(uTextures[3], fTexCoords);
        break;
        case 4:
        color = fColor * texture(uTextures[4], fTexCoords);
        break;
        case 5:
        color = fColor * texture(uTextures[5], fTexCoords);
        break;
        case 6:
        color = fColor * texture(uTextures[6], fTexCoords);
        break;
        case 7:
        color = fColor * texture(uTextures[7], fTexCoords);
        break;
        }
}
//#type vertex
//#version 330 core
//layout (location = 0) in vec3 aPos;
//layout (location = 1) in vec4 aColor;
//layout (location = 2) in vec2 aTexCoords;
//layout (location = 3) in float texID;
//
//out vec3 fPos;
//out vec4 fColor;
//out vec2 fTexCoords;
//out float fTexSlot;
//
//uniform mat4 uView;
//uniform mat4 uProjection;
//
//void main()
//{
//    fPos = aPos;
//    fColor = aColor;
//    fTexCoords = aTexCoords;
//    fTexSlot = texID;
//
//    gl_Position = uProjection * uView * vec4(aPos, 1.0);
//}
//
//    #type fragment
//    #version 330 core
//uniform float uAspect;
//
//out vec4 color;
//
//in vec3 fPos;
//in vec4 fColor;
//in vec2 fTexCoords;
//in float fTexSlot;
//
//uniform sampler2D TEX_1;
//uniform sampler2D TEX_2;
//uniform sampler2D TEX_3;
//uniform sampler2D TEX_4;
//uniform sampler2D TEX_5;
//uniform sampler2D TEX_6;
//uniform sampler2D TEX_7;
//
//void main()
//{
//    vec4 texColor = vec4(1, 1, 1, 1);
//    if (fTexSlot > 0) {
//        if (fTexSlot < 2) {
//            texColor = texture(TEX_1, fTexCoords);
//        } else if (fTexSlot < 3) {
//            texColor = texture(TEX_2, fTexCoords);
//        } else if (fTexSlot < 4) {
//            texColor = texture(TEX_3, fTexCoords);
//        } else if (fTexSlot < 5) {
//            texColor = texture(TEX_4, fTexCoords);
//        } else if (fTexSlot < 6) {
//            texColor = texture(TEX_5, fTexCoords);
//        } else if (fTexSlot < 7) {
//            texColor = texture(TEX_6, fTexCoords);
//        } else if (fTexSlot < 8) {
//            texColor = texture(TEX_7, fTexCoords);
//        }
//    }
//
//    if (fTexSlot > 0) {
//        color = texColor * fColor;
//    } else {
//        color = fColor;
//    }
//}
