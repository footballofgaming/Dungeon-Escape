import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemy that appears in level two aka the boss enemy.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class EnemiesLvlTwo extends Enemies
{
    /**
     * Act - do whatever the EnemiesLvlTwo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int xSpeed = 5;
    public int ySpeed = 5;
    public int health = 5;
    boolean enemyDead = false; //to notify whether this enemy is dead or not
public void act()
    {
        setLocation(getX()+xSpeed,getY()+ySpeed); 
        collisionDetection();
        hitEdge();
        if (health <= 0) {
            Greenfoot.setWorld(new WinScreen());
        }
        }
        
    public void hitEdge() { //to tell if hit the edge and bounce the boss
        World myWorld = getWorld(); 
        if(getY() <= 40) {
            Greenfoot.playSound("boing.mp3");
            ySpeed = ySpeed * -1;
        }
        if (getX() <= 40) {
            Greenfoot.playSound("boing.mp3");
            xSpeed = xSpeed * -1;
        }
        if (getX() >= myWorld.getWidth()-40) {
            Greenfoot.playSound("boing.mp3");
            xSpeed = xSpeed * -1;
        }
        if (getY() >= myWorld.getHeight()-100) {
            Greenfoot.playSound("boing.mp3");
            ySpeed = ySpeed * -1;
        }
    }
    
    /*public void bounceEdge() {
        String edge = hitEdge();
        if(edge.equals("top") || edge.equals("bottom")) {
            ySpeed = ySpeed * -1;
            xSpeed = xSpeed * -1;
        } else if (edge.equals("left") || edge.equals("right")) {
            xSpeed = xSpeed * -1;
        }
    }*/
        
    public void collisionDetection()
    {
        
        Wall sideWall = getSideWall();

        while (collisionAmount<objects.length){
            /*//Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+2,objects[collisionAmount]);
                if (object!=null&&ymove>0){i=-getImage().getWidth()/2+2; ymove=-1; setLocation(getX(),object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);}
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                if (object!=null&&ymove<0){i=-getImage().getWidth()/2+2; ymove=1; setLocation(getX(),object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);}
            }*/
            collisionAmount++;

            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+2,Knife.class);
                if (object!=null&&ymove>0){
                    getWorld().removeObject(object);
                    getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    setImage("MrP.png");
                    Greenfoot.playSound("Explosion.mp3");
                    //getWorld().removeObject(this);
                    health = health -1;
                    return;
                }
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,Knife.class);
                if (object!=null&&ymove<0){
                    getWorld().removeObject(object);
                    //getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    setImage("MrP.png");
                    Greenfoot.playSound("Explosion.mp3");
                    //getWorld().removeObject(this);
                    health = health -1;
                    return;
                }
            }
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3,i,Knife.class);
                if (object!=null&&ymove<0){
                    getWorld().removeObject(object);
                    //getWorld().removeObject(sideWall);
                    setImage("Explosion.png");
                    Greenfoot.delay(20);
                    setImage("MrP.png");
                    Greenfoot.playSound("Explosion.mp3");
                    //getWorld().removeObject(this);
                    health = health -1;
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
                    setImage("MrP.png");
                    Greenfoot.playSound("Explosion.mp3");
                    //getWorld().removeObject(this);
                    health = health -1;
                    return;
                }
            }
        }
        collisionAmount=0;
    }
}
