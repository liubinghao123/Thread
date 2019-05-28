import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Keeper on 2019-05-27
 * 使用ReentrantLock Condition 单链表实现阻塞队列
 */
public class LinkedBlockingQueue implements BlockingQueue {
    private volatile Node head;
    private volatile Node tail;
    //队列长度
    private volatile int length;
    private int count;
    private ReentrantLock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition provider = lock.newCondition();

    public LinkedBlockingQueue(int count) {
        this.count = count;
    }

    public LinkedBlockingQueue() {
        this.count = 100;
    }

    public void put(Object value) {
        lock.lock();
        try {
            Node h = new Node();
            h.setVal(value);
            if(tail != null){
                //判断是否超过队列长度,阻塞生产者线程
                if(length >= count){
                    System.out.println("生成队列已满，线程【"+Thread.currentThread().getName()+"】阻塞等待");
                    provider.await();
                }else{
                    tail.setNext(h);
                    tail = h;
                }
            }else{
                head = tail = h;
                System.out.println("唤醒所有消费者线程");
                consumer.signalAll();
            }
            length++;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while(true){
                if(head == null) {
                    System.out.println("队列为空，消费者线程【"+Thread.currentThread().getName()+"】阻塞等待");
                    consumer.await();
                }else{
                    Node h = head;
                    //如果只剩下一个节点，即头结点和尾节点相等，同时置为空
                    if (tail == head){
                        tail = head = null;
                    }else{
                        head = h.getNext();
                    }
                    if(--length<=0){
                        //唤醒生产者线程
                        System.out.println("唤醒所有生产者线程");
                        provider.signalAll();
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
