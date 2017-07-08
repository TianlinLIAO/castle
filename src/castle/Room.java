package castle;

public class Room {
    public String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String description) 
    {
        this.description = description;
    }

    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
    }

    @Override
    public String toString()
    {
        return description;
    }
    
    public String getExitDesc(){
    	StringBuffer buffer=new StringBuffer();
    	if(northExit!=null){
    		buffer.append("north ");
    	}
    	if(eastExit!=null){
    		buffer.append("east ");
    	}
    	if(westExit!=null){
    		buffer.append("west ");
    	}
    	if(southExit!=null){
    		buffer.append("south ");
    	}
    	return buffer.toString();
    }
    
    public Room getExit(String direction){
    	Room nextRoom=null;
    	if(direction.equals("north")) {
            nextRoom = northExit;
        }
        if(direction.equals("east")) {
            nextRoom = eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = southExit;
        }
        if(direction.equals("west")) {
            nextRoom = westExit;
        }
    	return nextRoom;
    }
}
