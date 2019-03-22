package merge.mergeTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MergeTest {
    class Request{
        int orderId;
        CompletableFuture<Order> orders =new CompletableFuture<>();
    }
    public static LinkedBlockingQueue<Request> queues = new LinkedBlockingQueue<>();
   public Order getOrders(int orderId) throws ExecutionException, InterruptedException {
       Request request =new Request();
        request.orderId=orderId;
      // request.orders=new CompletableFuture<>();
        queues.put(request);
        return request.orders.get();
    }
  public  void init(){
        ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
              int size=queues.size();
                System.out.println("size" + size);
              if (size>0) {

                  List<Integer> orderIds = new ArrayList<>();
                  Map<Integer, Request> requestMap = new HashMap<>();
                  for (int i = 0; i < size; i++) {
                      Request request = queues.poll();
                      orderIds.add(request.orderId);
                      requestMap.put(request.orderId, request);
                  }
                  List<Order> orders = new MergeService().getOrder(orderIds);
                  for (Order order : orders
                  ) {
                      requestMap.get(order.getOrderId()).orders.complete(order);
                  }
              }
            }
        },1,1, TimeUnit.SECONDS);
    }
}
