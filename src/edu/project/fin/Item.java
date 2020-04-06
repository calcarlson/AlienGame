package edu.project.fin;

public class Item {
    private int health;
    private int rarity;
    private long spawnRate;

    public Item(int health, long spawnRate, int rarity) {
	super();
	this.health = health;
	this.spawnRate = spawnRate;
	this.rarity = rarity;
    }

    public void setHealth(int health) {
	this.health = health;
    }

    public int getHealth() {
	return health;
    }

    public void setRarity(int rarity) {
	this.rarity = rarity;
    }

    public int getRarity() {
	return rarity;
    }

    public void setSpawnRate(long spawnRate) {
	this.spawnRate = spawnRate;
    }

    public long getSpawnRate() {
	return spawnRate;
    }
}
