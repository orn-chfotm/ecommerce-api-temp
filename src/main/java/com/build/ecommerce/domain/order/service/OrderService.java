package com.build.ecommerce.domain.order.service;

import com.build.ecommerce.domain.order.dto.reposonse.OrderDetail;
import com.build.ecommerce.domain.order.dto.reposonse.OrderRequest;
import com.build.ecommerce.domain.order.dto.request.OrderResponse;
import com.build.ecommerce.domain.order.entity.Order;
import com.build.ecommerce.domain.order.entity.OrderProduct;
import com.build.ecommerce.domain.order.repository.OrderRepository;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.exception.ProductNotFountException;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import com.build.ecommerce.domain.user.entity.User;
import com.build.ecommerce.domain.user.exception.UserNotFountException;
import com.build.ecommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderRequest request){
        User orderer = userRepository.findById(request.userId())
                .orElseThrow(UserNotFountException::new);

        List<OrderDetail> orders = request.orders();

        Order newOrder = Order.builder()
                .user(orderer)
            .build();

        orders
            .forEach(o -> {
                Long productId = o.productId();
                Product findProduct = productRepository.findById(productId)
                        .orElseThrow(ProductNotFountException::new);

                OrderProduct orderProduct = OrderProduct.builder()
                        .product(findProduct)
                        .quantity(o.quantity())
                        .totalPrice(findProduct.getPrice().multiply(BigDecimal.valueOf(o.quantity())))
                        .build();

                newOrder.addOrder(orderProduct);
            });

        orderRepository.save(newOrder);

        return OrderResponse.builder()
                    .orderNumber(newOrder.getOrderNumber())
                .build();
    }
}
