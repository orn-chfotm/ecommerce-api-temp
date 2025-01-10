package com.build.domain.order.service;

import com.build.domain.address.entity.AddressEntity;
import com.build.domain.address.exception.AddressNotFountException;
import com.build.domain.address.repository.AddressEntityRepository;
import com.build.domain.order.dto.reposonse.OrderRequest;
import com.build.domain.order.dto.request.OrderResponse;
import com.build.domain.order.dto.request.OrderedDetail;
import com.build.domain.order.dto.request.OrderedProductDeatilResponse;
import com.build.domain.order.dto.request.OrderedProductResponse;
import com.build.domain.order.entity.Order;
import com.build.domain.order.entity.OrderProduct;
import com.build.domain.order.entity.Status;
import com.build.domain.order.exception.OrderNotFountException;
import com.build.domain.order.repository.OrderRepository;
import com.build.domain.product.entity.Product;
import com.build.domain.product.exception.ProductNotFountException;
import com.build.domain.product.repository.ProductRepository;
import com.build.domain.member.user.entity.User;
import com.build.domain.member.user.exception.UserNotFountException;
import com.build.domain.member.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AddressEntityRepository addressEntityRepository;

    public OrderResponse createOrder(OrderRequest request){
        /* 주문자 정보 */
        User findUser = userRepository.findById(request.userId())
                .orElseThrow(UserNotFountException::new);

        /* 주문자 배송지 정보 */
        AddressEntity findUserAddr = addressEntityRepository.findById(request.addressId())
                .orElseThrow(AddressNotFountException::new);

        /* 주문 제품 목록*/
        List<OrderProduct> orderProducts = request.orders().stream()
                .map(order -> {
                    Product findProduct = productRepository.findById(order.productId())
                            .orElseThrow(ProductNotFountException::new);

                    /* 주문 수량 */
                    int quantity = order.quantity();
                    /*  제품 가격 * 주문 수량 */
                    BigDecimal multiply = findProduct.getPrice().multiply(BigDecimal.valueOf(quantity));

                    return OrderProduct.builder()
                            .product(findProduct)
                            .quantity(order.quantity())
                            .totalPrice(multiply)
                            .build();
                }).toList();

        Order saveOrder = Order.builder()
                .status(Status.COMPLET)
                .user(findUser)
                .address(findUserAddr.getAddress())
                .build();

        for (OrderProduct orderProduct : orderProducts) {
            saveOrder.addOrder(orderProduct);
        }

        orderRepository.save(saveOrder);

        return OrderResponse.toDto(saveOrder);
    }

    public OrderResponse cancelOrder(Long orderId) {
        Order findOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFountException::new);

        findOrder.cancle();

        return OrderResponse.toDto(findOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderDetails(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFountException::new);

        return user.getOrders().stream()
                .map(order -> {
                    List<OrderedDetail> orderedDetails = order.getOrderProducts().stream()
                            .map(orderProduct -> OrderedDetail.toDto(
                                            OrderedProductResponse.toDto(orderProduct),
                                            OrderedProductDeatilResponse.toDto(orderProduct.getProduct())
                                    )
                            ).toList();
                    return OrderResponse.toOrderedDetailDto(order, orderedDetails);
                }).toList();
    }

}
