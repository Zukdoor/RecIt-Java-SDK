package org.zukdoor.zukvnc.sdk;

public class Global {
	public static final String SERVER_URL = "https://api.zukdoor.com";

	public static final String SIGN_IN_URL = String.format("%s/gateway/api/v1/tokens", SERVER_URL);

	public static final String TOKEN_URL = String.format("%s/gateway/api/v1/tokens/{token}", SERVER_URL);

	// zuk vnc urls

	public static final String ZUK_VNC_URL = String.format("%s/zukvnc-test", SERVER_URL);

	public static final String TASKS_URL = String.format("%s/api/v1/tasks", ZUK_VNC_URL);

	public static final String TASK_URL = String.format("%s/api/v1/tasks/{taskId}", ZUK_VNC_URL);

	public static final String RECORD_URL = String.format("%s/api/v1/tasks/{taskId}/records", ZUK_VNC_URL);

	public static final String RTMP_URL = String.format("%s/api/v1/tasks/{taskId}/rtmps", ZUK_VNC_URL);

	public static final String GENERATE_RECORD_URL = String.format("%s/api/v1/stopped-tasks/{taskId}/records/{recordName}/urls", ZUK_VNC_URL);

//	public static final String RECORD_URL = String.format("%s/api/v1/users/{userId}/vnctasks/{taskId}/recordFile", ZUK_VNC_URL);

}
