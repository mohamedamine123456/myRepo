List-partitioner:


A java library for partitioning a list into a list of sublists.


* How to install :

1. Clone the project from : https://github.com/mohamedamine123456/myRepo.git

2. Execute mvn install
* How to use it :

import partioner.ListPartionerImpl;
import partioner.exception.ListPartitionException;

import java.util.Arrays;
import java.util.List;

public class ListPartionerDemo {

    public static void main(String[] args) throws ListPartitionException {

        ListPartionerImpl partioner = new ListPartionerImpl();
        List<String> inputList = Arrays.asList("hello", "foo", "bar");
        List<List<String>> lists = partioner.partionate(inputList, 2);
        lists.forEach(l -> System.out.println(l));

    }
}

