# image-to-conway
Turn an image into an initial conway's game of life grid... and start living

## Introduction
The idea behind this project is to transforme any jpeg image into a starting grid for the famous Conway´s Game of Life.
After this transformation - the implementation of a filter - it will be possible to play the game according to Conways' game.
The number of iterations can be controlled or played indefinitly.
Another functionatity would be to rewind the played game to its original configuration.
This possibilty turns this project in a kind of encryption software... a simple one.

## Technology
Java will be the chosen language for the backend and frontend.
The Java Swing framework will be used.

Given the seperation between the UI and the model, a future web-application using other technologies will be always on the table. 

## Requirements
The following requirements will give a first hint for the type of user stories and functionatilties that need to be answered

- As a user, I want to be able to upload an jpge image.
- As a user, I want to be able to upload any type of image proportion.
- As a user, I want to start the game.
- As a user, I want to be able to stop the game at any moment.
- As a user, I want to be able to rewind the iterations and return to the original image.
- As a user, I want to be able to save the image that results of any given number of iterations.


##Possible future new functionalities
As already mentioned, this project could be turned into a web-application.
Another interesting possibility could be the use of this rearrengement of the pixels to something that ressembles an image compression software.
Compressing an already compressed file - jpg - can be a strange thing to do, but for the sake of learning it could be tryed.
At a first glance, this could be done by analysing each iteration and see how much less space it take after using a simple compression method.

##Open questions
Turning a jpeg into a grid of black and white pixels turns the file into something  more "raw". 
