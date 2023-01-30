import java.util.HashMap;

import java.util.Set;

public class Player {
    private HashMap<String, Item> inventory;

  Item intergrity;
  Command command;

    Player() {
        inventory = new HashMap<>();

    }

    public String getItemString() {
        String returnString = "Player Inv:";
        Set<String> keys = inventory.keySet();
        for(String item: keys) {
            returnString += " " + item;
        }
        return returnString;
    }

    public void setItem(String name, Item item) {
        inventory.put(name, item);
    }

    public Item getItem(String name) {
        return inventory.remove(name);
    }

    public boolean hasKey(String enter){

        if(enter == "castle"){
            if(inventory.containsKey("guard key")){
                return true;
            }
        if(enter == "cave"){


            return true;

        }
    }
        return false;
    }



    /*public HashMap getInventory(){
        return inventory;}

    public void setIntergrity(String name,Item intergrity){
        this.intergrity = intergrity;
    }
    public Item getIntergrity(){
        return intergrity;
    }*/

}