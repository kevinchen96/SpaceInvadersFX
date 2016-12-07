package gamestate;

import java.util.ArrayList;

import bullet.EnemyBullet;
import bullet.PlayerBullet;
import enemy.BasicEnemy;
import enemy.BigEnemy;
import enemy.Enemy;
import enemy.QuickEnemy;
import item.Bullet;
import item.Health;
import item.Item;
import item.Speed;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import ship.BasicShip;
import ship.Ship;
import views.PlayView;
import views.StartView;
import views.View;

/**
 * 
 * @author Kevin
 * Play State, for the actual game play. Encapsulates all the data and methods needed for
 * user play. Transitions back to new play state or main menu;
 */
public class PlayState extends GameState {
	//Lists of user bullets, enemy bullets, enemies, and items
	private ArrayList<PlayerBullet> bullets;
	private ArrayList<Enemy> entities;
	private ArrayList<Item> items;
	private ArrayList<EnemyBullet> enemyBullets;
	
	//Booleans for user states of moving, shooting, and alive.
	//Event keeps track of user key input for direction to move
	private boolean moving;
	private boolean shooting;
	private boolean alive;
	private KeyEvent event;
	
	private long start; //Tracks start time of enemy creation
	private long startShoot; //Tracks start time of user shooting
	private long startItem; //Tracks start time of item creation
	private long startRound; //Tracks start time between rounds
	
	private String next; //next state
	private double round; //rounds for "increasing difficulty" within game
	private int diff; //user chosen difficulty, 0-2 
	
	private Ship user;
	
	public PlayState(PlayView v, Ship ship, int difficulty){
		super(v);
		bullets = new ArrayList<PlayerBullet>();
		entities = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		enemyBullets = new ArrayList<EnemyBullet>();
		
		start = System.nanoTime();
		startShoot = start;
		startItem = start;
		startRound = start;
		
		moving = false;
		shooting = false;
		alive = true;
		
		round = 3.0;
		diff = difficulty;

		user = ship;
	}
	
	public void action(){
		//if alive, gameplay begins
		//series of functions to control parts of game
		if(alive){
			long time = System.nanoTime();
			roundChange(time); //changes round based on time
			moveShip(); //ship movement
			userShoot(time); //ship shooting
			createEntity(time); //create enemy
			createBuff(time); //create item
	    	clearLists(); //clear lists if out of bounds
	    	checkCollisions(); //check collisions between user bullets and enemies
	    	checkItems(); //check collisions between items and ship
	    	checkHits(); //check collisions between enemy bullets and ship
	    	createBullets(time); //create enemy bullets
	    	moveAI(); //move all entities separate from ship
		}
	}
	
	//if user is shooting, check time between shots, shoot bullet
	//shots fired every .25 seconds
	private void userShoot(long time) {
		// TODO Auto-generated method stub
		if(isShooting()){
			if((time-startShoot) > 250000000){
				((PlayView) v).shoot();
				startShoot = time;
			}
		}
	}

	//if user input indicates movement, move ship
	private void moveShip() {
		// TODO Auto-generated method stub
		if(isMoving()){
			user.moveShip(event);
		}
	}
	
	//check round times to see if round needs to be updated
	//rounds update every 15 seconds for increased difficulty
	private void roundChange(long time) {
		// TODO Auto-generated method stub
		if((time-startRound)/15 > 1000000000){
			round -= .5;
			if(round < 0){
				round = 0;
			}
			startRound = time;
		}
	}

	//check collisions bounds of ship and item to see if user got buff
	private void checkItems() {
		// TODO Auto-generated method stub
		int j = items.size();
		while(j-- > 0){
			if(user.getImage().getBoundsInParent().intersects(items.get(j).getImage().getBoundsInParent())){
				buff(items.get(j));
				((PlayView) v).remove(items.get(j).getImage());
			    items.remove(j);
			}
		}
	}

	//apply buff user, make call to view to update
	private void buff(Item temp) {
		// TODO Auto-generated method stub
		user.buff(temp);
		((PlayView) v).buff(temp);
	}

