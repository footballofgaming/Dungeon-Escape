import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

 /**
 * The initial screen that shows.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setBackground(new GreenfootImage("Black.jpg"));
        showText("ZELDA BUT SLIGHTLY LESS BAD", 200, 50);
        showText("PRESS SPACE TO START THE GAME!",200,150);
        Greenfoot.start();
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new RandomlyGeneratingDungeon());
    }
}


