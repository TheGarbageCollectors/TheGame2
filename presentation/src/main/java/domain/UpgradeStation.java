/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

public class UpgradeStation extends Room
{

    private String name;

    private int[] backpackUpgradePriceArray =
    {
        50, 500, 1000000
    };
    private int[] recyclerUpgradeArray =
    {
        200, 750, 1000000
    };
    private int[] townUpgradePriceArray =
    {
        100, 200, 300, 400, 500, 750, 1000, 1500, 2000, 1000000
    };

    public UpgradeStation(String dir, String name)
    {
        super(dir);
        this.name = name;

    }

    public void buyUpgrade(Object obj, Player playerObj)
    {
        int upgradePrice = 0;
        if (!(obj instanceof Upgradeable))
        {
            System.out.println("No");
        }
        //Der er m[ske en bedre m[de at g're det p[. Jeg t;nker at vi evt kan bruge en array i en array og s[ et nested for loop for at tjekke hvad der bliver upgraderet.
        // hvis det er 0 der bliver upgraderet s[ tilg[r den arrayet p[ index 0. 
        if (null != ((Upgradeable) obj).getName())
        {
            switch (((Upgradeable) obj).getName())
            {
                case "town_hall":
                    if (((Upgradeable) obj).getLevel() < townUpgradePriceArray.length)
                    {

                        upgradePrice = townUpgradePriceArray[(((Upgradeable) obj).getLevel()) - 1];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            ((Upgradeable) obj).upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                            if (((Town) obj).getHappiness()>= 100 )
                            {
                                System.out.println("You have won the game (:");
                            }
                        } else
                        {
                            System.out.println("You do not have enough money for this upgrade");
                        }

                    }
                    break;
                case "recycler":
                    if (((Upgradeable) obj).getLevel() < recyclerUpgradeArray.length)
                    {
                        upgradePrice = recyclerUpgradeArray[(((Upgradeable) obj).getLevel() - 1)];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            ((Upgradeable) obj).upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                        } else
                        {
                            System.out.println("You do not have enough money for this upgrade");
                        }
                    }
                    break;
                case "backpack":
                    if (((Upgradeable) obj).getLevel() < backpackUpgradePriceArray.length)
                    {
                        upgradePrice = backpackUpgradePriceArray[(((Upgradeable) obj).getLevel() - 1)];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            ((Upgradeable) obj).upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                        } else
                        {
                            System.out.println("You do not have enough money for this upgrade");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid: Error");
                    break;
            }
        }
    }
    
    public void upgradeObject(String thingToBeUpgraded, Room upgradeStation, Town town, Room recycler, Player player1) {
            //Using a switch statement to figure out what the user wants to upgrade. 
            switch (thingToBeUpgraded)
            {
                case "townhall":
                    ((UpgradeStation) upgradeStation).buyUpgrade(town, player1);
                    System.out.println("Town Hall level is now: " + ((Upgradeable) town).getLevel());
                    town.increaseHappiness((town.getLevel() * 10) - town.getHappiness());
                    if ( town.getHappiness() >= 100 ){
                        System.out.println("You have won the game");
                        
                    } else {
                    System.out.println("Your town's happiness is now: " + town.getHappiness());
                    break;
                    }
                case "recycler":
                    ((UpgradeStation) upgradeStation).buyUpgrade(recycler, player1);
                    System.out.println("Recycler level is now: " + ((Upgradeable) recycler).getLevel());
                    break;
                case "backpack":
                    ((UpgradeStation) upgradeStation).buyUpgrade(player1.getBackpackObj(), player1);
                    System.out.println("Backpack level is now: " + ((Upgradeable) player1.getBackpackObj()).getLevel());
                    System.out.println("Your inventory size is now: " + player1.getBackpackObj().getMaxSize());
                    break;
            }

        
       
    }

    public int getUpgradePrices(String thingToUpgrade, Town town, Room recycler, Player player1) {
        int tempPrice = 0;
        switch(thingToUpgrade) {
            case "townhall":
                tempPrice = this.townUpgradePriceArray[((Upgradeable) town).getLevel()-1];
                break;
            case "recycler":
                tempPrice = this.recyclerUpgradeArray[((Upgradeable) recycler).getLevel()-1];
                break;
            case "backpack":
                tempPrice = this.backpackUpgradePriceArray[((Upgradeable) player1.getBackpackObj()).getLevel()-1];
                break;
        }
        return tempPrice;
    } 
    
    public String getName() { 
        return this.name;
    }
}
