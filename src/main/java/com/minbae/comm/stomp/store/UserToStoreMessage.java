package com.minbae.comm.stomp.store;

import lombok.Data;

@Data
public class UserToStoreMessage {

	//user가 결제 완료 후 가게에 보낼 메시지
	private String u_trade_history_idx;

	//store한테 결제 요청 들어왔을 때 user에 대한 응답
	private String accept_Or_not;
}
