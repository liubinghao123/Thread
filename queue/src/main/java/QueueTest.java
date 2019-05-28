import java.util.concurrent.TimeUnit;

/**
 * Created by Keeper on 2019-05-27
 */
public class QueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue(10);
        for(int i=0;i<2;i++){
            new Thread(()->{
                while(true){
                    String val = System.currentTimeMillis() + "";
                    System.out.println(Thread.currentThread().getName() + "生产了" + val);
                    queue.put(val);
                }
            }).start();
        }

        for(int i=0;i<4;i++){
            new Thread(()->{
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "开始消费----------");
                    Object val = queue.take();
                    System.out.println(Thread.currentThread().getName() + "消费了" + val);
                }
            }).start();
        }

    }
}
