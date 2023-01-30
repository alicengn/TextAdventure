public enum CommandWord {

    GO( "go"), QUIT( "quit"), HELP ( "help"), UNKNOWN ( "unknown"), LOOK("look"), GRAB("grab"), DROP("drop"), TALK ("talk"),
    ENTER("enter"), LIGHT ("light");

    private String commandString;

  CommandWord( String commandString){
        this.commandString=commandString;
    }
    public String toString(){
      return commandString;
    }
}
