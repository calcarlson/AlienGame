package edu.project.fin;

public class Player {
    public double health;
    public String name = "";
    public boolean alive;
    public long movement;

    public Player(String name, double health, boolean alive, long movement) {
	this.name = name;
	this.health = health;
	this.movement = movement;
	this.alive = alive;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setHealth(double health) {
	this.health = health;
    }

    public double getHealth() {
	return health;
    }

    public void setLuck(long movement) {
	this.movement = movement;
    }

    public long getMovement() {
	return movement;
    }

    public void setAlive(boolean alive) {
	this.alive = alive;
    }

    public boolean getAlive() {
	return alive;
    }
}
