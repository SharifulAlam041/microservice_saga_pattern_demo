package com.techworld.UserService.projections;

import com.techworld.CommonService.model.CardDetails;
import com.techworld.CommonService.model.User;
import com.techworld.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Get Details from db
        CardDetails cardDetails=CardDetails.builder()
                .name("Shariful Alam")
                .validUntilMonth(11)
                .validUntilYear(2024)
                .cardNumber("238293453423")
                .cvv(453)
                .build();
        return User.builder()
                .userId(query.getUserId())
                .firstName("Shariful")
                .lastName("Alam")
                .cardDetails(cardDetails)
                .build();
    }

}
