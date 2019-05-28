import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-27
 */
public class QueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue();
        for(int i=0;i<2;i++){
            new Thread(()->{
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String val = System.currentTimeMillis() + "";
                    System.out.println(Thread.currentThread().getName() + "生产了" + val);
                    queue.put(val);
                }
            }).start();
        }

        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    System.out.println(Thread.currentThread().getName() + "开始消费----------");
                    Object val = queue.take();
                    System.out.println(Thread.currentThread().getName() + "消费了" + val);
                }
            }).start();
        }

    }
}
