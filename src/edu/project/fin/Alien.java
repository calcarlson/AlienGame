package edu.project.fin;

public abstract class Alien {
    private int health; // 0=dead, 100=full strength
    private String name;
    private double damage;
    private long movement;

    public Alien(int health, String name, double damage, long movement) {
	super();
	this.health = health;
	this.name = name;
	this.damage = damage;
	this.movement = movement;
    }

    public int getHealth() {
	return health;
    }

    public void setHealth(int health) {
	this.health = health;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getDamage() {
	return damage;
    }

    public void setDamage(double damage) {
	this.damage = damage;
    }

    public long getMovement() {
	return movement;
    }

    public void setMovement(long movement) {
	this.movement = movement;
    }
}