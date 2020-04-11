package org.zukdoor.zukvnc.sdk;

import org.zukdoor.zukvnc.sdk.exceptions.Forbidden;
import org.zukdoor.zukvnc.sdk.exceptions.Unauthorized;
import org.zukdoor.zukvnc.sdk.models.Principal;
import org.zukdoor.zukvnc.sdk.models.request.*;
import org.zukdoor.zukvnc.sdk.models.response.*;

public class ZukVncClientImpl extends AbstractClient implements ZukVncClient {
	private final String username;
	private final String password;
	private final String token;
	private final Principal principal;

	ZukVncClientImpl(String username, String password, String token, Principal principal) {
		super(username, password, token);
		this.username = username;
		this.password = password;
		this.token = token;
		this.principal = principal;
	}

	@Override
	public Principal getPrincipal() {
		return principal;
	}

	@Override
	public CreateTaskResponse createTask(CreateTaskRequest request) {
		return post(Global.TASKS_URL)
			.body(request)
			.result(CreateTaskResponse.class);
	}

	@Override
	public void stopTask(Long display) throws Unauthorized {
		delete(Global.TASK_URL, display)
			.result(Void.class);
	}

	@Override
	public QueryTasksResponse queryTasks(QueryTasksRequest request) throws Forbidden {
		request.setUserId(principal.getId());
		return get(Global.TASKS_URL)
			.result(QueryTasksResponse.class);
	}

	@Override
	public void startRecording(StartRecordingRequest request) {
		post(Global.RECORD_URL, request.getDisplay())
			.body(request)
			.result(Void.class);
	}

	@Override
	public void stopRecording(StopRecordingRequest request) {
		delete(Global.RECORD_URL, request.getDisplay())
			.result(Void.class);
	}

	@Override
	public void startPushing(StartPushingRequest request) {
		post(Global.RTMP_URL, request.getDisplay())
			.body(request)
			.result(Void.class);
	}

	@Override
	public void stopPushing(StopPushingRequest request) {
		post(Global.RTMP_URL, request.getDisplay())
			.body(request)
			.result(Void.class);
	}

	@Override
	public GenerateUrlResponse generateRecordUrl(GenerateUrlRequest request) {
		return post(Global.GENERATE_RECORD_URL, request.getTaskId(), request.getRecordName())
			.body(request)
			.result(GenerateUrlResponse.class);
	}

}
