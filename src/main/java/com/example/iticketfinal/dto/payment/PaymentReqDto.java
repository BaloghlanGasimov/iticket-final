package com.example.iticketfinal.dto.payment;

import com.example.iticketfinal.enums.TicketCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {

    @NotNull
    private TicketCategory category;
    @NotNull
    private Integer count;

}
