package monstergame;

import java.util.Random;

public class Monster 
{
	private String name;
	private int attackPoints, defencePoints, lifePoints, damagePoints;
	
	public Monster()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(5);
		
		switch(randomNumber)
		{
			case 0: name = "Black Daemon"; break;
	        case 1: name = "The Rabbit"; break;
	        case 2: name = "Silver Dragon"; break;
	        case 3: name = "Mountain Troll"; break;
	        case 4: name = "Alien Monster"; break;
	        default: name = "Sphinx"; break;
		}
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public int getDefencePoints() {
		return defencePoints;
	}

	public void setDefencePoints(int defencePoints) {
		this.defencePoints = defencePoints;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public int getDamagePoints() {
		return damagePoints;
	}

	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}

	public String getName() {
		return name;
	}
	

}
