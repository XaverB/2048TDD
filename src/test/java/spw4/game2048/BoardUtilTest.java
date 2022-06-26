package spw4.game2048;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BoardUtilTest {

  @Test
  void isFullWhenIsFullReturnsTrue() {
    int[][] board = new int[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 1));
    BoardUtil sut = new BoardUtilImpl(board);

    assertTrue(sut.isFull());
  }

  @Test
  void isFullWhenIsEmptyReturnsFalse() {
    int[][] board = new int[4][4];
    BoardUtil sut = new BoardUtilImpl(board);
    assertFalse(sut.isFull());
  }

  @Test
  void isFullWhenIsNotFullReturnsFalse() {
    int[][] board = new int[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 1));
    board[0][0] = 0;
    BoardUtil sut = new BoardUtilImpl(board);

    assertFalse(sut.isFull());
  }

  @Test
  void cleanWhenIsNotEmpty() {
    int[][] board = new int[4][4];
    int[][] expectedBoard = new int[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 1));
    BoardUtil sut = new BoardUtilImpl(board);
    sut.clear();

    assertArrayEquals(expectedBoard, board);
  }

  @Test
  void isWonContainsWinValueReturnsTrue() {
    int[][] board = new int[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 1));
    board[0][0] = GameImpl.WIN_VALUE;
    BoardUtil sut = new BoardUtilImpl(board);

    assertTrue(sut.isWon());
  }

  @Test
  void isWonContainsNoWinValueReturnsFalse() {
    int[][] board = new int[4][4];
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 1));
    BoardUtil sut = new BoardUtilImpl(board);

    assertFalse(sut.isWon());
  }

}