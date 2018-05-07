package monstergame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main 
{
	private static Scanner in = new Scanner(System.in);
	private static Player player ;
	private static ArrayList<Monster> monsterList ;
	
	//Main method. Program execution starts from here
	public static void main (String[] args)
	{
		printSeperator();
		System.out.println("Welcome to the Monster Game");
		printSeperator();
		
		System.out.println("Enter your credentials");
		player = createPlayer();
		printSeperator();
		
		GameDifficulty difficulty = getGameDifficulty();
		monsterList = getMonsterList(difficulty);
		
		if(monsterList.size()>1)
			displayMonsterList();
		
		
		for(int i=0;i<monsterList.size();i++)
		{
			Monster monster = monsterList.get(i);
			
			printSeperator();
			playGame(player, monster);
			printSeperator();
			
			if (player.getLifePoints() <= 0) {
	            System.out.println("You are dead. Your adventure finishes here. R.I.P.");
	            break; // from for loop
	        }
	        if (monster.getLifePoints()<= 0) {
	            System.out.println("You defeated the "+monster.getName()+". Congratulations!");
	        }
		}
		
		
		
		
        
        
	}
	
	//This method prints the monster's list
	private static void displayMonsterList() {
		printSeperator();
		System.out.println("You have to fight the follwing monters: ");
		for(int i=0;i<monsterList.size();i++)
		{
			System.out.println(monsterList.get(i).getName());
		}
		printSeperator();
	}

	//This method contains the actual logic of the game
	private static void playGame(Player player, Monster monster)
	{
		boolean runaway = false;
		Random generator = new Random();
		do 
		{
            boolean attacker = generator.nextBoolean();
            if (attacker) 
            {
                System.out.println("Your attack");
                int dice = generator.nextInt(6)+1 + generator.nextInt(6)+1;
                int attackValue = player.getAttackPoints() + dice;
                System.out.println("Rolled values: "+dice);
                System.out.println("Your attack value: " + attackValue);
                if (attackValue > monster.getDefencePoints()) 
                {
                    System.out.println("Your attack was successful.");
                    monster.setLifePoints(monster.getLifePoints() - player.getDamagePoints());
                    //System.out.println(monster.getName()+"'s remaining Life Points: " + monster.getLifePoints());
                } 
                else 
                {
                    System.out.println("Your attack was not successful.");
                }
                displayScoreBoard(player, monster);
            } 
            else 
            {
                System.out.println(monster.getName()+" attacks.");
                int dice = generator.nextInt(6)+1 + generator.nextInt(6)+1;
                int attackValue = monster.getAttackPoints() + dice;
                System.out.println("Rolled values:"+dice);
                System.out.println(monster.getName()+"'s attack value: " + attackValue);
                if (attackValue > player.getDefencePoints())
                {
                    System.out.println(monster.getName()+"'s attack was successful.");
                    player.setLifePoints(player.getLifePoints() - monster.getDamagePoints());
                    //System.out.println("Your remaining Life Points: " + player.getLifePoints());
                } 
                else 
                {
                    System.out.println(monster.getName()+"'s attack was not successful.");
                }
                displayScoreBoard(player, monster);
            }
            // from this point on
            System.out.println("Please press ENTER to continue! (Or type in 'run' to run away)");
            String command = in.nextLine();
            switch (command)
            {
                case "run":
                    System.out.println("You ran away. You coward! -4 Life Points!");
                    player.setLifePoints(player.getLifePoints()-4);
                    runaway = true;
                    break;
                default:
            }
        } while (player.getLifePoints()>0 && monster.getLifePoints()>0 && !runaway);
		
	}
	
	//This method displays the life points score board
	private static void displayScoreBoard(Player player, Monster monster)
	{
		
		System.out.println("-----------------------------------------");
		System.out.println("|Your Life\t|"+monster.getName()+"'s Life\t|");
		System.out.println("-----------------------------------------");
		System.out.println("|\t"+player.getLifePoints()+"\t|\t"+monster.getLifePoints()+"\t\t|");
		System.out.println("-----------------------------------------");

	}
	
	//This method gets the difficulty level of the game
	private static GameDifficulty getGameDifficulty()
	{
		GameDifficulty difficulty = null;
		while(difficulty == null)
		{
			System.out.println("Select difficulty level: \n1. Easy \t2. Moderate \t3. Difficult ");
			int difficultyChoice = in.nextInt();
			in.nextLine();
			switch(difficultyChoice)
			{
				case 1: difficulty = GameDifficulty.EASY;	 break;
				case 2: difficulty = GameDifficulty.NORMAL;	 break;
				case 3: difficulty = GameDifficulty.DIFFICULT;	 break;
				default: System.out.println("Invalid choice!! Select again");
			}
		}
		
		return difficulty;
		
	}
	
	//This method returns the monsters list as per the difficulty level of the game
	private static ArrayList<Monster> getMonsterList(GameDifficulty difficulty ) 
	{
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		switch(difficulty)
		{
			case EASY: {
				monsterList.add(createEasyMonster());
			}
			break;
			
			case NORMAL:{
				monsterList.add(createNormalMonster());
			}
			break;
			
			case DIFFICULT:{
				monsterList.add(createNormalMonster());
				monsterList.add(createNormalMonster());
				monsterList.add(createNormalMonster());
			}
			break;
		}
		
		return monsterList;
	}

	//This method creates a new monster equivalent to the user
	private static Monster createNormalMonster()
	{
		Monster monster = new Monster();
		monster.setAttackPoints(player.getAttackPoints());
		monster.setDefencePoints(player.getDefencePoints());
		monster.setLifePoints(player.getLifePoints());
		monster.setDamagePoints(player.getDamagePoints());
		return monster;
	}
	
	//This method create a new monster (level: easy)
	private static Monster createEasyMonster()
	{
		Monster monster = new Monster();
		monster.setAttackPoints(player.getAttackPoints());
		monster.setDefencePoints(player.getDefencePoints()/2);
		monster.setLifePoints(player.getLifePoints()/2);
		monster.setDamagePoints(player.getDamagePoints());
		return monster;
	}
	
	//This method creates a new player and returns it
	private static Player createPlayer()
	{
		Player player = new Player();
		
		System.out.print("Your Attack Points: ");
		player.setAttackPoints(in.nextInt());
        System.out.print("Your Defense Points: ");
        player.setDefencePoints(in.nextInt());
        System.out.print("Your Life Points: ");
        player.setLifePoints(in.nextInt());
        System.out.print("Your Damage Points: ");
        player.setDamagePoints(in.nextInt());
        
        return player;
	}
	
	// This method is used to provide a seperator life for more visuality
	private static void printSeperator()
	{
		System.out.println("*******************************");
	}

}
