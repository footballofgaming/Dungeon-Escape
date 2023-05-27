import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that shows when the player dies.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class DeathScreen extends World
{

    /**
     * Constructor for objects of class DeathScreen.
     * 
     */
    public DeathScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setBackground(new GreenfootImage("Black.jpg"));
        showText("YOU DIED LOL", 200, 50);
        showText("PRESS SPACE TO START A NEW GAME!",200,150);
        Greenfoot.start();
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new RandomlyGeneratingDungeon());
    }
}
