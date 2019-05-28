/**
 * Created by Keeper on 2019-05-27
 */
public interface BlockingQueue {
    void put(Object value);
    Object take();
}
