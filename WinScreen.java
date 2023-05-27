import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen extends World
{

    /**
     * The screen that is displayed when you win.
     * 
     * @authors Rayhan, Aruyan, Ibrahim
     * @version 2023/01/22
     */
    public WinScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setBackground(new GreenfootImage("Black.jpg"));
        Greenfoot.playSound("yay.mp3");
        showText("CONGRATULATIONS YOU DEFEATED MR. P", 250, 50);
        showText("PRESS SPACE TO RESTART THE GAME!",200,150);
        Greenfoot.start();
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new RandomlyGeneratingDungeon());
    }
}

