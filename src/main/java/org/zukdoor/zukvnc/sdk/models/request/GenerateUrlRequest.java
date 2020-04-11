package org.zukdoor.zukvnc.sdk.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 生成录像地址请求，参数含义如下：
 * taskId 任务id，可以从创建新任务响应中获得。必填
 * recordName 录像名称，开始录像请求时传递的名称。必填
 * expireTime 过期时间，可以从生成的地址下载录像的时间，单位为秒，最长为7天。必填
 */
@Data
@RequiredArgsConstructor
public class GenerateUrlRequest {
	private final Long taskId;
	private final String recordName;
	private final Integer expireTime;
}
