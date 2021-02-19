#version 120


attribute vec2  textureCoords;

attribute vec2 vertices;
uniform mat4 projection;
uniform mat4 rts;
mat4 matrix;

varying vec2 tex;//allows this variable to be sent to the fragment shader

void main(){
tex=textureCoords;
matrix=projection*rts;
  gl_Position=matrix*vec4(vertices,0,1);

} 

