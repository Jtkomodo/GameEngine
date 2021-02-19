#version 120

uniform sampler2D sampler;
varying vec2 tex;
uniform vec4 color;


void main(){    
 
gl_FragColor=color*texture2D(sampler,tex);


}
