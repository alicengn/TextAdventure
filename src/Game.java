public class Game {

    private Room currentRoom;
    Room innerCave;
    Room cave;
    Room lake;

    Room store;
    Room dungeon;
    Room dragonCage;

    Room insideCastle;
    Room kitchen;
    Room traderRoom;

    Room frontCastle;
    Room backCastle;
    Room eastCastle;
    Room westCastle;

    Room northWestForest;
    Room eastForest;
    Room mountain;
    Room top;

    Item medal;
    Item sword;
    Item carrot;
    Item goldenEgg;
    private Parser parser;
    private Player player;
    boolean finished = false;
    boolean wantToQuit;
    boolean pass = false;
    boolean light;
    boolean checkMedal;
    boolean ownerQuest = false;
    boolean damage = false;




    public Game() {
        parser = new Parser();
        player = new Player();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.createRooms();
        game.play();
    }

    private void createRooms() {
        frontCastle = new Room("Front Castle","the main gate is guarded securely, I don't think those guard will let us in...","This look like the front of the castle, some of the guard standing there, talk to them, maybe they know something. There is a road leading up north");
        backCastle = new Room("Back Castle","they have a little gate here for seller to enter","This is the back of the castle, some seller getting into the castle to delivery something");
        eastCastle = new Room("East Castle","The wall is too high, I don't think it's climbable","This is the west of the castle, the wall look too tall, there are some bushes around.");
        westCastle = new Room("West Castle","those walls look so high,  there is also small hole in the walk","This is the East of the castle,  the wall look unclimbable, but there is a small hole underneath, I wonder will it fit if a human try to get through.");
        insideCastle = new Room ("West Castle","You are inside the castle now!","You are inside the castle with the help of the guard, but I don't think we have much time in here, try to get what you need here and escape quick!");
        kitchen = new Room("Kitchen","Ohh it's the kitchen, but why there is no food here?","This is the kitchen inside the castle, there is a way out on the west.");
        traderRoom = new Room("Trader Room","Is the trading room, this place look fun","This is the trading room, there are seller and also guard in here, I can pretend to obe one of the trader and get out of here!");

        store = new Room("Store","adventurer, this AMAZING STORE might have something you need for your adventure, take a look","This store have so many cool thing, but the owner is not around, I wonder where are they... If I grab something, will there be any consequenses?");
        cave = new Room("Cave","Wow, it's a dark cave, we need some sort of light!","Wow, it's a dark cave, we need some sort of light!" );
        innerCave = new Room("Inner Cave","This cave look huge, maybe it lead can lead to somewhere","This is a big cave with  water dripping from the stone above, walk careful and don't be too loud." );

        northWestForest = new Room("North West Forest","This is a forest with big tree all around" ,"This is a huge forest, I can see big tree all around and rabbit jumping. There is a golden egg in one of the rabbit hole.");
        eastForest = new Room("East Forest","This road seems to be blocked by a tree" ,"This road seems to be blocked by a tree");
        lake = new Room("Lake","The lake doesn't look too deep","This is a lake with crystal clear water,there is something on the north side of it. I just wonder how can we pass here?");
        mountain = new Room ("Mountain", "You swim over the lake and see the mountain, there is something glowing on the top of the mountain", "There is something glowing on the top of the mountain, this is a very tall mountain, but look climbable");
        dungeon = new Room("Dungeon","uhhhhh, an abandoned dungeon? ","this is an old dungeon, seems like no one has stepped inside for a very long time" );
        dragonCage = new Room("Dragon Cage","WAIT WAIT what is that!!!!! A DRAGON??","The dragon is sleeping, be careful, don't wake it up! But it seems like there is something under the dragon's neck..." );
top = new Room ("Top Mountain", "So this is the end of our journey, you are a great hero for being able to go this far, a legend of this world. And now our journey come to an end, it's time to say good bye. YOU WIN", "");


        frontCastle.setExit("north", cave);
        frontCastle.setExit("west", westCastle);
        frontCastle.setExit("east", eastCastle);
        frontCastle.setExit("inside", insideCastle);
        frontCastle.setExit("south",backCastle);

        eastCastle.setExit("north", frontCastle);
        eastCastle.setExit("south", backCastle);
        eastCastle.setExit("east", eastForest);

        eastForest.setExit("west", eastCastle);


        westCastle.setExit("north", frontCastle);
        westCastle.setExit("south", backCastle);
        westCastle.setExit("west", dungeon);
        westCastle.setExit("under", westCastle);

        backCastle.setExit("east", eastCastle);
        backCastle.setExit("north", frontCastle);
        backCastle.setExit("south", store);
        backCastle.setExit("west", westCastle);
        backCastle.setExit("inside",traderRoom);

        insideCastle.setExit("north", traderRoom);
        insideCastle.setExit("east", kitchen);

northWestForest.setExit("south", dungeon);


        kitchen.setExit("west", insideCastle);
        traderRoom.setExit("south",insideCastle);
        traderRoom.setExit("outside", backCastle);

        dungeon.setExit("inside", dragonCage);
        dungeon.setExit("east", westCastle);
        dungeon.setExit("north",northWestForest);

        dragonCage.setExit("outside", dungeon);

        store.setExit("north", backCastle);
        cave.setExit("inside" ,innerCave);
        cave.setExit("south",frontCastle);



        innerCave.setExit("south", frontCastle);
        innerCave.setExit("west", northWestForest);
        innerCave.setExit("east", lake);

        lake.setExit("west", innerCave);
        lake.setExit("east",mountain);
        mountain.setExit("up", top);



        Item coin3 = new Item("Wow, a coin!");
        Item lighter = new Item("it's a lighter, it would be so helpful in dark place");
        Item coin1 = new Item("Wow, a coin!");
        Item coin2 = new Item("Wow, a coin!");
         goldenEgg = new Item ("Ohhh, it's a golden egg, can I make egg scramble with it?");
         carrot = new Item ("carrot! yummy!");
        Item cup = new Item ("a glass of water, just in time, I'm so thirsty...");
        Item knife = new Item ("it's a rusted knife");
         medal = new Item ("");
         sword = new Item ("");

        insideCastle.setItem("lighter", lighter);
        backCastle.setItem("coin1", coin1);

        dungeon.setItem("coin2", coin2);
        dragonCage.setItem("medal",medal);

        eastCastle .setItem("coin3", coin3);
        northWestForest.setItem("goldenEgg", goldenEgg);

        kitchen.setItem("carrot",carrot);
        kitchen.setItem("cup", cup);
        kitchen.setItem ("knife", knife);
store.setItem("sword",sword);


        currentRoom = frontCastle;
    }

    public void play() {
        printWelcome();

        finished = false;


        while(!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thanks for playing!");
    }
    public void die(){
        System.out.println("YOU DIE");
        wantToQuit= true;
    }


    private boolean processCommand(Command command) {
        wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case LOOK:
                look(command);
                break;

            case DROP:
                drop(command);
                break;

            case GRAB:
                grab(command);
                break;
            case TALK:
                talk (command);
                break;
            case LIGHT:
                checkLight();
                break;
            case GIVE:
             give (command);
             break;
            case DRINK:
                drink(command);
                break;
            case ATTACK:
                attack(command);
                break;

        }



        return wantToQuit;
    }
    public void checkLight(){
        if (player.getInventory().containsKey("lighter")) {
            System.out.println("Lighter on");
            light = true;
            return ;
        }
        else{
            System.out.println("You don't have anything to light");
            light =  false;
            return;
        }
    }
    public boolean checkMedal(){
        if (player.getInventory().containsKey("medal")) {
            checkMedal = true;
            return true;
        }
        else{

             checkMedal=  false;
            return false;
        }
    }


    private void attack(Command command){
        if(!command.hasSecondWord()) {

            System.out.println("You attack the Dragon with your sword and it seems to work! Let's go adventurer!");
            System.out.println("You killed the Dragon and grab his medal");
            player.setItem("medal",medal);
            return;}
        else if (command.getSecondWord().equals("dragon")){
            System.out.println("You attack the Dragon with your sword and it seems to work! Let's go adventurer!");
            System.out.println("You killed the Dragon and grab his medal");
            player.setItem("medal",medal);
            return;
        }
        else{
            System.out.println("You cannot attack that "+command.getSecondWord());
            return;
        }
    }
private void drink(Command command){
    if(!command.hasSecondWord()) {

        System.out.println("Drink what?");
        return;
    }

    String item = command.getSecondWord();
    if (item.equals("cup")){
        System.out.println("You save yourself from dehydration!");
        player.getItem("cup");
        return;
    }
    else {
        System.out.println("You cannot drink that "+item);
    }
}
private void give(Command command){

    if(!command.hasSecondWord()) {

        System.out.println("Give what?");
        return;
    }

    String item = command.getSecondWord();

    if (player.getInventory().containsKey(item)){
        pass=true;
        System.out.println("You distract the rabbit by carrot! Grab the egg before they know!");
        player.getItem("carrot");
        System.out.println("You picked up the golden egg");
        player.setItem("goldenEgg",goldenEgg);

        return;
    }

    else{
    System.out.println("You cannot give that "+item); return;}
    }




    private void grab(Command command) {

        if(!command.hasSecondWord()) {

            System.out.println("Grab what?");
            return;
        }

        String key = command.getSecondWord();
        Item grabItem = currentRoom.getItem(key);


        if (grabItem == null) {
            System.out.println("You can't grab " + command.getSecondWord()); return;}

        else if (key.equals("sword")&&currentRoom.equals(store)) {
            System.out.println ("The seller come back and think that you try to steal his sword, he is waiting for your explaination,try to talk to him!"); return;
            }
        else if (grabItem.equals(medal)&& currentRoom.equals(dragonCage)) {
            if (player.getInventory().containsKey("sword")) {
                System.out.println("You wake the dragon up by trying to get the medal on it neck, it try to attack you but fail because the sword in your hand glow and stunned the dragon");
                return;
            }
            else if (!player.getInventory().containsKey("sword")){
                System.out.println("You got eaten by the dragon while trying to get his medal");
                die();
                return;
            }}


        else if (key.equals("goldenEgg")&&currentRoom.equals(northWestForest)){
            if (pass == true){
                System.out.println("You picked up the golden egg");
                currentRoom.getItem("goldenEgg");
                player.setItem("goldenEgg",goldenEgg);
                return;
            }
            else{
            System.out.println("the rabbit stopped you from getting the egg, find some food to distract them"); currentRoom.setItem("goldenEgg",goldenEgg);
        }}


        else {
            player.setItem(key, grabItem);
            System.out.println("You picked up " + command.getSecondWord());
            System.out.println(grabItem.getItemDescription());
return;
        }
    }
    private void talk (Command command){
        //ask mr Adams about second word
        if (!command.hasSecondWord()){
            System.out.println("Who do you want to talk to?");
return;
        }
        String character = command.getSecondWord();

        if (character.equals("guard")&&currentRoom.equals(frontCastle)){
            if (player.getInventory().containsKey("coin1") &&player.getInventory().containsKey("coin2")&&player.getInventory().containsKey("coin3") ){
                System.out.println("Guard: Thanks, now hold this key to go inside, don't  tell anyone I give you this!");
                player.setItem("guardKey", new Item("")); player.getItem("coin1");player.getItem("coin2");player.getItem("coin3");
                return;}
            if (currentRoom.equals(frontCastle)&&player.getInventory().containsKey("guardKey") ){
                System.out.println("Guard: Good luck adventurer!");

                return;}
            else if (currentRoom.equals(frontCastle))
                System.out.println("Guard: Hey!!! You can't pass here! But if you find me 3 coins then maybe I can offer little help...Don't tell anyone");
            return;}
        if (character.equals("seller")){
            if(player.getInventory().containsKey("goldenEgg")&&currentRoom.equals(store)){
                System.out.println("Seller: I will get this egg, now you can get that sword, some other thing too if you want!");
                System.out.println("The seller gave you the sword");
                player.setItem("sword", new Item(""));
                return;}
            else if(player.getInventory().containsKey("sword")&&currentRoom.equals(store)){
                System.out.println("Seller: I will close the store soon"); return;
            }
            else if (ownerQuest == true){
                System.out.println("Seller: Talk to me when you have the egg, hurry up!"); return;
            }
            else {System.out.println("Seller: Are you trying to steal the sword young man? I can give it to you, only if you find me a golden egg, it think it locate somewhere at the North West Forest."); ownerQuest = true; return;}
        }
        else
            System.out.println("You can't talk to "+character); return;
    }


    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        String key = command.getSecondWord();
        Item dropItem = player.getItem(key);

        if (dropItem == null) {
            System.out.println("You can't drop " + command.getSecondWord());
        }
        else {
            currentRoom.setItem(key, dropItem);
        }
    }

    private void printHelp() {
        System.out.println("You are lost.  You are alone.  You wander");
        System.out.println("You are in a mysterious world, there is a Castle behind you, maybe there will be people that know something...");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void look(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("You can't look at " + command.getSecondWord());
            return;
        }

        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Which direction do you want to go?");
return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null ) {
            System.out.println("You can't go "+command.getSecondWord());

            return;
        }



        if (nextRoom.equals(insideCastle)) {
            //System.out.println("inside first if");
            if (currentRoom.equals(frontCastle) && player.getInventory().containsKey("guardKey")) {
                currentRoom = insideCastle;
                System.out.println("Current Room: "+currentRoom.getName());
                System.out.println("You have successfully enter the castle with the guard key, now try to find something useful here and escape before anyone notice...");

                System.out.println(currentRoom.getShortDescription());
                return;
            }
            else if (currentRoom.equals(frontCastle) && !player.getInventory().containsKey("guardKey")){
                System.out.println ("You got arrested by the guard for illegally attempt to enter the castle");
                System.out.println("You spend the rest of your life in prison");
                die();
                return;
            }
            else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getName());
                System.out.println(currentRoom.getShortDescription());
                return;
            }
        }
