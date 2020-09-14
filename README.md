# PokéSolve

![Application video](https://media.giphy.com/media/MCXcBlkO5HGY61Mv5x/giphy.gif)

Practicing Android app development and testing!

Choose your starter Pokémon, give it a name, and then solve increasingly difficult math problems in order to evolve it!

<h2>Code, Challenges</h2>
<h3>Passing data between activities</h3>
Initially I was simply passing a string between the ChoiceActivity and the MainActivity, where it would hit a switch statement to select which Pokemon object to create.
However after refactoring the Pokemon class, this approach would've undone a lot of my good work. I found Intent cannot handle objects unless they were serialised, so I implemented it for both the Pokemon and the PokemonStatistics classes.

<h3>Animated PNGs for Sprites</h3>
penfeizhou's APNG library was amazing for this, and it's my first time using a 3rd party library. So easy!
This added a lot more character to what was essentially a static screen. I also implemented an ImageView ontop of the sprite to animate effects when the Pokemon evolved.

<h3>Automated Testing</h3>
I've been using Appium and TestNG for automated testing of this project on Amazon Device Farm. This was honestly a nightmare at the start, as dealing with movement between Activities was really difficult. I especially faced challenges with the Alert Modal Dialog pop-ups. After implementing WebDriverWait and the shamefully bruteforce sleep(), I overcame a lot of the missing element errors.
It's also really fun watching the automation suite solve the math challenges, and absolutely puts me to shame.

<h2>To-do</h2>

- [x] Refactor all View IDs
- [x] Implement polymorphism (removing a large number of switch statements), generify classes and refactor methods
- [ ] Add multiplication and division math challenges (will have to change from int to handle this)
- [ ] Learn and understand how to use the Android Activity Lifecycle

![Application image](screenshot_1.png)
![Application image](screenshot_2.png)
![Application image](screenshot_3.png)
