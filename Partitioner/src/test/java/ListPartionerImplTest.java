
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import partioner.ListPartionerImpl;
import partioner.exception.ListPartitionErrors;
import partioner.exception.ListPartitionException;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListPartionerImplTest {

    public ListPartionerImpl partioner;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        partioner = new ListPartionerImpl();
    }

    @Test
    public void testValidPartioningIfListSizeIsDivisibleByPartitionMaxSize() throws ListPartitionException {
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Collection<List<Integer>> actual = partioner.partionate(inputList, 2);
        assertEquals(6, actual.size());
        actual.forEach(p -> assertTrue(p.size() == 2));
    }

    @Test
    public void testValidPartioningIfListSizeIsNotDivisibleByPartitionMaxSize() throws ListPartitionException {
        List<Integer> inputList = Arrays.asList(1, 2, 3);
        Collection<List<Integer>> actual = partioner.partionate(inputList, 2);
        assertEquals(2, actual.size());

        Iterator<List<Integer>> iterator = actual.iterator();

        List<Integer> firstSubList = iterator.next();
        assertEquals(2, firstSubList.size());

        List<Integer> secondSubList = iterator.next();
        assertEquals(1, secondSubList.size());
    }

    @Test
    public void testIfInputListSizeIsLowerThanPartitionMaxSizeReturnesOneSublist() throws ListPartitionException {
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Collection<List<Integer>> actual = partioner.partionate(inputList, 20);
        assertEquals(1, actual.size());
        assertEquals(actual.iterator().next(), inputList);
    }

    @Test
    public void testIfEmptyInputListReturnsOneEmptyList() throws ListPartitionException {
        List<Integer> inputList = new ArrayList<>();
        Collection<List<Integer>> actual = partioner.partionate(inputList, 2);
        assertEquals(0, actual.size());
    }

    @Test
    public void testIfListOfStringIsCorrectlyPartitioned() throws ListPartitionException {
        List<String> inputList = Arrays.asList("foo", "bar", "toto", "hello");
        Collection<List<String>> actual = partioner.partionate(inputList, 3);
        assertEquals(2, actual.size());

        Iterator<List<String>> iterator = actual.iterator();

        List<String> firstSubList = iterator.next();
        assertEquals(3, firstSubList.size());
        assertEquals(Arrays.asList("foo", "bar", "toto"), firstSubList);

        List<String> secondSubList = iterator.next();
        assertEquals(1, secondSubList.size());
        assertEquals(Arrays.asList("hello"), secondSubList);
    }

    @Test
    public void testIfNullInputListThrowsRightExceptionWithRightMessage() throws ListPartitionException {
        exceptionRule.expect(ListPartitionException.class);
        exceptionRule.expectMessage(ListPartitionErrors.NULL_LIST_INPUT);
        List<Integer> inputList = null;
        partioner.partionate(inputList, 2);
    }

    @Test
    public void testIfInvalidPartitionMaxValueThrowsRightExceptionWithRightMessage() throws ListPartitionException {
        exceptionRule.expect(ListPartitionException.class);
        exceptionRule.expectMessage(ListPartitionErrors.INVALID_PARTITION_SIZE_VALUE);
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        partioner.partionate(inputList, -1);
    }
}