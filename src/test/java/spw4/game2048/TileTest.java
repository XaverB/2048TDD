package spw4.game2048;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

  GameImpl game;

  @BeforeEach
  void setup() {
    game = new GameImpl();
  }


  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  @Test
  void equalsInnerClassTileWithInvalidParameterReturnFalse(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    assertNotEquals(sut, "dummy");
  }

  @Test
  void equalsInnerClassTileSameTileReturnTrue(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    assertEquals(sut, sut);
  }

  @Test
  void equalsInnerClassTileWithSameValueReturnTrue(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    GameImpl.Tile dummy = game.new Tile(4,2,2);
    assertEquals(sut, dummy);
  }


  @ParameterizedTest(name = "value = {0}")
  @ValueSource(ints = {0,2,4,8,256})
  void getValueInnerClassTileWithValidValue(int value){
    GameImpl.Tile sut = game.new Tile(value,0,0);
    assertEquals(value, sut.getValue());
  }

  @ParameterizedTest(name = "value = {0}")
  @ValueSource(ints = {0,2,4,8,256})
  void setValueInnerClassTileWithValidValue(int value){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setValue(value);
    assertEquals(value, sut.getValue());
  }

  @Test
  void isEmptyInnerClassTileReturnTrue(){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    assertTrue(sut.isEmpty());
  }

  @ParameterizedTest(name = "rowNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  void getRowInnerClassTileReturnRowNumber(int rowNumber){
    GameImpl.Tile sut = game.new Tile(0,rowNumber,0);
    assertEquals(rowNumber, sut.getRow());
  }

  @ParameterizedTest(name = "rowNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  void setRowInnerClassTileReturnRowNumber(int rowNumber){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setRow(rowNumber);
    assertEquals(rowNumber, sut.getRow());
  }

  @ParameterizedTest(name = "colNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  void getColInnerClassTileReturnColNumber(int colNumber){
    GameImpl.Tile sut = game.new Tile(0,0,colNumber);
    assertEquals(colNumber, sut.getCol());
  }

  @ParameterizedTest(name = "colNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  void setColInnerClassTileReturnColNumber(int colNumber){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setCol(colNumber);
    assertEquals(colNumber, sut.getCol());
  }


  @Test
  void toStringInnerClassTileReturnValidString(){
    GameImpl.Tile sut = game.new Tile(1,2,3);
    String result = "1 2 3";
    assertEquals(result, sut.toString());
  }


  @ParameterizedTest(name = "tileValue = {0}")
  @ValueSource(ints = {1,2,3,4})
  void getLastMergedInRoundInnerClassTileReturnLastMergedInRoundValue(int round){
    GameImpl.Tile sut = game.new Tile(1,2,3);
    sut.setLastMergedInRound(round);

    assertEquals(round, sut.getLastMergedInRound());
  }
}
