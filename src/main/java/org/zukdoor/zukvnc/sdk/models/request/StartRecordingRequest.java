package org.zukdoor.zukvnc.sdk.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 开始录制请求，参数含义如下：
 * display 任务id，可以从创建新任务响应中获得。必填
 * recordName 录像名称，同一任务中的录像名称不可重复。必填
 * format 录像格式。选填，默认为mkv
 * onlyAudio 是否只录制音频。选填，默认为否
 * storageType 存储类型，0由我们托管；1为存储至AWS S3；2为阿里云OSS；3为dropbox；4为七牛云。根据存储类型填充以下字段，字段说明见各存储的文档。选填，默认为0
 * accessKey 存储类型为1, 2, 4时必填
 * secretKey 存储类型为1, 2, 4时必填
 * bucketName 存储类型为1, 2, 4时必填
 * region 存储类型为1, 2, 4时必填
 * accessToken 存储类型为3时必填
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StartRecordingRequest {
	private final Long display;
	private final String recordName;
	private String format;
	private Boolean onlyAudio = false;
	private int storageType = 0;
	private String accessKey;
	private String secretKey;
	private String bucketName;
	private String region;
	private String accessToken;
}
