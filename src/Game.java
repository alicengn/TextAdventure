public class Game {

    private Room currentRoom;
    Room innerCave;
    Room darkPit;
    Room insideCastle;
    private Parser parser;
    private Player player;
    boolean finished = false;


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
        Room frontCastle = new Room("the main gate is guarded securely, I don't think those guard will let us in...","This look like the front of the castle, some of the guard standing there, maybe they know something. There is a road leading up north");
        Room backCastle = new Room("they have a little gate here for seller to enter","");
        Room eastCastle = new Room("The wall is too high, I don't think it's climbable","");
        Room westCastle = new Room("those walls look so high,  there is also small hole in the walk","");
    Room insideCastle = new Room ("You are inside the castle now!","long descb");

        Room store = new Room("adventurer, this AMAZING STORE might have something you need for your adventure, take a look","This store have so many cool thing, but the owner is not around, I wonder where are they... If I grab something, will there be any consequenses? It just a game anyway... ");
        Room cave = new Room("Wow, it's a dark cave, we need some sort of light!","" );
        Room innerCave = new Room("","" );
        Room northWestForest = new Room("This is a forest with big tree all around" ,"");
        Room eastForest = new Room("This road seems to be blocked by a tree" ,"");
        Room lake = new Room("The lake doesn't look too deep","");

        Room dungeon = new Room("uhhhhh, an abandoned dungeon? ","" );
        Room dragonCage = new Room("WAIT WAIT what is that!!!!! A DRAGON??","" );

        Room darkPit = new Room("You got eaten by a huge evil knight","You got eaten by a huge evil knight");
       
        frontCastle.setExit("north", cave);
        frontCastle.setExit("west", westCastle);
        frontCastle.setExit("east", eastCastle);
        frontCastle.setExit("inside", frontCastle);

        eastCastle.setExit("north", frontCastle);
        eastCastle.setExit("south", backCastle);
        eastCastle.setExit("east", eastForest);
        eastCastle.setExit("west", eastCastle);

        westCastle.setExit("north", frontCastle);
        westCastle.setExit("south", backCastle);
        westCastle.setExit("west", dragonCage);
        westCastle.setExit("east", westCastle);

        backCastle.setExit("east", westCastle);
        backCastle.setExit("north", frontCastle);
        backCastle.setExit("south", store);
        backCastle.setExit("west", eastCastle);

        dungeon.setExit("inside", dragonCage);
        dungeon.setExit("east", eastCastle);

        store.setExit("north", backCastle);
        cave.setExit("enter",innerCave);
        innerCave.setExit("south", frontCastle);
        innerCave.setExit("west", northWestForest);
        innerCave.setExit("east", lake);

        Item coin3 = new Item();
        Item lighter = new Item();
        Item coin1 = new Item();
        Item coin2 = new Item();

frontCastle.setItem("lighter", lighter);
        frontCastle.setItem("coin1", coin1);

        frontCastle.setItem("coin2", coin2);

        frontCastle .setItem("coin3", coin3);


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

    private boolean die(){
        if (currentRoom == "darkPit")
        { System.out.println("Too bad Adventurer, you died...");
        finished = true;
        return true}

        return false;
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

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
            case ENTER:
                    enter(command);
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
        else
            System.out.println("You dont have lighter!");
            return false;
    }
private void enter(Command command){
        if (!command.hasSecondWord()){
            System.out.println("Where do you want to enter?");}

        String location = command.getSecondWord();
        if (command.hasSecondWord()){
            if (command.getSecondWord()=="cave"){
         if (player.hasKey("cave")) {
                System.out.println("You got eaten by the dark knight");
                //finished=true;
            }
            else if (!player.hasKey("cave") ){currentRoom = innerCave;}}

            if (player.hasKey("castle")){
                currentRoom=insideCastle;
        }}

}


    private void grab(Command command) {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Grab what?");
            return;
        }

        String key = command.getSecondWord();
        Item grabItem = currentRoom.getItem(key);


        if (grabItem == null) {
            System.out.println("You can't grab " + command.getSecondWord());
        }
        else {
            player.setItem(key, grabItem);

        }
    }
    private void talk (Command command){
        if (!command.hasSecondWord()){
            System.out.println("Who do you want to talk to?");
            return;
        }

       if (command.getSecondWord().equals("guard")){
                if (player.getInventory().containsKey("coin1") &&player.getInventory().containsKey("coin2")&&player.getInventory().containsKey("coin3") ){
              System.out.println("Thanks, now hold this key to go inside, don't  tell anyone I give you this!");
             player.setItem("guardKey", new Item() ); player.getItem("coin1");player.getItem("coin2");player.getItem("coin3;");}
           if (player.getInventory().containsKey("guardKey") ){
               System.out.println("Goodluck adventurer!");}
       else
                 System.out.println("Hey!!! You can't pass here! But if you find me 3 coins then maybe I can offer little help...Don't tell anyone");

         }}

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

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
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
    System.out.println()
    }*/

