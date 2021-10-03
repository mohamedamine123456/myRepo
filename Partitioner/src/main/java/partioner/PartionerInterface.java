package partioner;

import partioner.exception.ListPartitionException;

import java.util.Collection;
import java.util.List;

public interface PartionerInterface<T>{

    public Collection<List<T>> partionate(List<T> input, int partitionMaxSize) throws ListPartitionException;

}
