package spw4.game2048;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class) class GameTest {
  // we do not want to use the constants from GameImpl,
  // because we are testing against an interface, which does not
  // have those values
  private final int ROW_COUNT = 4;
  private final int COL_COUNT = 4;


  Game sut;


  @BeforeEach
  void setup() {
    sut = new GameImpl();
  }



  @DisplayName("GameImpl.createGame returns new Game")
  @Test
  void createGame() {
    assertNotNull(sut);
    for (int row = 0; row < ROW_COUNT; row++) {
      for (int col = 0; col < COL_COUNT; col++) {
        assertEquals(0, sut.getValueAt(0, 0));
      }
    }
  }

  @DisplayName("GameImpl.initialize set two tiles to 2 or 4")
  @Test
  void initializeSetTwoTiles() {
    // arrange
    int expectedAtZeroZero = 2;
    int expectedAtZeroOne = 4;

    GameRandomGenerator randomStub = new GameRandomGeneratorStub(
            List.of(expectedAtZeroZero, expectedAtZeroOne,expectedAtZeroOne),
            List.of(0, 0, 0, 0, 0, 1)
    );

    sut = new GameImpl(randomStub);

    // act
    sut.initialize();

    // assert
    assertEquals(expectedAtZeroZero, sut.getValueAt(0, 0));
    assertEquals(expectedAtZeroOne, sut.getValueAt(0, 1));

    // assert everything else 0
    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        // do not check our set tile values when checking the 0 fields
        if(i != 0){
            assertEquals(0, sut.getValueAt(i,j));
        }
      }
    }
  }


  @DisplayName("GameImpl.isOver set return False")
  @Test
  void isOverReturnFalse(){
    assertFalse(sut.isOver());
  }


  @ParameterizedTest(name = "row = {0}, column is valid")
  @CsvSource( {"-4,", "-1", "4", "5"})
  void getValueAtWithInvalidRowArgumentThrowsIndexOutOfBoundsException(int row){
    assertThrows(IndexOutOfBoundsException.class, ()-> sut.getValueAt(row, 0));
  }

  @ParameterizedTest(name = "column = {0}, row is valid")
  @CsvSource( {"-4,", "-1", "4", "5"})
  void getValueAtWithInvalidColumnArgumentThrowsIndexOutOfBoundsException(int col){
    assertThrows(IndexOutOfBoundsException.class, ()-> sut.getValueAt(0, col));
  }

  @Test
  @DisplayName("GameImpl.isWon new Game return False")
  void isWonWhenIsNotWonReturnsFalse() {
    sut.initialize();
    assertFalse(sut.isWon());
  }

  @Test
  @DisplayName("GameImpl.isOver return true")
  void gameTestIsOver(){

    GameRandomGenerator randomStub = new GameRandomGeneratorStub(
            List.of(2, 4,8,16, 32,64,128, 256,512,1024,
                    2, 4,8,16, 32,64,128, 256,512,1024,
                    2, 4,8,16, 32,64,128, 256,512,1024),
            List.of(0, 0, 0, 0, 0, 0, 0, 0,0, 0,
                    0, 1, 0, 1, 0, 1, 0,1,
                    0, 2, 0, 2, 0, 2, 0,2,
                    0, 3, 0, 3, 0, 3, 0,3
            )
    );

    sut = new GameImpl(randomStub);
    for(int i = 0; i < 16; i++){
      sut.move(Direction.down);
    }
    sut.move(Direction.left);
    assertTrue(sut.isOver());
  }

  @Test
  @DisplayName("GameImpl.getScore new Game return zero")
  void getScoreNewGameReturnsZero(){
    sut.initialize();
    int expected = 0;
    int result = sut.getScore();

    assertEquals(expected, result);
  }

  @Test
  @DisplayName("GameImpl.getMoves return moves is 4 ")
  void getScoreNewGameReturnsFour(){
    GameRandomGenerator randomStub = new GameRandomGeneratorStub(
            List.of(2, 2,8),
            List.of(0, 0, 0, 1, 1,3)
    );
    sut = new GameImpl(randomStub);
    sut.initialize();
    sut.move(Direction.right);

    int expected = 4;
    int result = sut.getScore();

    assertEquals(expected, result);
  }

  @Test
  @DisplayName("GameImpl.getMoves return moves is zero ")
  void getMovesNewGameReturnsZero(){
    sut.initialize();
    int expected = 0;
    int result = sut.getMoves();

    assertEquals(expected, result);
  }



  @Test
  @DisplayName("GameImpl.getMoves return moves is One ")
  void getMovesNewGameReturnsOne(){
    sut.initialize();
    int expected = 1;
    sut.move(Direction.left);
    int result = sut.getMoves();


    assertEquals(expected, result);
  }



  @Test
  @DisplayName("GameImpl.isWon return true")
  void gameTestIsWonReturnTrue(){

    GameRandomGenerator randomStub = new GameRandomGeneratorStub(
            List.of(2, 4,8,16, 32,64,128, 256,512,1024,
                    2, 4,8,1024, 32,64,128, 256,512,1024,
                    2, 4,512,1024, 32,64,128, 256,512,1024),
            List.of(0, 0, 0, 0, 0, 0, 0, 0,0, 0,
                    0, 1, 0, 1, 0, 1, 0,1,
                    0, 2, 0, 2, 0, 2, 0,2,
                    0, 3, 0, 3, 0, 3, 0,3, 3,3
            )
    );

    sut = new GameImpl(randomStub);
    for(int i = 0; i < 16; i++){
      sut.move(Direction.down);
    }
    sut.move(Direction.left);
    assertTrue(sut.isWon());
  }

  @Test
  @DisplayName("GameImpl.toString  return valid String")
  void gameTestToString(){

    GameRandomGenerator randomStub = new GameRandomGeneratorStub(
            List.of(2, 4,2),
            List.of(0, 0, 0, 2, 0, 0
            )
    );

    sut = new GameImpl(randomStub);
    sut.initialize();
    String result = """
            ---------------------------------
            2 | . | 4 | . |\s
            . | . | . | . |\s
            . | . | . | . |\s
            . | . | . | . |\s
            ---------------------------------
            """;
    assertEquals(result, sut.toString());
  }



}