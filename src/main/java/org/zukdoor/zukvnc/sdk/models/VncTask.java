package org.zukdoor.zukvnc.sdk.models;

import lombok.Value;

import java.util.List;

@Value
public class VncTask {
	private final long id;
	private final String userId;
	private final long startTime;
	private final long stopTime;
	private final String recordingName;
	private final List<String> rtmpUrls;
	private final List<RecordFile> recordFiles;
	private final boolean ended;
}