	//check time for last created buff
	//if 20 seconds have passed, create buff
	private void createBuff(long time) {
		// TODO Auto-generated method stub
		if((time-startItem)/20 > 1000000000){
			Item item = null;
			int random = (int) (Math.random()*3);
			switch(random){
				case 0:
					item = new Bullet(Math.random()*500 + 100, 0);
					break;
				case 1:
					item = new Health(Math.random()*500 + 100, 0);
					break;
				case 2:
					item = new Speed(Math.random()*500 + 100, 0);
					break;
			}
			items.add(item);
			((PlayView) v).setItem(item);
    		startItem = time;
		}
	}

	//check collisions between ship and enemy bullet. If collided, update player health, 
	//remove from list, and remove from view
	private void checkHits() {
		// TODO Auto-generated method stub
		int j = enemyBullets.size();
		while(j-- > 0){
			if(user.getImage().getBoundsInParent().intersects(enemyBullets.get(j).getImage().getBoundsInParent())){
				EnemyBullet temp = enemyBullets.get(j);
				hit(temp.getStrength());
				((PlayView) v).remove(enemyBullets.get(j).getImage());
			    enemyBullets.remove(j);
			}
		}
	}

	//move enemy bullets and items down, user bullets up, and enemies either down or diagonal
	private void moveAI() {
		// TODO Auto-generated method stub
		for(int i = 0; i < entities.size(); i++){
			((Enemy) entities.get(i)).moveDirection();
		}
		for(int i = 0; i < items.size(); i++){
			items.get(i).moveDown();
		}
		for(int i = 0; i < bullets.size(); i++){
    		bullets.get(i).moveUp();
    	}
    	for(int i = 0; i < enemyBullets.size(); i++){
    		enemyBullets.get(i).moveDown();
    	}
	}

	//creates bullet if enough time has elapsed between enemy firing
	public void createBullets(long time) {
		// TODO Auto-generated method stub
		for(int i = 0; i < entities.size(); i++){
    		if(entities.get(i).checkShotTime(time)){
    			createEnemyBullet(entities.get(i));
    			entities.get(i).setTime(time);
    		}
    	}
	}

	//checks bounds for enemy and user bullets, items, and enemies, and clears item if off screen
	public void clearLists() {
		// TODO Auto-generated method stub
		int i = entities.size();
		while(i-- > 0){
			if(entities.get(i).getImage().getBoundsInParent().getMinY() > 650){
				hit(entities.get(i).getHealth());
				entities.remove(i);
			}
		}
		
		i = bullets.size();
		while(i-- > 0){
			if(bullets.get(i).getImage().getBoundsInParent().getMaxY() < 0){
				bullets.remove(i);
			}
		}
		
		i = enemyBullets.size();
		while(i-- > 0){
			if(enemyBullets.get(i).getImage().getBoundsInParent().getMinY() > 650){
				enemyBullets.remove(i);
			}
		}
		
		i = items.size();
		while(i-- > 0){
			if(items.get(i).getImage().getBoundsInParent().getMinY() > 650){
				items.remove(i);
			}
		}
	}
	
	//updates view and user health
	public void hit(int health) {
		// TODO Auto-generated method stub
		((PlayView) v).hit(health);
		user.hit(health);
	}

	//adds bullet from user input, called from view
	public void addBullet(PlayerBullet bullet) {
		// TODO Auto-generated method stub
		bullets.add(bullet);
	}
	
	//checks collision between user bullets and enemies.
	//if collided, remove bullet from view and list, update enemy health
	//if enemy health <= 0, remove enemey from view and list
	public void checkCollisions() {
		// TODO Auto-generated method stub
		int i = entities.size();
		while(i-- > 0){
			int j = bullets.size();
			while(j-- > 0){
				if(entities.get(i).getImage().getBoundsInParent().intersects(bullets.get(j).getImage().getBoundsInParent())){
					PlayerBullet temp = bullets.get(j);
					entities.get(i).hit(temp.getStrength());
					if(entities.get(i).getHealth() <= 0){
						((PlayView) v).deleteEnemy(entities.get(i).getImage());
						((PlayView) v).addScore(entities.get(i).getFullHealth());
					    entities.remove(i);
					}
					((PlayView) v).remove(bullets.get(j).getImage());
				    bullets.remove(j);
				}
			}
		}
	}
	
