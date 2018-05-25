public class Array<E> {

    private E[] data;
    private int size;

    //构造函数,传入数组的容量capacity构造Array
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参数的构造函数,默认数组的容量capacity=10
    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i =0 ; i< arr.length ; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //获取数组的元素个数
    public int getSize(){
        return size;
    }
    //返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }
    //向所有元素后添加一个新元素
    public void addLast(E e){

        add(size,e);
    }

    public void addFirst(E e){
        add(0,e);
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("add failed. Require index >= 0 and index <= size");

        if(size == data.length)
            resize(2 * data.length);

        for (int i = size -1; i >= index ; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        return data[index];
    }

    public void set(int index , E e) {
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    public boolean contains(E e) {
        for(int i = 0 ; i< size ;i++) {
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal");

        E ret = data[index];
        for(int i = index+1; i< size ; i++)
            data[i-1] = data[i];
        size --;
        data[size] = null; //loitering objects != memory leak

        if(size == data.length /4 && data.length/2 !=0)
        //数组容量不能为0
        {
            resize(data.length /2);
        }

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size -1);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(find(e));
    }

    public void swap(int i , int j){
        if (i < 0 || i >=size || j<0 || j>=size)
            throw new IllegalArgumentException("index is illegal.");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();

    }

    public void resize(int NewCapacity) {
        E[] newData = (E[])new Object[NewCapacity];
        for(int i = 0 ; i<size ; i++)
            newData[i]  = data[i];
        data = newData;
    }
}
