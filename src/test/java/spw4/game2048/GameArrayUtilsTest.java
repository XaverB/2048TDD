package spw4.game2048;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

class GameArrayUtilsTest {

  @Test
  void moveZerosToRightWhenMixedInput() {
    int[] array = new int[]{0, 2, 2, 0};
    int[] expected = new int[]{2, 2, 0, 0};
    GameArrayUtils.moveZerosToRight(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToWhenOnlyZero() {
    int[] array = new int[]{0, 0, 0, 0};
    int[] expected = new int[]{0, 0, 0, 0};
    GameArrayUtils.moveZerosToRight(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToRightMixesInput() {
    int[] array = new int[]{0, 1, 0, 1};
    int[] expected = new int[]{1, 1, 0, 0};
    GameArrayUtils.moveZerosToRight(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToLeft() {
    int[] array = new int[]{0, 2, 2, 0};
    int[] expected = new int[]{0, 0, 2, 2};
    GameArrayUtils.moveZerosToLeft(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToLeft2() {
    int[] array = new int[]{2, 2, 2, 2};
    int[] expected = new int[]{2, 2, 2, 2};
    GameArrayUtils.moveZerosToLeft(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToLeft3() {
    int[] array = new int[]{0,0,0,0};
    int[] expected = new int[]{0,0,0,0};
    GameArrayUtils.moveZerosToLeft(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void moveZerosToLeft4() {
    int[] array = new int[]{2,0,2,0};
    int[] expected = new int[]{0,0,2,2};
    GameArrayUtils.moveZerosToLeft(array, array.length);
    assertArrayEquals(expected, array);
  }

  @Test
  void mergeRight() {
    int[] array = new int[]{0,0,2,2};
    int[] expected = new int[]{0,0,0,4};
    GameArrayUtils.mergeValuesRight(array, array.length);
    assertArrayEquals(expected, array);
  }


}