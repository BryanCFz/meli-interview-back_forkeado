package com.meli.interview.back.subscription_api.components.subscription.infraestructure.adapter.in.rest.controller;

import com.meli.interview.back.subscription_api.components.subscription.infraestructure.adapter.in.rest.response.SubscriptionUserDto;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;


    @GetMapping("/user/{id}")
    public ResponseEntity<SubscriptionUserDto> getUserSubscriptionsCost(@PathVariable Long id) {
        return subscriptionService.getUserById(id);
    }

}

/*
public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException {..}

 */