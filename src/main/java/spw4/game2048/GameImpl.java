package spw4.game2048;

public class GameImpl implements Game {

  public class Tile {

    private int row;
    private int col;
    private int value;

    /**
     * Keep track of the last round in which this tile was merged to avoid multiple merges per move
     */
    private int lastMergedInRound = -1;

    /**
     * Creates a new tile
     */
    public Tile(int value) {
      this.value = value;
    }

    /**
     * Creates a new tile and adds it to the internal storage of the linked Game object
     */
    public Tile(int value, int row, int col) {
      this.value = value;
      board[row][col] = this;
      this.row = row;
      this.col = col;
    }

    /**
     * Moves the current tile to the desired direction. This method will take care of merges for the current and next tile.
     */
    public void move(Direction direction) {

      boolean shouldContinueMove = true;

      int rowDelta = 0;
      int colDelta = 0;

      switch (direction) {
        case up -> rowDelta--;
        case down -> rowDelta++;
        case left -> colDelta--;
        case right -> colDelta++;
      }

      do {
        boolean isOutOfBounds = row + rowDelta >= TILE_COUNT || col + colDelta >= TILE_COUNT
                || row + rowDelta < 0 || col + colDelta < 0;

        if (isOutOfBounds) {
          break;
        }

        Tile neighbour = board[row + rowDelta][col + colDelta];
        if (neighbour == null) {
          // move
          board[row][col] = null;
          row += rowDelta;
          col += colDelta;
          board[row][col] = this;
        } else {
          // we only want to merge a tile one time per move
          boolean mergeAllowed = this.lastMergedInRound < movesCounter && neighbour.lastMergedInRound < movesCounter;
          boolean shouldMerge = neighbour.equals(this) && mergeAllowed;
          if (shouldMerge) {
            board[neighbour.row][neighbour.col] = null;
            this.value *= 2;
            score += this.value;
            this.lastMergedInRound = movesCounter;
          } else {
            shouldContinueMove = false;
          }
        }
      }
      while (shouldContinueMove);
    }

    public boolean isEmpty() {
      return value == 0;
    }

    public int getRow() {
      return row;
    }

    public void setRow(int row) {
      this.row = row;
    }

    public int getCol() {
      return col;
    }

    public void setCol(int col) {
      this.col = col;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof Tile t)) {
        return false;
      }
      return this.value == t.value;
    }

    @Override
    public String toString() {
      return value + " " + row + " " + col;
    }

    public int getLastMergedInRound() {
      return lastMergedInRound;
    }

    public void setLastMergedInRound(int lastMergedInRound) {
      this.lastMergedInRound = lastMergedInRound;
    }
  } // Tile

  /**
   * Index range for board access row is from 0 to 3
   */
  public static final int TILE_COUNT = 4;
  private int score = 0;
  private int movesCounter = 0;
  private boolean isOver = false;

  protected Tile[][] board = new Tile[TILE_COUNT][TILE_COUNT];

  GameRandomGenerator generator;
  BoardUtil boardUtil;

  public GameImpl() {
    generator = new GameRandomGeneratorImpl();
    boardUtil = new BoardUtilImpl(board);
  }

  public GameImpl(GameRandomGenerator generator) {
    this.generator = generator;
    boardUtil = new BoardUtilImpl(board);
  }

  /**
   * Initializes the internal board state with two valid tiles.
   * All other tiles are filled with 0 (=empty)
   */
  public void initialize() {
    boardUtil.clear();

    int setTileCount = 0;
    do {
      int row = generator.generateIndex();
      int col = generator.generateIndex();
      int tileValue = generator.generateTile();

      if (!isTileEmpty(row, col))
        continue;

      board[row][col] = new Tile(tileValue, row, col);
      setTileCount++;

    } while (setTileCount < 2);
  }

  public int getMoves() {
    return movesCounter;
  }

  public int getScore() {
    return score;
  }

  public int getValueAt(int row, int col) {
    checkBounds(row, col);
    return board[row][col] == null ? 0 : board[row][col].getValue();
  }

  public boolean isOver() {
    return isOver;
  }

  public boolean isWon() {
    return boardUtil.isWon();
  }

  public void move(Direction direction) {
    movesCounter++;
    moveTiles(direction);
    generateNewTile();
  }

  private void moveTiles(Direction direction) {
    switch (direction) {
      case up -> moveTilesUp();
      case left -> moveTilesLeft();
      case down -> moveTilesDown();
      case right -> moveTilesRight();
    }
  }

  private void moveTilesRight() {
    for (int currentRow = 0; currentRow < TILE_COUNT; currentRow++) {
      for (int i = TILE_COUNT - 1; i >= 0; i--) {
        if (board[currentRow][i] != null) {
          Tile currentTile = board[currentRow][i];
          currentTile.move(Direction.right);
        }
      }
    }
  }

  private void moveTilesDown() {
    for (int currentColumn = 0; currentColumn < TILE_COUNT; currentColumn++) {
      for (int currentRow = TILE_COUNT - 1; currentRow >= 0; currentRow--) {
        if (board[currentRow][currentColumn] != null) {
          Tile currentTile = board[currentRow][currentColumn];
          currentTile.move(Direction.down);
        }
      }
    }
  }

  private void moveTilesLeft() {
    for (int currentRow = 0; currentRow < TILE_COUNT; currentRow++) {
      for (int currentColumn = 0; currentColumn < TILE_COUNT; currentColumn++) {
        if (board[currentRow][currentColumn] != null) {
          Tile currentTile = board[currentRow][currentColumn];
          currentTile.move(Direction.left);
        }
      }
    }
  }

  private void moveTilesUp() {
    for (int currentColumn = 0; currentColumn < TILE_COUNT; currentColumn++) {
      for (int currentRow = 0; currentRow < TILE_COUNT; currentRow++) {
        if (board[currentRow][currentColumn] != null) {
          Tile currentTile = board[currentRow][currentColumn];
          currentTile.move(Direction.up);
        }
      }
    }
  }

  /**
   * Returns if the tile at row, col is empty (=value is 0)
   *
   * @return Returns true if the tile is empty, else false
   */
  private boolean isTileEmpty(int row, int col) {
    checkBounds(row, col);
    return board[row][col] == null || board[row][col].getValue() == 0;
  }

  private void checkBounds(int row, int col) {
    if (row >= TILE_COUNT)
      throw new IndexOutOfBoundsException("Row index (" + row + ") is >= " + TILE_COUNT);

    if (col >= TILE_COUNT)
      throw new IndexOutOfBoundsException("Column index (" + col + ") is >= " + TILE_COUNT);
  }

  /**
   * Generates a new tile and put it into a random position.
   * If the board is full, the internal state isOver will be set to true.
   */
  private void generateNewTile() {
    if (boardUtil.isFull()) {
      isOver = true;
      return;
    }

    Tile newTile = null;
    do {
      int row = generator.generateIndex();
      int col = generator.generateIndex();
      int tileValue = generator.generateTile();

      if (!isTileEmpty(row, col))
        continue;
      newTile = new Tile(tileValue, row, col);
    } while (newTile == null);
  }

  @Override
  public String toString() {
    StringBuilder boardString = new StringBuilder();
    boardString.append("---------------------------------\n");
    for (int row = 0; row < TILE_COUNT; row++) {
      for (int col = 0; col < TILE_COUNT; col++) {
        if (board[row][col] == null) {
          boardString.append("." + " | ");
        } else {
          boardString.append(board[row][col].getValue()).append(" | ");
        }
      }
      boardString.append("\n");
    }
    boardString.append("---------------------------------\n");
    return boardString.toString();
  }
}
