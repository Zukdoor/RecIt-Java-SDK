package org.zukdoor.zukvnc.sdk.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 停止推流请求，参数含义如下：
 * display 任务id，可以从创建新任务响应中获得。必填
 */
@Data
@RequiredArgsConstructor
public class StopPushingRequest {
	private final Long display;
}
