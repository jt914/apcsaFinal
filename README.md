This is a simple traffic simulator built entirely in Java.
It uses a common MVC pattern, and is developed using JFrame.

Credit:
Joseph Tso and Quan Huynh

How I am thinking of stucturing the project right now:
We have a timer in the Main window essentially updating every 10 milliseconds
We store arrays representing the directions of the different cars in the Constants file, N E S and W
Then we have the actual logic in the car class


Have a timer representing how long they have been waiting at the stop sign for. Have to be first in the array
Update car method in the main window called in action listener
Passes boolean on whether to start the timer of how long the car has been waiting
compares the times of how long everyone has been waiting for


TO DO IF TIME PERMITS:
Make the translate method better by calculating the radius by which the car would turn, since cars have to slow down and change direction and dont just do it immediately




LOGIC SO FAR:
TO Do FOR TOMORROW:

Main if statement is betweeen whether the intersection is occupied or not.
If it is occupied, then just basically make all the cars that need to stop, so if theyre at the stop sign x and y, or if they are
behind the lead car, then make them stop.

Otherwise, keep going

Note: When checking for which car is first car in a list to not go through the intersection yet, go through the list and check which cars have the "is finished" boolean thingy