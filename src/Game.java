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
    private Parser parser;
    private Player player;
    boolean finished = false;
    boolean wantToQuit;



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
        frontCastle = new Room("the main gate is guarded securely, I don't think those guard will let us in...","This look like the front of the castle, some of the guard standing there, maybe they know something. There is a road leading up north");
        backCastle = new Room("they have a little gate here for seller to enter","This is the back of the castle, some seller getting into the castle to delivery something");
        eastCastle = new Room("The wall is too high, I don't think it's climbable","This is the west of the castle, the wall look too tall, there are some bushes around.");
        westCastle = new Room("those walls look so high,  there is also small hole in the walk","This is the East of the castle,  the wall look unclimbable, but there is a small hole underneath, I wonder will it fit if a human try to get through.");
        insideCastle = new Room ("You are inside the castle now!","You are inside the castle with the help of the guard, but I don't think we have much time in here, try to get what you need here and escape quick!");
        kitchen = new Room("Ohh it's the kitchen, but why there is no food here?","This is the kitchen inside the castle, there is a way out on the west.");
        traderRoom = new Room("Is the trading room, this place look fun","This is the trading room, there are seller and also guard in here, I can pretend to obe one of the trader and get out of here!");

        store = new Room("advent urer, this AMAZING STORE might have something you need for your adventure, take a look","This store have so many cool thing, but the owner is not around, I wonder where are they... If I grab something, will there be any consequenses?");
        cave = new Room("Wow, it's a dark cave, we need some sort of light!","Wow, it's a dark cave, we need some sort of light!" );
        innerCave = new Room("This cave look huge, maybe it lead can lead to somewhere","This is a big cave with  water dripping from the stone above, walk careful and don't be too loud." );

        northWestForest = new Room("This is a forest with big tree all around" ,"This is a huge forest, I can see big tree all around and rabbit jumping. There is a golden egg in one of the rabbit hole.");
         eastForest = new Room("This road seems to be blocked by a tree" ,"This road seems to be blocked by a tree");
         lake = new Room("The lake doesn't look too deep","This is a lake with crystal clear water,there is something on the north side of it. I just wonder how can we pass here?");

        dungeon = new Room("uhhhhh, an abandoned dungeon? ","this is an old dungeon, seems like no one has stepped inside for a very long time" );
        dragonCage = new Room("WAIT WAIT what is that!!!!! A DRAGON??","The dragon is sleeping, be careful, don't wake it up! But it seems like there is something under the dragon's neck..." );



        frontCastle.setExit("north", cave);
        frontCastle.setExit("west", westCastle);
        frontCastle.setExit("east", eastCastle);
        frontCastle.setExit("inside", insideCastle);

        eastCastle.setExit("north", frontCastle);
        eastCastle.setExit("south", backCastle);
        eastCastle.setExit("east", eastForest);

        eastForest.setExit("west", eastCastle);


        westCastle.setExit("north", frontCastle);
        westCastle.setExit("south", backCastle);
        westCastle.setExit("west", dungeon);

        backCastle.setExit("east", eastCastle);
        backCastle.setExit("north", frontCastle);
        backCastle.setExit("south", store);
        backCastle.setExit("west", westCastle);

        insideCastle.setExit("north", traderRoom);
        insideCastle.setExit("east", kitchen);

        kitchen.setExit("west", insideCastle);
        traderRoom.setExit("south",insideCastle);
        traderRoom.setExit("north", backCastle);

        dungeon.setExit("inside", dragonCage);
        dungeon.setExit("east", eastForest);

        dragonCage.setExit("outside", dungeon);

        store.setExit("north", backCastle);
        cave.setExit("inside" ,innerCave);
        cave.setExit("south",frontCastle);


        innerCave.setExit("south", frontCastle);
        innerCave.setExit("west", northWestForest);
        innerCave.setExit("east", lake);



        Item coin3 = new Item();
        Item lighter = new Item();
        Item coin1 = new Item();
        Item coin2 = new Item();

        insideCastle.setItem("lighter", lighter);
        backCastle.setItem("coin1", coin1);

        dungeon.setItem("coin2", coin2);

        eastCastle .setItem("coin3", coin3);
        northWestForest.setItem("goldenEgg", new Item());

        kitchen.setItem("carrot",new Item());


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
        }



    return wantToQuit;
    }
    public boolean checkLight(){
        if (player.getInventory().containsKey("lighter")) {
            System.out.println("Lighter on");
            return true;
        }
        else{
            System.out.println("You don't have anything to light");
            return false;
    }}








    private void grab(Command command) {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Grab what?");
            return;
        }

        String key = command.getSecondWord();
        Item grabItem = currentRoom.getItem(key);


        if (grabItem == null) {
            System.out.println("You can't grab " + command.getSecondWord());}

        else if (key.equals("sword")&&currentRoom.equals(store)) {
            System.out.println ("The seller come back and think that you try to steal his sword, he is waiting for your explaination");}
       else if (key.equals("medal")&& currentRoom.equals(dragonCage)){
           if (player.getInventory().containsKey("sword"))
            System.out.println("You wake the dragon up by trying to get the medal on it neck, it try to attack you but fail because the sword in your hand glow and stunned the dragon");
             else {
                 System.out.println("You got eaten by the dragon while trying to get his medal");
                 die();
                 return;
           }

        }
       else if (key.equals("goldenEgg")&&currentRoom.equals(northWestForest)){
           System.out.println("the rabbit stopped you from getting the egg, find some food to distract them");
        }


        else {
            player.setItem(key, grabItem);
            System.out.println("You picked up " + command.getSecondWord());

        }
    }
    private void talk (Command command){
        //ask mr Adams about second word
        if (!command.hasSecondWord()){
            System.out.println("Who do you want to talk to?");

        }
        String character = command.getSecondWord();

        if (character.equals("guard")&&currentRoom.equals(frontCastle)){
            if (player.getInventory().containsKey("coin1") &&player.getInventory().containsKey("coin2")&&player.getInventory().containsKey("coin3") ){
                System.out.println("Thanks, now hold this key to go inside, don't  tell anyone I give you this!");
                player.setItem("guardKey", new Item() ); player.getItem("coin1");player.getItem("coin2");player.getItem("coin3");
                  return;}
            if (currentRoom.equals(frontCastle)&&player.getInventory().containsKey("guardKey") ){
                System.out.println("Good luck adventurer!");
            return;}
            else if (currentRoom.equals(frontCastle))
                System.out.println("Hey!!! You can't pass here! But if you find me 3 coins then maybe I can offer little help...Don't tell anyone");
        return;}
        if (character.equals("seller")){
            if(player.getInventory().containsKey("goldenEgg")&&currentRoom.equals(store)){
                System.out.println("I will get this egg, now you can get that sword, some other thing too if you want!");
            return;}
            else if(player.getInventory().containsKey("sword")&&currentRoom.equals(store)){
                System.out.println("I will close the store soon, hurry up!"); return;
            }
            else {System.out.println("Talk to me when you have the golden egg"); return;}
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
                System.out.println("You have successfully enter the castle with the guard key, now try to find something useful here and escape before anyone notice...");
                currentRoom = insideCastle;
                System.out.println(currentRoom.getShortDescription());
                return;
            }
            else if (currentRoom.equals(frontCastle) && !player.getInventory().containsKey("guardKey")){
                System.out.println ("You got arrested by the guard for illegally attempt to enter the castle");
                System.out.println("You spend the rest of your life in prison");
                die();
                return;
            }
        }

        else if(nextRoom.equals(innerCave)){
          if (currentRoom.equals(cave) &&checkLight() == true)   {
                 currentRoom = innerCave;
             System.out.println(currentRoom.getShortDescription());
             return;}
            else if (currentRoom == cave &&checkLight() == false){
             System.out.println ("You got killed by the dark knight, remember to get a light next time!");
             die();
             return;
         }
        }










        else {
            //System.out.println("assignment is happening here");
            currentRoom = nextRoom;
            System.out.println("Current Room: "+currentRoom); //how to print name?
            System.out.println(currentRoom.getShortDescription());
        }
    }

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