package spw4.game2048;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
  @DisplayName("Tile.equals with invalid argument return false")
  @Test
  void equalsInnerClassTileWithInvalidParameterReturnFalse(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    assertNotEquals("dummy",sut);
  }

  @Test
  @DisplayName("Tile.equals with same Tile return true")
  void equalsInnerClassTileSameTileReturnTrue(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    assertEquals(sut, sut);
  }

  @Test
  @DisplayName("Tile.equals with other Tile return true")
  void equalsInnerClassTileWithSameValueReturnTrue(){
    GameImpl.Tile sut = game.new Tile(4,0,0);
    GameImpl.Tile dummy = game.new Tile(4,2,2);
    assertEquals(sut, dummy);
  }


  @ParameterizedTest(name = "value = {0}")
  @ValueSource(ints = {0,2,4,8,256})
  @DisplayName("Tile.getValue with valid argument return value")
  void getValueInnerClassTileWithValidValue(int value){
    GameImpl.Tile sut = game.new Tile(value,0,0);
    assertEquals(value, sut.getValue());
  }

  @ParameterizedTest(name = "value = {0}")
  @ValueSource(ints = {0,2,4,8,256})
  @DisplayName("Tile.setValue with valid argument return value")
  void setValueInnerClassTileWithValidValue(int value){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setValue(value);
    assertEquals(value, sut.getValue());
  }

  @Test
  @DisplayName("Tile.isEmtpy with value zero return true")
  void isEmptyInnerClassTileReturnTrue(){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    assertTrue(sut.isEmpty());
  }

  @ParameterizedTest(name = "rowNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  @DisplayName("Tile.getRow with valid value return value")
  void getRowInnerClassTileReturnRowNumber(int rowNumber){
    GameImpl.Tile sut = game.new Tile(0,rowNumber,0);
    assertEquals(rowNumber, sut.getRow());
  }

  @ParameterizedTest(name = "rowNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  @DisplayName("Tile.setRow with valid value return value")
  void setRowInnerClassTileReturnRowNumber(int rowNumber){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setRow(rowNumber);
    assertEquals(rowNumber, sut.getRow());
  }

  @ParameterizedTest(name = "colNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  @DisplayName("Tile.getCol with valid value return value")
  void getColInnerClassTileReturnColNumber(int colNumber){
    GameImpl.Tile sut = game.new Tile(0,0,colNumber);
    assertEquals(colNumber, sut.getCol());
  }

  @ParameterizedTest(name = "colNumber = {0}")
  @ValueSource(ints = {0,1,2,3})
  @DisplayName("Tile.setCol with valid value return value")
  void setColInnerClassTileReturnColNumber(int colNumber){
    GameImpl.Tile sut = game.new Tile(0,0,0);
    sut.setCol(colNumber);
    assertEquals(colNumber, sut.getCol());
  }


  @Test
  @DisplayName("Tile.toString with valid arguments return arguments as string")
  void toStringInnerClassTileReturnValidString(){
    GameImpl.Tile sut = game.new Tile(1,2,3);
    String result = "1 2 3";
    assertEquals(result, sut.toString());
  }


  @ParameterizedTest(name = "tileValue = {0}")
  @ValueSource(ints = {1,2,3,4})
  @DisplayName("Tile.setLastMergedInRound with value return value ")
  void getLastMergedInRoundInnerClassTileReturnLastMergedInRoundValue(int round){
    GameImpl.Tile sut = game.new Tile(1,2,3);
    sut.setLastMergedInRound(round);

    assertEquals(round, sut.getLastMergedInRound());
  }
}
