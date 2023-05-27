import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Holds code for the lava animation.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class Lava extends Actor
{
    /**
     * Act - do whatever the Lava wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        int animationSpeed = 300; //the higher the number the lower the time between frames (slower speed)
         if (System.currentTimeMillis()/animationSpeed % 3 == 0) {
            setImage("Lava1.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 3 == 1){
            setImage("Lava2.png");
        }
        if (System.currentTimeMillis()/animationSpeed % 3 == 2){
            setImage("Lava3.png");
        } 
    }    
}





