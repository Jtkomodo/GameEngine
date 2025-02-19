<h1>My Game Engine</h1>
<p>This is a Game engine I was working on to self-teach how to work with bigger projects.
I worked on this before I went to college so I have learned a lot since then esescially in the the areas of how to make more readable code.</p>
<img src="https://github.com/user-attachments/assets/ae4cc966-b172-4cc4-98e9-d99bceb13dcb">


<h2>Intent of this project</h2>
The original plan was to do it with a friend, however he got too caught up with college.
This taught me a lot about programming in general and I remade the project a few times because of
the mistakes I learned along the way. If I were to make this project again I would add a lot more comments
to the code to allow it to be better read. This project is meant to be changed into a Java library that
would be included to create a game. It will fully make a runnable OpenGL window just by including it, making
a start class that extends the Game object, and initializes the object through the main(). It has two functions
that need to be overridden. I created an example of how this would work in the folder <b>/src/main/java/test/Start.java</b>
<dl>
<dt>start()</dt>
<dd>This is where all the startup code would be placed anything that needs to be initiated before the program
starts would be placed here.</dd>

<dt>GameLoop()</dt>
<dd>This is where the game loop actually happens. Anything that is run every frame goes here.<dd>
</dl>
<p>That is all you need to include for a simple window to be created. And takes out a ton of the code required
to write just to get to a GLFW window.</p>

<h2>Things I would change</h2>
<p>This was my first attempt at implementing an Entity Component system(ECS). The idea is to be able to create a simple entity and 
add only the components needed for the functionality of what is being looked for. These components can also be written in the
Scripting language Groovy this is so that when designing a Game you can test out functionality simply by reloading the script.
With an ECS I also wanted to be able to change variables at will in the program so I created Some Variable classes, however, I wanted
it to also check for syntax errors before the program was run so I included generics so the IDE would detect any Variable types that
are incorrect. Looking back I feel this was a good idea for a first attempt, but could use a lot of work and might still lead to bugs in code that is hard
to detect sense Variables can be changed anywhere in code, and would consider another method of doing this. It also led to some over-engineered code
that I am not a big fan of, but taught me a ton as well which was the purpose of the project.</p>

<p>While I would change a lot I am very happy with a lot of its functionality to do sound, draw sprites, draw strings to screen and it all being done pretty
much from scratch. My future plans were to add UI functionality, however, school picked up, and was not able to finish my ideas.</p>


