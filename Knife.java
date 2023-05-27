import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This was originally supposed to be a knife but then was changed to be a mine that explodes on impact.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class Knife extends Actor
{
    /**
     * Act - do whatever the Knife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //getWidth()/2,getHeight()/2+20
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null)) {
            int x = mouse.getX()-getWorld().getWidth()/2;
            int y = mouse.getY()-(getWorld().getHeight()/2+20);
            double angle = Math.tan(y/x);
        }
    }
}


