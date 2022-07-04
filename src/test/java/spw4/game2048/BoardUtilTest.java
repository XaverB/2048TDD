package spw4.game2048;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class BoardUtilTest {

  GameImpl game;

  @BeforeEach
  void setup() {
    game = new GameImpl();
  }

  @Test
  @DisplayName("BoardUtilTest.isFull when is full returns true")
  void isFullWhenIsFullReturnsTrue() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, game.new Tile(1, 0, 0)));
    BoardUtil sut = new BoardUtilImpl(board);

    assertTrue(sut.isFull());
  }

  @Test
  @DisplayName("BoardUtilTest.isFull when is empty returns false")
  void isFullWhenIsEmptyReturnsFalse() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    BoardUtil sut = new BoardUtilImpl(board);
    assertFalse(sut.isFull());
  }

  @Test
  @DisplayName("BoardUtilTest.isFull when is not full returns false")
  void isFullWhenIsNotFullReturnsFalse() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x,game.new Tile(1, 0, 0)));
    board[0][0] = null;
    BoardUtil sut = new BoardUtilImpl(board);

    assertFalse(sut.isFull());
  }

  @Test
  @DisplayName("BoardUtilTest.clean remove all tiles from board Return clean board")
  void cleanWhenIsNotEmpty() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    GameImpl.Tile[][] expectedBoard = new GameImpl.Tile[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x,  game.new Tile(1, 0, 0)));
    BoardUtil sut = new BoardUtilImpl(board);
    sut.clear();

    assertArrayEquals(expectedBoard, board);
  }

  @Test
  @DisplayName("BoardUtilTest.isWon contains win value return true")
  void isWonContainsWinValueReturnsTrue() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, game.new Tile(1, 0, 0)));
    board[0][0] = game.new Tile(2048, 0, 3);
    BoardUtil sut = new BoardUtilImpl(board);

    assertTrue(sut.isWon());
  }

  @Test
  @DisplayName("BoardUtilTest.isWon no Win Value return false")
  void isWonContainsNoWinValueReturnsFalse() {
    GameImpl.Tile[][] board = new GameImpl.Tile[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, game.new Tile(1,0 ,0)));
    BoardUtil sut = new BoardUtilImpl(board);

    assertFalse(sut.isWon());
  }

}