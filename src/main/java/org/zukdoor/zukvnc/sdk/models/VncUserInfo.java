package org.zukdoor.zukvnc.sdk.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VncUserInfo {
	private String userId;
	private List<VncTask> tasks;
}
