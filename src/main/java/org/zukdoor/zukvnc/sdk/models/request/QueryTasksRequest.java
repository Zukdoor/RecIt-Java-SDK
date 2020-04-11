package org.zukdoor.zukvnc.sdk.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 查询任务信息请求，参数含义如下：
 * pageSize 分页大小，用于控制分页展示，每一页展示的任务数量。选填，默认为20
 * pageNum 分页编号，用于控制分页展示，展示第几页的任务，从0开始。选填，默认为0
 * stopped 按任务是否停止过滤。选填
 * startedTime 按任务的开始时间过滤，只保留任务的开始时间晚于startedTime的任务。选填
 * stoppedTime 按任务的开始时间过滤，只保留任务的开始时间早于stoppedTime的任务。选填
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class QueryTasksRequest {
	private Integer pageSize;
	private Integer pageNum;
	private String userId;
	private Boolean stopped;
	private Long startedTime;
	private Long stoppedTime;
}
