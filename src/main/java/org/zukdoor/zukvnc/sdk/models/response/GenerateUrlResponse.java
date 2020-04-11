package org.zukdoor.zukvnc.sdk.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生成录像地址响应，字段含义如下：
 * url 录像的地址，在过期时间内可以从该地址下载录像文件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateUrlResponse {
	private String url;
}
