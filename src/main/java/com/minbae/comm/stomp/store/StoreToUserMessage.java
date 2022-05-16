package com.minbae.comm.stomp.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreToUserMessage {

    private String message;
    private String tradehistoryidx;

}
