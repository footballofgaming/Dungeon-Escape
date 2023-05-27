import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main code for the enemies movement and physics.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class Enemies extends Actor
{
    /**
     * Act - do whatever the Enemies wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /*public Enemies(){

    }
     */
    public void act()
    {

        setLocation(getX(),getY()+ymove); 
        collisionDetection();
        if (ymove > 0) {
            frontAnimation();
        }
        if (ymove < 0) {
            backAnimation();
        }
        /* The enemies gives random damages between  15-45. 
         * The level one enemies only gives out 10-20 damage max. 
         * level 2 enemies gives out 15-35 damages max
         * Level 3 enemies gives out 25-45 damage max 
         * 
         * The final boss (Mr.P) has a health of 500
         *  
         * 
         */
    }
    
    public void frontAnimation(){   
    }
    public void backAnimation(){   
    }
    public Wall getSideWall(){
        return null;
    }
    /*public static void levelOneEnemies(){
    /*    int levlOneEnemiesHealth = 50;
    GreenfootImage image = new GreenfootImage("Police.png");
    } */
    Class[] objects = {Wall.class,Lava.class,Water.class};
    int collisionAmount=0;
    int ymove =1;
    public static boolean enemyDead = false;//boolean telling if enemy is dead or alive
    public static boolean enemyDeadTwo = false;
    public void collisionDetection()
    {
        
        Wall sideWall = getSideWall();

        while (collisionAmount<objects.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+2,objects[collisionAmount]);
                if (object!=null&&ymove>0){i=-getImage().getWidth()/2+2; ymove=-1; setLocation(getX(),object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);}
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                if (object!=null&&ymove<0){i=-getImage().getWidth()/2+2; ymove=1; setLocation(getX(),object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);}
            }
            collisionAmount++;

            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+2,Knife.class);
                if (object!=null&&ymove>0){
                    getWorld().removeObject(object);
                    getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    Greenfoot.playSound("Explosion.mp3");
                    getWorld().removeObject(this);
                    return;
                }
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,Knife.class);
                if (object!=null&&ymove<0){
                    getWorld().removeObject(object);
                    getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    Greenfoot.playSound("Explosion.mp3");
                    getWorld().removeObject(this);
                    return;
                }
            }
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3,i,Knife.class);
                if (object!=null&&ymove<0){
                    getWorld().removeObject(object);
                    getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    Greenfoot.playSound("Explosion.mp3");
                    getWorld().removeObject(this);
                    return;
                }
            }
            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2,i,Knife.class);
                if (object!=null&&ymove<0){
                    getWorld().removeObject(object);

                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    Greenfoot.playSound("Explosion.mp3");
                    getWorld().removeObject(this);
                    return;
                }
            }
        }
        collisionAmount=0;
    }

   
}


