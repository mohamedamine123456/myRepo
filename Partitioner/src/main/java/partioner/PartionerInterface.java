package partioner;

import partioner.exception.ListPartitionException;

import java.util.List;

public interface PartionerInterface<T>{

    List<List<T>> partionate(List<T> input, int partitionMaxSize) throws ListPartitionException;

}
