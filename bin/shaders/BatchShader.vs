#version 120


attribute vec3  textureCoords;
attribute vec2 vertices;
attribute vec4 colors;
attribute vec3 translation;
uniform mat4 projection;
uniform mat4 UIProjection;
uniform mat4 rts;
mat4 matrix;
mat4 trans;


varying vec3 tex;//allows this variable to be sent to the fragment shader
varying vec4 color;
void main(){
tex=textureCoords;
color=colors;
trans=mat4(
    1,0,0,translation.x,
    0,1,0,translation.y,
    0,0,1,0,
    0,0,0,1
);
trans=transpose(trans);
mat4 matp;

if(translation.z==1){
matp=UIProjection;
}
else{
matp=projection;
}
matrix=matp*rts*trans;
  gl_Position=matrix*vec4(vertices,0,1);

} 

