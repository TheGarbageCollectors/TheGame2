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
public class UpgradeStation
{
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

    public UpgradeStation()
    {
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
                        obj.upgradeLevel();
                    }
                    break;
                case "Recycler":
                    if (obj.getLevel() < recyclerUpgradeArray.length)
                    {
                        upgradePrice = recyclerUpgradeArray[obj.getLevel()];
                        obj.upgradeLevel();
                    }
                    break;
                case "Backpack":
                    if (obj.getLevel() < backpackUpgradePriceArray.length)
                    {
                        upgradePrice = backpackUpgradePriceArray[obj.getLevel()];
                        obj.upgradeLevel();
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
