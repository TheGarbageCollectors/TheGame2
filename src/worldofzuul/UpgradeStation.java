/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author GamerQuvang
 */
public class UpgradeStation extends Room
{

    private String name;

    private int[] backpackUpgradePriceArray =
    {
        50, 300, 500
    };
    private int[] recyclerUpgradeArray =
    {
        100, 200, 300
    };
    private int[] recyclerSortingUpgradeArray =
    {
        0, 30, 300
    };
    private int[] townUpgradePriceArray =
    {
        150, 300, 500, 750, 1000, 1500, 2000, 2500, 5000, 1000000
    };

    public UpgradeStation(String dir, String name)
    {
        super(dir);
        this.name = name;

    }
    public void welcomeMessage(Object backpack, Object town, Object recycler){
                int backpackLevel = ((Upgradeable) backpack).getLevel();
                int townLevel = ((Upgradeable) town).getLevel();
                int recyclerLevel = ((Upgradeable) recycler).getLevel();
                System.out.println("You can buy upgrades for Backpack, Town and Recycler");
                System.out.println("Next upgrade for Backpack is: " + (backpackLevel + 1) + ". level that costs: " + backpackUpgradePriceArray[backpackLevel - 1]);
                System.out.println("Next upgrade for Town_Hall is: " + (townLevel + 1) + ". level that costs: " + townUpgradePriceArray[townLevel - 1]);
                System.out.println("Next upgrade for Recycler is: " + (recyclerLevel + 1)  + ". level that costs: " + recyclerUpgradeArray[recyclerLevel - 1]);
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
                case "Backpack":
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
        //playerObj.removeMoney(upgradePrice);
        //Remove money from the player account here 

    }
}