	//create enemy based on round
	//crates enemies every 3 seconds at first, subtracting by .5 seconds every round update
	//based on difficulty, creates different kind of entity
	public void createEntity(long time) {
		// TODO Auto-generated method stub
		if((time-start)/round > 1000000000){
			switch(diff){
				case 0:
					createEasyEntity(time);
					break;
				case 1:
					createMediumEntity(time);
					break;
				default:
					createDifficultEntity(time);
					break;
			}
		}
	}
	
	//medium entity creates random entity with set direction down
	private void createMediumEntity(long time) {
		// TODO Auto-generated method stub
		Enemy enemy = createRandomEntity("DOWN");
		entities.add(enemy);
		((PlayView) v).setEnemy(enemy);
		start = time;
	}
	
	//random enemy created, either basic, big, or quick entity with given direction
	private Enemy createRandomEntity(String tempDirection){
		int temp = (int) Math.floor(Math.random()*3);
		
		Enemy enemy = null;
		switch(temp){
			case 0:
				enemy = new BasicEnemy(Math.random()*500 + 100, 0, System.nanoTime(), tempDirection);
				break;
			case 1:
				enemy = new QuickEnemy(Math.random()*500 + 100, 0, System.nanoTime(), tempDirection);
				break;
			case 2:
				enemy = new BigEnemy(Math.random()*500 + 100, 0, System.nanoTime(), tempDirection);
				break;
		}
		return enemy;
	}

	//difficult entity created; random entity and random direction
	private void createDifficultEntity(long time) {
		// TODO Auto-generated method stub
		int temp = (int) Math.floor(Math.random()*2);
		String tempDirection;
		if(temp == 0){
			tempDirection = "DOWN";
		}
		else{
			tempDirection = "DIAGONAL";
		}
		Enemy enemy = createRandomEntity(tempDirection);
		entities.add(enemy);
		((PlayView) v).setEnemy(enemy);
		start = time;
	}

	//easy entity creates a basic enemy and set direction down
	private void createEasyEntity(long time) {
		// TODO Auto-generated method stub
		Enemy enemy = new BasicEnemy(Math.random()*500 + 100, 0, System.nanoTime(), "DOWN");
		entities.add(enemy);
		((PlayView) v).setEnemy(enemy);
		start = time;
	}

	//enemy bullet created based on number of enemy bullets
	public void createEnemyBullet(Enemy enemy) {
		// TODO Auto-generated method stub
		EnemyBullet bullet, bullet2, bullet3;
		switch(enemy.getBulletNumber()){
			case 1:
				bullet = new EnemyBullet(enemy.getX(), enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				enemyBullets.add(bullet);
				((PlayView) v).addEnemyBullet(bullet);
				break;
			case 2:
				bullet = new EnemyBullet(enemy.getX() - 5, enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				bullet2 = new EnemyBullet(enemy.getX() + 5, enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				enemyBullets.add(bullet);
				enemyBullets.add(bullet2);
				((PlayView) v).addEnemyBullet(bullet, bullet2);
				break;
			case 3: 
				bullet = new EnemyBullet(enemy.getX() - 10, enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				bullet2 = new EnemyBullet(enemy.getX(), enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				bullet3 = new EnemyBullet(enemy.getX() + 10, enemy.getY() + 25, enemy.getBulletStrength(), enemy.getBulletSpeed());
				
				enemyBullets.add(bullet);
				enemyBullets.add(bullet2);
				enemyBullets.add(bullet3);
				((PlayView) v).addEnemyBullet(bullet, bullet2, bullet3);
				break;	
		}
		
	}

	//transition either to play again or start screen
	@Override
	public View nextView() {
		// TODO Auto-generated method stub
		switch(next){
			case "MENU":
				return new StartView(v.getScene(), new FlowPane());
			case "PLAY":
				return new PlayView(v.getScene(), v.getPane(), user, diff);
			default:
				return null;
		}
	}
	
	public void setShoot(boolean b){
		shooting = b;
	}
	
	public void setMove(boolean b, KeyEvent event){
		this.event = event;
		moving = b;
	}
	
	public boolean isMoving() {
		// TODO Auto-generated method stub
		return moving;
	}
	
	public boolean isShooting() {
		// TODO Auto-generated method stub
		return shooting;
	}
	
	public void setDead(){
		alive = false;
	}

	public void setState(String input) {
		// TODO Auto-generated method stub
		next = input;
	}

}
