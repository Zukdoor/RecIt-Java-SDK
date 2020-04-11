package org.zukdoor.zukvnc.sdk.models.response;

import lombok.Data;

/**
 * 创建新任务响应，字段含义如下：
 * display 任务的id
 * userId 任务创建者的id
 * url 任务的url，与请求中的url对应
 * callback 任务的回调地址，与请求中的callback对应
 * createdTime 任务的创建时间
 */
@Data
public class CreateTaskResponse {
	private long display;
	private String userId;
	private String url;
	private String callback;
	private Long createdTime;
}
