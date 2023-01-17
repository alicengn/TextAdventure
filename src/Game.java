public class Game {
    private Room currentRoom;
    public Game(){
    }
    public static void main(String[]args){
        Game game = new Game();
        game.createRooms();
        game.play();
    }
    private void createRooms(){
        Room centerCastle = new Room("short desc");
        Room northCastle = new Room("short desc");
        Room southCastle = new Room("short desc");
        Room eastCastle = new Room("short desc");
        Room westCastle = new Room("short desc");
    }

    public void play(){
        printWelcome();
        boolean finished = false;
        while(!finished){

        }
        System.out.println("Thanks for playing!");
    }
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in front of a castle, hunting for treasure!");
        System.out.println("Type \"help\"if you need assistance");
        System.out.println();
        System.out.println("Player, you are standing in front of a castle, the main gate is guarded securely");
    }
}
