import greenfoot.*;
import java.util.List;

/**
 * This is level one of the game.
 * 
 * @authors Rayhan, Aruyan, Ibrahim
 * @version 2023/01/22
 */

public class RandomlyGeneratingDungeon extends World
{   
    /**
     * Constructor for objects of class RandomlyGeneratingDungeon.
     * 
     */
    Wall sideWallOne; 
    Wall sideWallTwo; 
    public RandomlyGeneratingDungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(680, 480, 1, false); 
        sideWallOne = new Wall(40,getHeight()); 
        sideWallTwo = new Wall(40,getHeight());
        addObject(new Link(),getWidth()/2,getHeight()/2+20);
        addObject(new FadeOverlay(),getWidth()/2,getHeight()/2);
        addObject(new Wall(getWidth()*2,40),getWidth(),20);
        addObject(new Wall(getWidth()*2,40),getWidth(),getHeight()-20);
        addObject(new Wall(40,getHeight()),20,getHeight()/2);
        addObject(new Wall(40,getHeight()),getWidth()*3-20,getHeight()/2);
        addObject(sideWallOne,getWidth()-20,getHeight()/2);
        addObject(sideWallTwo,getWidth()*2-20,getHeight()/2);
        addObject(new EnemiesLvlOne(),(getWidth()/4)+400,getHeight()/4+10);
        //addObject(new EnemiesLvlTwo(),6 *getWidth()/4,getHeight()/4+10);
        generateHearts(10); //generates the player's hearts
        generateMineInventory(5); //adds 5 mines in the player's inventory
        prisonRoom();
        paintOrder();
    }
      public Wall getSideWallOne(){
       return sideWallOne;  
    }
    public Wall getSideWallTwo(){
       return sideWallTwo;  
    }

    Class[] all = {Wall.class,Key.class,Block.class,Lava.class,Water.class,EnemiesLvlOne.class,Knife.class,EnemiesLvlTwo.class};
    public void scroll(String direction){
        int v=0;
        int h=0;
        if (direction.equals("left"))h=getWidth();
        if (direction.equals("right"))h=-getWidth();
        if (direction.equals("up"))v=getHeight();
        if (direction.equals("down"))v=-getHeight();
        int a=0;
        while (a<all.length){
            List object = getObjects(all[a]);
            if (! object.isEmpty()){
                for (int i=0; i<object.size(); i++){
                    Actor Object = (Actor) object.get(i);
                    Object.setLocation(Object.getX()+h,Object.getY()+v);
                }
            }
            a++;
        }
    }
    
    public void paintOrder(){
        setPaintOrder(Heart.class,Link.class,Knife.class,HealthBar.class,FadeOverlay.class,Wall.class,Key.class,Block.class,Lava.class,Water.class);
    }
    
    public void generateHearts(int health) {
        healthBar(540,460); //adds a black background bar
        for (int i = 0; i < health; i++) {
            heart(660-i*30,460); //places a heart inside the bar
            
        }
    }
    
    public void generateMineInventory(int numMines) {
        healthBar(0,460);
        for (int i = 0; i < numMines; i++) {
            knife(20+i*30,460); //adds the mines in the inventory bar
        }
    }
    
    public void act(){
        paintOrder();
        if (tileset==0)setBackground(new GreenfootImage("GreenTile.png"));
        if (tileset==1)setBackground(new GreenfootImage("BlueTile.png"));
        if (tileset==2)setBackground(new GreenfootImage("AquaTile.png"));
        if (tileset==3)setBackground(new GreenfootImage("YellowTile.png"));
        if (tileset==4)setBackground(new GreenfootImage("GreyTile.png"));
    }
    
    public void prisonRoom() {
        for (int i = 1; i <= 8; i++) {
            block(10,i,"IronBars.png"); //creates a wall of blocks
        }
        //creates a lava pool which the player has to move blocks onto to escape their cell
        for (int i = 1; i <=12; i++) {
            lava(i,10);
        }
        for (int i = 1; i <=12; i++) {
            lava(i,9);
        }
        
        //all the movable blocks in the prison room
        block(2,7,true,-1);
        block(5,7,true,-1);
        block(8,7,true,-1);
        block(2,4,true,-1);
        block(5,4,true,-1);
        block(8,4,true,-1);
    }
    
    /**
    Tile Sets
    ---------
    0 = Green
    1 = Blue
    2 = Aqua
    3 = Yellow
    4 = Grey
    */
    int tileset = 4;
    public void changeTileSet(int i){
        tileset=i;
    }
    
    //Dungeon Manipulation methods
    
    public void nextRoom(){
        
    }
    public void generateDungeonRoom(){
        
    }
    public void clearDungeonRoom(){
        Class[] objects = {Block.class,Wall.class}; //List of objects that will be cleared
        int object = 0;
        int i = 0;
        while (object<objects.length){
            List Object = getObjects(objects[object]);
            if (! Object.isEmpty() && (Actor) Object.get(0)!=null){
                while (i<Object.size()){
                    removeObject((Actor) Object.get(i));
                    i++;
                }
            }
            object++;
            i=0;
        }
    }
    public void generateDungeonEnemies(){
        
    }
    
    //Dungeon Tile Methods
    
    public void block(int x, int y, boolean movable, int event, int keypos){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(x,y,movable,event,keypos),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, boolean movable, int event){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(x,y,movable,event,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, boolean movable){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(x,y,movable,0,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(x,y,false,0,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, String image){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(x,y,image),x*40+20,getHeight()-y*40-20);
    }
    //Liquids
    public void lava(int x, int y){
        addObject(new Lava(),x*40+20,getHeight()-y*40-20);
    }
    public void water(int x, int y){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: WATER AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Water(),x*40+20,getHeight()-y*40-20);
    }
    private void heart(int x, int y){
        addObject(new Heart(),x,y);
    }
    private void healthBar(int x, int y){
        addObject(new HealthBar(),x,y);
    }
    private void knife(int x, int y) {
        addObject(new Knife(),x,y);
    }
    
}





