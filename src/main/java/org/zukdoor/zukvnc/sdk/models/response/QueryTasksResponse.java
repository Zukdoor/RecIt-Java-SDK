package org.zukdoor.zukvnc.sdk.models.response;

import lombok.Data;
import org.zukdoor.zukvnc.sdk.models.VncTask;

import java.util.List;

@Data
public class QueryTasksResponse {
	private List<VncTask> data;
	private long totalCount;
}
