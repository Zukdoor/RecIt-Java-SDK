package org.zukdoor.zukvnc.sdk.models;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordFile {
	private String key;
	private String fileName;
	private Date createdTime;
	private Long size;
}
