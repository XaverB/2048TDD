package spw4.game2048;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BoardUtilImpl implements BoardUtil {
  int[][] board;

  public BoardUtilImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public boolean isWon() {
    for (int row = 0; row < GameImpl.TILE_COUNT; row++) {
      for (int col = 0; col < GameImpl.TILE_COUNT; col++) {
        if (board[row][col] == 2048)
          return true;
      }
    }
    return false;
  }

  @Override
  public boolean isFull() {
    for (int row = 0; row < GameImpl.TILE_COUNT; row++) {
      for (int col = 0; col < GameImpl.TILE_COUNT; col++) {
        if (board[row][col] == 0)
          return false;
      }
    }
    return true;
  }

  public void clear() {
    Arrays.stream(board).forEach(x -> Arrays.fill(x, 0));
  }
}
