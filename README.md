# Introduction 
It is (one / two) player-game in which each clown carry two stacks of shapes, and

there are a set of colored shapes queues that end up falling down. Players try to

catch the falling plates, if they manage to collect three consecutive shapes of the

same color their score increases until the timer is over. <br>


# User Guide
1. User should first select whether to play a new game or load a previous game.

2. After selecting New game, user should select whether to play one / two player(s). <br>

3. User can select from options menu timer of the game, shapes to play with and levels of difficulty. <br>

4. For a one-player game user can play with keyboard or mouse but for two-
players game only one user can play with mouse or keyboard. <br>

5. While playing, user can pause the game and select whether to save, resume or end the game. <br>


# User Interface
## Main Menu 

<a href="https://imgbb.com/"><img src="https://image.ibb.co/ksDzEa/Screenshot_444.png" alt="Screenshot_444" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>ebay photo hosting</a><br />

<a href="https://imgbb.com/"><img src="https://image.ibb.co/ef5knv/Screenshot_445.png" alt="Screenshot_445" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>ebay photo hosting</a><br />

## Game Scene
<a href="https://ibb.co/ixZpEa"><img src="https://preview.ibb.co/jFiS0F/Screenshot_446.png" alt="Screenshot_446" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>ebay photo hosting</a><br />

# Code Design and Implementation

## 1- Design Patterns:

**a- MVC (Model-View-Controller):**

  - It is used to separate the internal representation of data (Model) and the view
     components (GUI) .Controllers acts as an interface between the model and the
     view components to controls the data flow into model object and updates the
     view whenever data changes.

Model:- Projectiles classes –Projectiles Pool-Projectiles States -
        characters classes - Score - Level – file handling.....etc

View :- Main Menu scene - Game scene.

Controller:- Main Controller – View Controller – Saving Controller
             and game controller.

- When the game is initialized Main Controller initializes all the controllers and they
  initialize their helpers.
-  Main Controller is the only one that can deal with the model.
- View Controller is responsible for the interaction of the view with the ...model
  through the main controller.
- Game Controller is responsible for the game as (dropping new projectiles –
  removing projectiles – checking whether the player caught a projectile and so on.
- Saving Controller (Originator) is responsible for saving and loading operations.<br>

**b -Objects Pool Design Pattern :-**

It is from the creational design patterns used to reuse objects.
It is used for creating projectiles. When we need a new
projectile the pool checks whether the available list is empty or not, It
creates new projectile (dynamically loaded) when the available list is
empty or it allows the reuse of projectiles when they are needed. When
the projectile is not needed we release it by calling the release method in
the pool and the pool returns it to the available list. <br>

**c-Iterator design pattern :-**

It is from the structural design patterns, used to access the elements of a
collection object in sequential manner without any need to know its
underlying representation.
It is used to iterate over the character’s hand which contains
his gains of projectiles. <br>

**d- Dynamic Linkage Design Pattern :-**

It is from the structural design patterns, allows a program, upon request,
to load and use arbitrary classes that implement a known interface at
runtime.
It is used to load the selected projectiles dynamically when
needed so as to support code extendibility when adding new classes. <br>

**e-Factory Design Pattern :-**

It is from the creational design patterns, used to create object
without exposing the creation logic to the client and refer to
newly created object using a common interface.
We used this pattern to know the color of the randomly generated
projectile through our color factory. <br>

**f-State Design Pattern :-**

It is from the behavioral design patterns, used when a behavior of an object
depends on its state.
It is used to determine the motion of the projectiles according to
its state.
Projectiles states are :- On shelf state - Flying state – Caught state –
Unused state. <br>

**g-Strategy Design Pattern :-**

It is from the behavioral design patterns, used when an object’s behavior or
algorithm can be changed at run time.
It is used to determine the velocity of the projectiles and the
rate of its generation according to user selection of the level of difficulty at
runtime. <br>

**h-Snapshot Design Pattern :-**

It is from the behavioral design patterns, Used to save objects and restore
its previous state.
It is used in saving / loading operations as we save timer , level
of difficulty , projectiles list and players list so as to restore them whenever
needed.
All stored classes must implement java.io.serializable interface.
required to save fields are stored in a memento object which is then
saved in file in a serialized format. <br>

**I -Observer Design Pattern :-**

It is from the behavioral design patterns, used when one object is
modified, its dependent objects are to be notified automatically
It is used when the user wants to save or load, saving
originator (observable) notifies the Game controller (observer) to
either bring or take the data from the saving originator. <br>

**j-Singleton Design Pattern :-**

It is from the creational design patterns, used when we need only one
instance at run time.
It is used in creating projectiles pool – main controller – game
controller - saving originator and view controller.<br>

**k- Visitor Design Pattern :-**

It is from the behavioral design patterns, it changes the executing
algorithm of an element class.
It is used when applying random generation on the
projectiles and characters , the entity accepts the visitor to randomize on
pictures and creates random shapes. <br>

**l -Adapter Design Pattern :-**

It is from the structural design patterns, used as a bridge between two
incompatible interfaces.
It is used to convert from center point of the image to its top left
so as to be drawn as javafx requires top left points. <br>
