import greenfoot.*;
import java.util.List;

//this message should be updated in pull
/**
 * This holds the code for the main player (controls, animation, and collision).
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */
public class Link extends Actor
{
    int xmove=0;
    int xmove2=0;
    int ymove=0;
    int ymove2=0;
    int scroll=0;
    int scrollTimer=0;
    int health = 10;
    int numMines = 5;
    private boolean won = false;
    private boolean allowDamage = true;
    private long collisionTimer;
    public void act() 
    {
        //Methods
        try{
            ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).setLocation(getX(),getY());
        }catch(IndexOutOfBoundsException e){}
        if (scroll==0){
            basicMoving();
            collisionDetection();
            if (getX()>=getWorld().getWidth()-1){scroll=1;}
            if (getX()<=0){scroll=2;}
            if (getY()>=getWorld().getHeight()-1){scroll=3;}
            if (getY()<=0){scroll=4;}
        }
        if (scroll==0){
            setLocation(getX()+xmove+xmove2,getY()+ymove+ymove2);
        }else {scroll();
        }
        enemyCollision();
        lavaDamage();
        checkDead();
        spawnKnife();
        levelOneOver();
        levelTwoOver();
    }

    public void subtractHealth(int hp) {
        health = health - hp;
        Greenfoot.playSound("Oof.mp3");
        updateHealth();
    }
    
    public void subtractMines() {
        numMines = numMines - 1;
        updateMineInventory();
    }
    
    public void spawnKnife() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null) && numMines > 0) {
            Greenfoot.playSound("Beep.mp3");
            getWorld().addObject(new Knife(),getX(),getY());
            Actor object = getOneObjectAtOffset(0,0,Knife.class);
            subtractMines();
        }
    }
    
    public void checkDead() {
        if (health <= 0) {
            Greenfoot.stop();
            Greenfoot.setWorld(new DeathScreen());
        }
    }

    public void updateHealth() {
        getWorld().removeObjects(getWorld().getObjects(Heart.class)); //deletes all hearts to create a new set
        for (int i = 0; i < health; i++) {
            heart(660-i*30,460);
        }
    }
    
    public void updateMineInventory() {
        for (int i = 0; i < 5; i++) { //deletes all mines to create a new set
            getWorld().removeObjects(getWorld().getObjectsAt(20+i*30, 460, Knife.class));
        }
        for (int i = 0; i < numMines; i++) {
            knife(20+i*30,460);
        }
    }

    public void scroll(){
        if (scrollTimer==0){((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeOut();}
        scrollTimer++;
        if(scrollTimer==30){
            if (scroll==1){xmove=-9; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("right");}
            if (scroll==2){xmove=9; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("left");}
            if (scroll==3){xmove=0; ymove=-9; ((RandomlyGeneratingDungeon)getWorld()).scroll("down");}
            if (scroll==4){xmove=0; ymove=9; ((RandomlyGeneratingDungeon)getWorld()).scroll("up");}
        }else if(scrollTimer>30){
            setLocation(getX()+xmove,getY()+ymove);
            if (scroll==1&&getX()<=30){scroll=0;}
            if (scroll==2&&getX()>=getWorld().getWidth()-30){scroll=0;}
            if (scroll==3&&getY()<=30){scroll=0;}
            if (scroll==4&&getY()>=getWorld().getHeight()-30){scroll=0;}
            if (scroll==0){scrollTimer=0; ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeIn();}
        }
    }

    public void basicMoving()
    {
        if (scroll!=0)return;
        int m=3; //Rate of cells that will be traveled
        //Change movement
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("s")) {
            animation(); //if any of the control keys are being pressed that means the character is moving and should be animated
        } else {
            setImage("Link.png");
        }
        if (Greenfoot.isKeyDown("a")&&ymove==0){xmove=-m; setRotation(90); animation();}
        if (Greenfoot.isKeyDown("d")&&ymove==0){xmove=m;  setRotation(270); animation();}
        if (Greenfoot.isKeyDown("w")&&xmove==0){ymove=-m; setRotation(180); animation();}
        if (Greenfoot.isKeyDown("s")&&xmove==0){ymove=m;  setRotation(0); animation();}
        if (Greenfoot.isKeyDown("s") && Greenfoot.isKeyDown("d")){ //when the character is going to the bottom right
            ymove=m;
            xmove=m;
            setRotation(315);
        }
        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("w")){ //when the character going to the top left
            ymove=-m;
            xmove=-m;
            setRotation(135);
        }
        if (Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("d")){ //when the character is going to the top right
            ymove=-m;
            xmove=m;
            setRotation(225);
        }
        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("s")){ //when the character is going to the bottom left
            ymove=m;
            xmove=-m;
            setRotation(45);
        } 
        if (! Greenfoot.isKeyDown("a")&&! Greenfoot.isKeyDown("d")){xmove=0;}
        if (! Greenfoot.isKeyDown("w")&&! Greenfoot.isKeyDown("s")){ymove=0;}
    }
    static String direction="up";
    public void animation() {

        int animationSpeed = 150; //(the higher the number the lower the speed)

        if (System.currentTimeMillis()/animationSpeed % 10 == 1) {
            
            setImage("2.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 2){
           
            setImage("3.png");
        }
        if (System.currentTimeMillis()/animationSpeed % 10 == 3){
           
            setImage("4.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 4){
            
            setImage("5.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 5){
           
            setImage("6.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 6){
           
            setImage("7.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 7){
          
            setImage("8.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 8){
           
            setImage("9.png");
        } 
        if (System.currentTimeMillis()/animationSpeed % 10 == 9){

            setImage("10.png");
        }

    }
    Class[] objects = {Wall.class,Block.class};
    int collisionAmount=0;
    public void collisionDetection()
    {
        while (collisionAmount<objects.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+2,objects[collisionAmount]);
                if (object!=null&&ymove>0){i=-getImage().getWidth()/2+2; ymove=0; setLocation(getX(),object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);}
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                if (object!=null&&ymove<0){i=-getImage().getWidth()/2+2; ymove=0; setLocation(getX(),object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);}
            }
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3, i,objects[collisionAmount]);
                if (object!=null&&xmove<0){i=-getImage().getHeight()/2+2; xmove=0; setLocation(object.getX()+object.getImage().getWidth()/2+getImage().getWidth()/2,getY());}
            }
            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, i,objects[collisionAmount]);
                if (object!=null&&xmove>0){i=-getImage().getHeight()/2+2; xmove=0; setLocation(object.getX()-object.getImage().getWidth()/2-getImage().getWidth()/2,getY());}
            }
            collisionAmount++;
        }
        collisionAmount=0;
    }

    public void lavaDamage() {
        Actor object = getOneObjectAtOffset(0,0,Lava.class);
        if (object!=null) {

            if (System.currentTimeMillis()/1000 - collisionTimer > 0.01) {
                allowDamage = true;
            }

        }
        if (object!=null && allowDamage) {
            subtractHealth(5);
            allowDamage = false;
            collisionTimer = System.currentTimeMillis()/1000;
        }
    }

    public void enemyCollision() {

        //Down check

        for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
            Actor objectA = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlOne.class);
            Actor objectB = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlTwo.class);
            if (objectA!=null || objectB != null) {
                if (System.currentTimeMillis()/1000 - collisionTimer > 1) {
                    allowDamage = true;
                }
            }
            if (objectA!=null && allowDamage){
                subtractHealth(1);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
            if (objectB!=null && allowDamage){
                subtractHealth(3);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
        }

        //Up check

        for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
            Actor objectA = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlOne.class);
            Actor objectB = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlTwo.class);
            if (objectA!=null && allowDamage){
                subtractHealth(1);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
            if (objectB!=null && allowDamage){
                subtractHealth(3);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
        }

        //Left check

        for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
            Actor objectA = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlOne.class);
            Actor objectB = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlTwo.class);
            if (objectA!=null && allowDamage){
                subtractHealth(1);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
            if (objectB!=null && allowDamage){
                subtractHealth(3);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
        }

        //Right check

        for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
            Actor objectA = getOneObjectAtOffset(i, getImage().getHeight()/2+2,Enemies.class);
            Actor objectB = getOneObjectAtOffset(i, getImage().getHeight()/2+2,EnemiesLvlTwo.class);
            if (objectA!=null && allowDamage){
                subtractHealth(1);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
            if (objectB!=null && allowDamage){
                subtractHealth(3);
                allowDamage = false;
                collisionTimer = System.currentTimeMillis()/1000;
            }
        }

    }
    
    private void heart(int x, int y){
        getWorld().addObject(new Heart(),x,y);
    }
    
    private void knife(int x, int y){
        getWorld().addObject(new Knife(),x,y);
    }
    private void levelOneOver() { //if level one is over
        if(EnemiesLvlOne.enemyDead) { //if the enemy is dead
            Greenfoot.setWorld(new RandomlyGeneratingDungeon2()); //generates the second room
            EnemiesLvlOne.enemyDead = false;
        }
    }
    private void levelTwoOver() {
        if(EnemiesLvlTwo.enemyDeadTwo) {
            Greenfoot.setWorld(new TitleScreen());
            EnemiesLvlTwo.enemyDeadTwo = false;
        }
    }
}
