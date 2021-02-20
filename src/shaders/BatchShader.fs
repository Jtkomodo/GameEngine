#version 120

uniform sampler2D sampler[32];
varying vec3 tex;
varying vec4 color;


void main(){    
 
int index=int(tex.z);
gl_FragColor=color*texture2D(sampler[index],tex.xy);


}