else if (nextRoom.equals(traderRoom)&& currentRoom.equals(backCastle)){
    System.out.println("You got arrest by trying to enter the castle illegally");
    die();
    return;
        }
else if (direction.equals("under")){
            System.out.println("You got arrest by trying to enter the castle illegally");
            die();
            return;
        }
        else if(nextRoom.equals(innerCave)){
            if (currentRoom.equals(cave) &&light == true)   {
                currentRoom = innerCave;
                System.out.println("Current Room: "+currentRoom.getName());

                System.out.println(currentRoom.getShortDescription());
                return;}
            else if (currentRoom.equals(cave) &&light == false){
                System.out.println ("You got killed by the dark knight, remember to get a light next time!");
                die();
                return;}
            else if (currentRoom.equals(lake)){
                currentRoom = innerCave;
                System.out.println("Current Room: "+currentRoom.getName());

                System.out.println(currentRoom.getShortDescription());
                return;
            }}
            else if(nextRoom.equals(mountain)){
                if (checkMedal() == true){
                    System.out.println("With the power of the medal, you swam over the lake and reach the mountain");
                    currentRoom = mountain;
                    System.out.println(currentRoom.getName());
                    System.out.println(currentRoom.getShortDescription());
                    return;
                }
                else if (checkMedal() == false){

                    currentRoom = lake;
                    System.out.println("This lake have some magic spell to prevent you from passing it, find a magic medal to pass");

                    return;
                }}
           else if (direction.equals("up")){
               currentRoom = top;
               System.out.println(currentRoom.getShortDescription());
               System.out.println ("YOU WIN AND GET BACK TO THE REAL WORLD");
                wantToQuit = true;
                return;
            }




    else{

    currentRoom = nextRoom;
    System.out.println("Current Room: "+currentRoom.getName());
    System.out.println (nextRoom.getShortDescription());
    return;
    }}

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("You can't quit " + command.getSecondWord());
            return false;
        }
        else {
            return true;
        }
    }


    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in a Mystery World, try figure out what happen!");
        System.out.println("Type \"help\" if you need assistance");
        System.out.println();
        System.out.println("Wow, it's a big castle here. This look like the front of the castle, some of the guard standing there, maybe they know something. There is a road leading up north");
    }}
/*Room nextRoom = ;
    if nextroom.equals(RoomVarible){
        if (player.getInventory().hasKey("thing")){
            System.out.println()}
        }
    System.out.println()*/