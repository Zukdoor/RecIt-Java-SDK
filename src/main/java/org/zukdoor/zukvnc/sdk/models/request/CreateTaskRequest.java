package org.zukdoor.zukvnc.sdk.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 创建新任务请求，参数含义如下：
 * url 链接，任务对应的url。必填
 * callback 回调地址，任务进行过程中产生的事件会发送至该地址。选填
 * region 任务运行的区域，当前仅支持us-east-1和cn-north-1。选填，默认为us-east-1
 * width 宽。选填
 * height 高。选填
 * spot	是否使用spot实例，spot实例没有预留，可能会被打断。选填，默认为否
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
	private final String url;
	private String callback;
	private Region region = Region.US_EAST_1;
	private Integer width;
	private Integer height;
	private Boolean spot;

	public enum Region {
		@JsonProperty("us-east-1")
		US_EAST_1("us-east-1"),
		@JsonProperty("cn-north-1")
		CN_NORTH_1("cn-north-1");

		@Getter private final String name;

		Region(String name) {
			this.name = name;
		}
	}
}
