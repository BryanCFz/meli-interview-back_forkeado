package com.meli.interview.back.subscription_api.components.subscription.infraestructure.adapter.in.rest.response;

import com.meli.interview.back.subscription_api.subscription.Subscription;

import java.util.List;

public class SubscriptionUserDto {
    private Long UserId;
    private List<Subscription> susSubscriptionList;
    private Float totalCost;

}
