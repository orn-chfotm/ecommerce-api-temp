package com.build.ecommerce.domain.order.service;

import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import com.build.ecommerce.domain.order.dto.reposonse.OrderDetail;
import com.build.ecommerce.domain.order.dto.reposonse.OrderRequest;
import com.build.ecommerce.domain.order.dto.request.OrderResponse;
import com.build.ecommerce.domain.order.entity.Order;
import com.build.ecommerce.domain.order.entity.OrderProduct;
import com.build.ecommerce.domain.order.entity.Status;
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
import java.util.ArrayList;
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
        User findUser = userRepository.findById(request.userId())
                .orElseThrow(UserNotFountException::new);

        List<OrderDetail> orders = request.orders();

        List<OrderProduct> orderProducts = new ArrayList<>();
        orders
            .forEach(order -> {
                Long productId = order.productId();
                Product findProduct = productRepository.findById(productId)
                        .orElseThrow(ProductNotFountException::new);

                OrderProduct orderProduct = OrderProduct.builder()
                        .product(findProduct)
                        .quantity(order.quantity())
                        .totalPrice(findProduct.getPrice().multiply(BigDecimal.valueOf(order.quantity())))
                        .build();

                orderProducts.add((orderProduct));
            });

        AddressEntity addressEntity = findUser.getAddress().stream()
                .filter(add -> add.getId().equals(request.addressId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        System.out.println("addressEntity :: " + addressEntity);

        Order newOrder = Order.builder()
                .status(Status.COMPLET)
                .user(findUser)
                .address(addressEntity.getAddress())
                .build();

        for (OrderProduct orderProduct : orderProducts) {
            newOrder.addOrder(orderProduct);
        }

        orderRepository.save(newOrder);
        Order findOrder = orderRepository.findById(newOrder.getId())
                .orElseThrow(RuntimeException::new);

        System.out.println("findOrder.getOrderNumber() = " + findOrder.getOrderNumber());
        System.out.println("newOrder = " + newOrder.getId());

        return OrderResponse.builder()
                    .orderNumber(findOrder.getOrderNumber())
                .build();
    }
}
