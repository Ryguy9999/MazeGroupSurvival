import java.util.HashMap;

public class WorldState {
	int[] color;
	Controls controls;
	HashMap<Integer, Player> players;
	Tile[][] maze;
	Vector dashTarget;
	int id, mazeSeed, dashCooldown, it;

	public WorldState() {
		this(new int[] { 0, 0, 0 }, new Controls(), new HashMap<Integer, Player>(), new Tile[0][0], -1, (int)(Math.random() * Integer.MAX_VALUE), 0, null, 0);
	}

	public WorldState(int[] color, Controls controls, HashMap<Integer, Player> players, Tile[][] maze, int id, int mazeSeed, int dashCooldown, Vector dashTarget, int it) {
		this.color = color;
		this.controls = controls;
		this.players = players;
		this.maze = maze;
		this.id = id;
		this.mazeSeed = mazeSeed;
		this.dashCooldown = dashCooldown;
		this.dashTarget = dashTarget;
		this.it = it;
	}
}