package com.hj.tj.gohome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.tj.gohome.config.handler.ServiceException;
import com.hj.tj.gohome.config.handler.ServiceExceptionEnum;
import com.hj.tj.gohome.entity.Order;
import com.hj.tj.gohome.entity.Owner;
import com.hj.tj.gohome.entity.RelPassengerOrder;
import com.hj.tj.gohome.enums.StatusEnum;
import com.hj.tj.gohome.mapper.OrderMapper;
import com.hj.tj.gohome.mapper.OwnerMapper;
import com.hj.tj.gohome.mapper.RelPassengerOrderMapper;
import com.hj.tj.gohome.service.OrderService;
import com.hj.tj.gohome.utils.OwnerContextHelper;
import com.hj.tj.gohome.vo.order.OrderSaveParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private RelPassengerOrderMapper relPassengerOrderMapper;

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveOrder(OrderSaveParam orderSaveParam) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("owner_id", OwnerContextHelper.getOwnerId());
        orderQueryWrapper.eq("origin", orderSaveParam.getOrigin());
        orderQueryWrapper.eq("destination", orderSaveParam.getDestination());
        orderQueryWrapper.eq("expect_date", orderSaveParam.getExpectDate());
        orderQueryWrapper.eq("train_number", orderSaveParam.getTrainNumber());
        orderQueryWrapper.eq("status", 1);
        List<Order> orderList = orderMapper.selectList(orderQueryWrapper);
        if (!CollectionUtils.isEmpty(orderList)) {
            for (Order order : orderList) {
                if (Math.abs(order.getCreatedAt().getTime() - System.currentTimeMillis()) >= 3000) {
                    throw new ServiceException(ServiceExceptionEnum.ORDER_REPEAT);
                }
            }
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderSaveParam, order);

        order.setCreator(OwnerContextHelper.getOwnerId().toString());
        order.setOwnerId(OwnerContextHelper.getOwnerId());
        order.setUpdater(OwnerContextHelper.getOwnerId().toString());

        Owner owner = ownerMapper.selectById(OwnerContextHelper.getOwnerId());
        if (Objects.isNull(owner)) {
            throw new ServiceException(ServiceExceptionEnum.OWNER_NOT_EXISTS);
        }
        if (!StringUtils.isEmpty(orderSaveParam.getWxAccount())) {
            owner.setWxAccount(orderSaveParam.getWxAccount());
        }
        owner.setPhone(orderSaveParam.getPhone());
        owner.setUpdatedAt(new Date());

        ownerMapper.updateById(owner);

        if (Objects.nonNull(order.getId())) {
            // 更新
            order.setUpdatedAt(new Date());
            order.setUpdater(OwnerContextHelper.getOwnerId().toString());
            orderMapper.updateById(order);
        } else {
            // 插入
            orderMapper.insert(order);
        }

        saveRelPassengerOrder(order.getId(), orderSaveParam.getPassengerIdList());

        return order.getId();
    }

    private void saveRelPassengerOrder(Integer orderId, List<Integer> passengerIdList) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", orderId);

        RelPassengerOrder relPassengerOrder = new RelPassengerOrder();
        relPassengerOrder.setStatus(StatusEnum.DELETED.getStatus());

        relPassengerOrderMapper.update(relPassengerOrder, queryWrapper);

        for (Integer passengerId : passengerIdList) {
            RelPassengerOrder tempRelPassengerOrder = new RelPassengerOrder();
            tempRelPassengerOrder.setCreator(OwnerContextHelper.getOwnerId().toString());
            tempRelPassengerOrder.setOrderId(orderId);
            tempRelPassengerOrder.setPassengerId(passengerId);
            tempRelPassengerOrder.setUpdater(OwnerContextHelper.getOwnerId().toString());

            relPassengerOrderMapper.insert(tempRelPassengerOrder);
        }
    }
}
