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

		// 制造房间
		outside = new Room("城堡外");
		lobby = new Room("大堂");
		pub = new Room("小酒吧");
		study = new Room("书房");
		bedroom = new Room("卧室");

		// 初始化房间的出口
		outside.setExits("east", lobby);
		outside.setExits("south", study);
		outside.setExits("west", pub);
		lobby.setExits("west", outside);
		pub.setExits("east", outside);
		study.setExits("north", outside);
		study.setExits("east", bedroom);
		bedroom.setExits("west", study);

		currentRoom = outside; // 从城堡门外开始
	}

	private void printWelcome() {
		System.out.println();
		System.out.println("欢迎来到城堡！");
		System.out.println("这是一个超级无聊的游戏。");
		System.out.println("如果需要帮助，请输入 'help' 。");
		System.out.println();
		showPrompt();
	}

	// 以下为用户命令

	private void goRoom(String direction) {
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("那里没有门！");
		} else {
			currentRoom = nextRoom;
			showPrompt();
		}
	}

	public void showPrompt() {
		System.out.println("现在你在" + currentRoom);
		System.out.print("出口有：");
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
						System.out.println("迷路了吗？你可以做的命令有：go bye help");
						System.out.println("如：\tgo east");
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
				System.out.println("非法输入！请再试一次。");
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
		System.out.println("感谢您的光临。再见！");
	}

}
