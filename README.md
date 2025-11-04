# Sticks 


## Description

I got the idea for this while I was playing Sticks with my friend. It's supposed to take a Sticks configuration and figure out all possible paths that can lead to the first player (the one one the left) winning, but it kind of doesn't do that? Obviously, I can't make it list every single path, but I tried to make it not perform anything on a configuration if it had already been visited, which means that no configuration will be seen twice in a path. This is probably why my code doesn't work. I don't know. Please help me.

Anyways, I got the idea for this while playing Sticks with my friend. I realized that, despite taking AP Computer Science, I hadn't written anything in a very long time. I thought that I should take some time to practice Java. At the time, I was completely unaware of the existence of Graph Theory and depth-first search, but I learned about it shortly after writing this program.


## Use

You can download the build from Releases. You can run it using: 
```batch
java -jar sticks.jar
```

Also, make sure to have Java 21 or later. 

If you just type nothing and press the "enter" key, you will get the starting configuration as the actual default starting configuration in sticks. If you actually give it a configuration, such as "1,2,4,2," it will start on that configuration. The first two numbers are the hands of the player that is meant to win (and the player that will be attacked first), while the latter two numbers represent the hands of the losing player. Typing " sticks" after this will give a representation with each "|" standing for a finger. 