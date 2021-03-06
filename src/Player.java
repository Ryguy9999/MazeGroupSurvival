import java.awt.Color;
import java.util.ArrayList;

public class Player {
	public static final int SPEED = 4, MAX_HEALTH = 15, KILL_ZONE_RADIUS = 150, NUM_KILL_ZONES = 4;
	public ArrayList<Vector> killZones;
	public Vector position, velocity;
	public Color color;
	public int health;

	public Player(Color c) {
		this(c, new ArrayList<Vector>());
	}

	public Player(Color c, ArrayList<Vector> k) {
		this(new Vector(MazeGame.TILE_SIZE * 1.5, MazeGame.TILE_SIZE * 1.5), c, MAX_HEALTH, k);
	}

	public Player(Vector pos, Color c, int h, ArrayList<Vector> k) {
		this(pos, new Vector(0, 0), c, h, k);
	}

	public Player(Vector pos, Vector vel, Color c, int h, ArrayList<Vector> k) {
		position = pos.clone();
		velocity = vel.clone();
		color = c;
		health = h;
		killZones = k;
	}

	public void movement(WorldState worldState) {
		Controls controls = worldState.controls;
		int speed = (int) (SPEED * (worldState.it == worldState.id ? 1.3 : 1));
		velocity.x = (controls.right ? speed : 0) + (controls.left ? -speed : 0);
		velocity.y = (controls.down ? speed : 0) + (controls.up ? -speed : 0);

		for (int i = 0; i < 5 && checkCollide(new Vector(position.x + velocity.x, position.y), worldState); i++) {
			velocity.x /= 2;
			if (i == 4) velocity.x = 0;
		}
		for (int i = 0; i < 5 && checkCollide(new Vector(position.x, position.y + velocity.y), worldState); i++) {
			velocity.y /= 2;
			if (i == 4) velocity.y = 0;
		}

		if (!checkCollide(new Vector(position.x + velocity.x, position.y + velocity.y), worldState)) position.doDelta(velocity);
	}

	private boolean checkCollide(Vector pos, WorldState worldState) {
		for (int x = (int) ((pos.x - MazeGame.PLAYER_SIZE) / MazeGame.TILE_SIZE); x < (pos.x + MazeGame.PLAYER_SIZE) / MazeGame.TILE_SIZE; x++)
			for (int y = (int) ((pos.y - MazeGame.PLAYER_SIZE) / MazeGame.TILE_SIZE); y < (pos.y + MazeGame.PLAYER_SIZE) / MazeGame.TILE_SIZE; y++)
				if (worldState.maze[x][y] == Tile.WALL) return true;

		if (pos.x < 0 || pos.y < 0 || pos.x > MazeGame.MAZE_SIZE * MazeGame.TILE_SIZE || pos.y > MazeGame.MAZE_SIZE * MazeGame.TILE_SIZE) return true;

		return false;
	}

	public String toString() {
		return "{" + position + ", " + velocity + ", " + color + "}";
	}
}
