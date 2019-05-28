import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Keeper on 2019-05-27
 * 使用ReentrantLock Condition 单链表实现阻塞队列
 */
public class LinkedBlockingQueue implements BlockingQueue {
    private volatile Node head;
    private volatile Node tail;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void put(Object value) {
        lock.lock();
        try {
            Node h = new Node();
            h.setVal(value);
            if(tail != null){
                tail.setNext(h);
                tail = h;
            }else{
                head = tail = h;
            }
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while(true){
                if(head == null) {
                    condition.await();
                }else{
                    Node h = head;
                    if (tail == head){
                        tail = head = null;
                    }else{
                        head = h.getNext();
                    }
                    return h.getVal();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }
}
