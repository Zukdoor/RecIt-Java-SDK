package org.zukdoor.zukvnc.sdk.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 开始推流请求，参数含义如下：
 * display 任务id，可以从创建新任务响应中获得。必填
 * rtmpUrl 推流目标地址列表。必填
 */
@Data
@RequiredArgsConstructor
public class StartPushingRequest {
	private final Long display;
	private final List<String> rtmpUrl;
}
