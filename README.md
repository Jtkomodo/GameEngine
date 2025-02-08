This is a Game engine I was working on to self teach how to work with bigger projects.
I worked on this before I went to college so a lot I have leared sense then.

Original plan was to do it with a friend ,howvever he got too caught up with college.
This taught me alot about programmign in general and I remade the project a few times because of
the mistakes I learned along the way. If I were to make this project again I would add a lot more comments
to the code to allow it to be better read. This project is meant to be changed into a java library that
would be included to create a game it will fully make a runnable opengl window jsut by including it,making
a start class that extends the Game object, and intiializes the object through main(). It has two functions
that need to be overidden. I created a examaple of how this would work in the folder /src/main/java/test/Start.java

start()
This is where all the startup code would be placed anything that needs to be intialized before the program
starts would be placed here. 

GameLoop()
This is where the game loop actually happens. Anything that is run every frame goes here.

That is all you need to include for a simple window to be created. And takes out a ton of the code required
to write just to get to a GLFW window.

This is also my first attempt at implementing a Entity Compent system(ECS). The idea is to be able to create a simple entity and 
add only the compentes needed for the functionality of what is being looked for. These compentes can also be written in the
Scripting language groovy this is so that when designeing a Game you can test out fucntionallity simply by reloading the script.
With a ECS I also wanted to be able to change variables at will in the program so I created Some Variable classes ,howver I wanted
it to also check for syntax erors before the program was ran so I included generics so the IDE will detect any Variable types that
are incorrect. Looking back I feel this was a good idea for a first attempt ,but could use a lot of work and might still lead to bugs in code hard
to detect sense Variables can be changed anywhere in code and would consider another method of doing this. It also lead to some over enginerred code
that I am not a big fan of ,but taught me a ton as well which was the purpose of the project.

While I would change a lot I am very happy with a lot of it's functionallity to do sound,draw sprites, draw strings to screen and it all being done pretty
much from scratch. My future plans were to add UI functionality ,however school picked up and was not able to finsih my ideas.

