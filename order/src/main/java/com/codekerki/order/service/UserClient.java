package com.codekerki.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient: Simplifies HTTP requests to other microservices by defining an interface with annotations.
 * Service Discovery: The @FeignClient annotation specifies the name of the service you want to call. 
 * In this case, @FeignClient("user") indicates that this client will communicate with the user service.
 * The actual URL of the user service is not hardcoded in the UserClient interface. 
 * Instead, it is resolved dynamically using service discovery (Eureka).
 */
@FeignClient("user")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "user/validate")
    boolean isUserValid(@RequestParam(name = "id") Long id);

}
