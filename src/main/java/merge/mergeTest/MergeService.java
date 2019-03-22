package merge.mergeTest;

import java.util.ArrayList;
import java.util.List;

public class MergeService {

    List<Order> getOrder(List<Integer> orderIds){
        List<Order> orders = new ArrayList<>();
        Order order=null;
        for (Integer orderId:orderIds
             ) {
            order=new Order();
            order.setOrderId(orderId);
            order.setOrderNumber("huang"+orderId);
            orders.add(order);
        }

        return orders;
    }
}
