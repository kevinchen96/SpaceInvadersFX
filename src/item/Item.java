package item;

import entities.Entity;

public abstract class Item extends Entity{
	
	//Items contain one buff each, specific items define which buff to use
	//All Items contain all 3 buffs, each specific item will have a number greater than 0
	//for its respective buff variable
	protected int health_buff;
	protected int speed_buff;
	protected int bullet_buff;
	
	public Item(double x, double y) {
		super(x, y);
		health_buff = 0;
		speed_buff = 0;
		bullet_buff = 0;
		speed = 20;
		setScale(75);
	}
	
	public int getHealth(){
		return health_buff;
	}
	public int getSpeed(){
		return speed_buff;
	}
	public int getBullet(){
		return bullet_buff;
	}

}
