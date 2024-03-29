package spw4.game2048;

import java.util.Iterator;

public class GameRandomGeneratorStub implements GameRandomGenerator {
  private final Iterator<Integer> tileValueIterator;
  private final Iterator<Integer> indexValueIterator;

  public GameRandomGeneratorStub(Iterable<Integer> tileValues, Iterable<Integer> indexValues) {
    tileValueIterator = tileValues.iterator();
    indexValueIterator = indexValues.iterator();
  }

  @Override
  public int generateTile() {
    return tileValueIterator.next();
  }

  @Override
  public int generateIndex() {
    return indexValueIterator.next();
  }
}
