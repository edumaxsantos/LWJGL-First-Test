#version 330 core

in vec3 position;
in vec3 color;
in vec2 textureCoord;

out vec3 passColor;
out vec2 passTextureCoord;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
//uniform float scale;

void main() {
    gl_Position = projection * view * model * vec4(position, 1.0);
    //gl_Position = vec4(position, 1.0) * vec4(scale, scale, scale, 1);
    passColor = color;
    passTextureCoord = textureCoord;
    //color = vec3(0, 0.5, 0.4);
}
