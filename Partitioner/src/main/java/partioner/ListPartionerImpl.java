package partioner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import partioner.exception.ListPartitionErrors;
import partioner.exception.ListPartitionException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ListPartionerImpl<T> implements PartionerInterface<T> {

    private Logger _log = LoggerFactory.getLogger(ListPartionerImpl.class);

    @Override
    public List<List<T>> partionate(List<T> inputList, int partitionMaxSize) throws ListPartitionException {
        if (inputList == null) {
            _log.error("Input list cannot be null");
            throw new ListPartitionException(ListPartitionErrors.NULL_LIST_INPUT);
        }

        if (partitionMaxSize <= 0) {
            _log.error("Invalid partition size value : {}",partitionMaxSize);
            throw new ListPartitionException(ListPartitionErrors.INVALID_PARTITION_SIZE_VALUE);
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        List<List<T>> partitionList = inputList.stream()
                .collect(Collectors.groupingBy(it -> atomicInteger.getAndIncrement() / partitionMaxSize)).values().stream().collect(Collectors.toList());

        return partitionList;
    }
}