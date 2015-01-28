# GunGunShoot
A Rock Paper Scissors type game. To run this program outside of an IDE, use the following commands from the project directory:

1. mvn clean install
2. java -cp target/ggs-0.0.1-SNAPSHOT.jar com.drem.games.ggs.App
 
##Rules
* Everyone starts with zero bullets. This is to weed out the people who aren’t paying attention to the rules. The logical first move of every game is to Reload.
* The objective is to shoot the opponent while they are vulnerable.
    * **If you shoot with no bullets, you are vulnerable.** 
    * **If your shield has taken too much damage, you are vulnerable.**
    * **If you are reloading, you are vulnerable.**
* In case of a stalemate, the person with the most bullets wins. If both players have the same amount of bullets, the game is a draw.

##Actions
* Block - Shield takes one damage if the opponent has shot a bullet.
* Shoot - Shoot a bullet if you have any. If not, you are vulnerable. 
* Reload - Gives you one bullet. If the opponent shoots, you are dead.

##Guns
* Pistol(1)
* Shotgun(3)
* Machine Gun(5)
* Rocket Launcher(10)
