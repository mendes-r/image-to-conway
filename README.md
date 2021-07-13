# Image-to-Conway
Turn an image into an initial conway's game of life grid... and start living

## Introduction
The idea behind this project is to transforme any jpeg image into a starting grid for the famous Conway´s Game of Life.
After this transformation - the implementation of a filter - it will be possible to play the game according to Conways' game.
The number of iterations can be controlled or played indefinitly.
Another functionatity would be to rewind the played game to its original configuration.
This possibilty turns this project in a kind of encryption software... a simple one.

## Technology

A C program should use a thresholding image processing algorithm to convert normal images in binary ones.

After that we should be able to use a program written in Java to use the processed image and turn it in to a Conway's Game of Life grid... and play it.

A kind of frontend is still in debate.

## Requirements

The following requirements will give a first hint for the type of user stories and functionalities that need to be answered

- As a user, I want to be able to upload an jpg image.
- As a user, I want to be able to upload any type of image proportion.
- As a user, I want to start the game.
- As a user, I want to be able to stop the game at any moment.
- As a user, I want to be able to rewind the iterations and return to the original image.
- As a user, I want to be able to save the image that results of any given number of iterations.

## Possible future new functionalities

As already mentioned, this project could be turned into a web-application.
Another interesting possibility could be the use of this rearrangement of the pixels to something that resembles an image compression software.

Compressing an already compressed file - jpg - can be a strange thing to do, but for the sake of learning it could be tried.

At a first glance, this could be done by analyzing each iteration and see how much less space it takes after using a simple compression method. 

No information should be lost with the running of the Conway's engine. A pixel in contact with the edges should be somehow recorded.
