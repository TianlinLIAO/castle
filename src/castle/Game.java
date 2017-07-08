package castle;

import java.util.Scanner;

public class Game {
	private Room currentRoom;
	
	public Game() 
    {
        createRooms();
    }

	private void createRooms() {
		Room outside, lobby, pub, study, bedroom;

		// Create new rooms
		outside = new Room("Outside");
		lobby = new Room("Lobby");
		pub = new Room("Pub");
		study = new Room("Study");
		bedroom = new Room("Bedroom");

		// Initiate the exits
		outside.setExits("east", lobby);
		outside.setExits("south", study);
		outside.setExits("west", pub);
		lobby.setExits("west", outside);
		pub.setExits("east", outside);
		study.setExits("north", outside);
		study.setExits("east", bedroom);
		bedroom.setExits("west", study);

		currentRoom = outside; // Game starts from outside of castle
	}

	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the castle！");
		System.out.println("This is a super boring game.");
		System.out.println("If you need help, please input 'help'.");
		System.out.println();
		showPrompt();
	}

	// Below are user commands

	private void goRoom(String direction) {
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("There's no exist!");
		} else {
			currentRoom = nextRoom;
			showPrompt();
		}
	}

	public void showPrompt() {
		System.out.println("Now you are at the " + currentRoom);
		System.out.print("Exists are：");
		System.out.print(currentRoom.getExitDesc());
		System.out.println();
	}

	public void play() {
		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			String[] words = line.split(" ");
			Handler handler=null;
			String value="";
			if(words[0].equals("help")){
				handler=new Handler() {
					@Override
					public void doCmd(String word) {
						System.out.println("You got lost? You could use these commands：go bye help");
						System.out.println("For example：\tgo east");
					}
				};
			}
			else if(words[0].equals("bye")){
				break;
			}
			else if(words[0].equals("go")){
				if(words.length>1) 
					value=words[1];
				handler=new Handler() {
					@Override
					public void doCmd(String word) {
						goRoom(word);
					}
				};
			}
			else {
				System.out.println("Invalid input! Please try again.");
				continue;
			}
			handler.doCmd(value);
		}
		in.close();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();
		System.out.println("Thank you! Bye bye!");
	}

}
