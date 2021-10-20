import java.util.NoSuchElementException;

class Vector2D {
    int index;
    List<Integer> numbers;
    
    public Vector2D(int[][] vec) {
        this.index = 0;
        this.numbers = new ArrayList<>();
        getAllNumbers(vec);
    }
    
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers.get(index++);
    }
    
    public boolean hasNext() {
        return index < numbers.size();
    }
    
    private void getAllNumbers(int[][] vec) {
        for (int[] element: vec) {
            for (int num: element) {
                numbers.add(num);
            }
        }
    }
}

class Vector2D {
    int i, j;
    int[][] vec;
    // easier than 341. Flatten Nested List Iterator
    // because there are only two levels, two pointers are enough
    public Vector2D(int[][] vec) {
        this.i = 0; // out pointer
        this.j = 0; // inner pointer
        this.vec = vec;
    }
    
    public int next() {
        if (!hasNext()) {
            return -1;
        }
        return vec[i][j++];
    }
    
    public boolean hasNext() {
        while (i < vec.length && j == vec[i].length) {
            i++;
            j = 0;
        }
        return i < vec.length;
    }
}