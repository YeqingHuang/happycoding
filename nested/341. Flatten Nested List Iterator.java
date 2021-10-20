public class NestedIterator implements Iterator<Integer> {
    // method1: easiest one
    // all all numbers in the constructor and consume later
    List<Integer> numbers;
    int index;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.index = 0;
        this.numbers = new ArrayList<>();
        getAllNumbers(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return numbers.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < numbers.size();
    }
    
    private void getAllNumbers(List<NestedInteger> nestedList) {
        for (NestedInteger nested: nestedList) {
            if (nested.isInteger()) {
                numbers.add(nested.getInteger());
            } else {
                getAllNumbers(nested.getList());
            }
        }
    }
}

public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        // when curr nestedInteger is integer, consume it
        // otherwise we need to unwrap it (this step is done in hasNext())
        // [1,[4,[6],8],9]
        // one thing is that in stack, we push first but consume later
        // this is not what we want, we want push first and use first
        // therefore, just add each object in the reverse order
        stack = new Stack<>();
        for (int i=nestedList.size() - 1; i>=0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        // we want to make sure that the top of the stack is always an integer
        // so that it can be consumed by next()
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> topList = stack.pop().getList();
            for (int i=topList.size() - 1; i>=0; i--) {
                stack.push(topList.get(i));
            }
        }
        // as long as it's not empty, we have something to consume 
        return !stack.isEmpty();
    }
}
