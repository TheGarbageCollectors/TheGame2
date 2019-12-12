package domain;

public class Player
{

    private String name;
    private int money;
    private Backpack backpack = new Backpack(); 

    Player(String playerName)
    {
        this.money = 1111111111;
        this.name = playerName;
    }

    public boolean enoughMoney(int amount)
    {
        int moneyLeft = this.money - amount;
        if (moneyLeft >= 0)
        {
            return true;
        } else
        {
            return false;
        }

    }
    
    public Backpack getBackpackObj (){
        return this.backpack; 
    }

    public int removeMoney(int amount)
    {
        System.out.println("Upgrade purchased. " + amount + " coins removed from your balance." );
        System.out.println("Total coins: " + (money - amount));
        return this.money -= amount;
    }

    public int getMoney()
    {
        return this.money;
    }
    public void addMoney(int amount){
        System.out.println(amount + " coins have been added to your balance" );
        System.out.println("Total coins: " + (money + amount));
        this.money += amount;
    }
    public String getName() {
        return this.name;
    }
}
