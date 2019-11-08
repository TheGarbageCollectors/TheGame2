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

    public void buyUpgrade(Object obj, Player playerObj)
    {
        int upgradePrice = 0;
        if (!(obj instanceof Upgradeable))
        {
            return;
        }
        //Der er m[ske en bedre m[de at g're det p[. Jeg t;nker at vi evt kan bruge en array i en array og s[ et nested for loop for at tjekke hvad der bliver upgraderet.
        // hvis det er 0 der bliver upgraderet s[ tilg[r den arrayet p[ index 0. 
        if (null != ((Upgradeable) obj).getName())
        {
            switch (((Upgradeable) obj).getName())
            {
                case "Town":
                    if (((Upgradeable) obj).getLevel() < townUpgradePriceArray.length)
                    {

                        upgradePrice = townUpgradePriceArray[((Upgradeable) obj).getLevel()];
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
                case "Recycler":
                    if (((Upgradeable) obj).getLevel() < recyclerUpgradeArray.length)
                    {
                        upgradePrice = recyclerUpgradeArray[((Upgradeable) obj).getLevel()];
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
                        upgradePrice = backpackUpgradePriceArray[((Upgradeable) obj).getLevel()];
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
                    break;
            }
        }
        playerObj.removeMoney(upgradePrice);
        //Remove money from the player account here 

    }
}
