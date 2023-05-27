import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemy that appears in level one.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class EnemiesLvlOne extends Enemies
{
    /**
     * Act - do whatever the EnemiesLvlOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
        // Add your action code here.
    }
     public void frontAnimation() {
        int animationSpeed = 100; //the higher the number the lower the time between frames (slower speed)
        if (System.currentTimeMillis()/animationSpeed % 4 == 0) {
            setImage("1f.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 4 == 1){
            setImage("2f.png");
        }
        if (System.currentTimeMillis()/animationSpeed % 4 == 2){
            setImage("3f.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 4 == 3){
            setImage("4f.png");
        } 
    } 

    public void backAnimation() {
        int animationSpeed = 100; //the higher the number the lower the time between frames (slower speed)
        if (System.currentTimeMillis()/animationSpeed % 4 == 0) {
            setImage("1d.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 4 == 1){
            setImage("2d.png");
        }
        if (System.currentTimeMillis()/animationSpeed % 4 == 2){
            setImage("3d.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 4 == 3){
            setImage("4d.png");
        } 
    }  
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
                    enemyDead = true;
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
                    enemyDead = true;
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
                    enemyDead = true;
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
                    enemyDead = true;
                    return;
                }
            }
        }
        collisionAmount=0;
    }
    /*public Wall getSideWall(){
        RandomlyGeneratingDungeon world = (RandomlyGeneratingDungeon)getWorld(); 
        Wall sideWall = world.getSideWallOne();
        return sideWall; 
    }*/
}
