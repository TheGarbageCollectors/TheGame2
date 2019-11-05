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
        0, 10, 100
    };
    private int[] recyclerUpgradeArray =
    {
        0, 20, 200
    };
    private int[] recyclerSortingUpgradeArray =
    {
        0, 30, 300
    };
    private int[] townUpgradePriceArray =
    {
        0, 30, 300
    };

    public UpgradeStation(String dir, String name)
    {
        super(dir);
        this.name = name;
        
    }

    public void buyUpgrade(Upgradeable obj, Player playerObj)
    {
        int upgradePrice = 0;
        if (null != obj.getName())
        {
            switch (obj.getName())
            {
                case "Town":
                    if (obj.getLevel() < townUpgradePriceArray.length)
                    {

                        upgradePrice = townUpgradePriceArray[obj.getLevel()];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            obj.upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                        }

                    }
                    break;
                case "Recycler":
                    if (obj.getLevel() < recyclerUpgradeArray.length)
                    {
                        upgradePrice = recyclerUpgradeArray[obj.getLevel()];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            obj.upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                        }
                    }
                    break;
                case "Backpack":
                    if (obj.getLevel() < backpackUpgradePriceArray.length)
                    {
                        upgradePrice = backpackUpgradePriceArray[obj.getLevel()];
                        if (playerObj.enoughMoney(upgradePrice))
                        {
                            obj.upgradeLevel();
                            playerObj.removeMoney(upgradePrice);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        playerObj.removeMoney(upgradePrice);
        //Remove money from the player account here 

    }
}
