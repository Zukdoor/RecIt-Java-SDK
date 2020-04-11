package org.zukdoor.zukvnc.sdk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.zukdoor.zukvnc.sdk.exceptions.*;
import org.zukdoor.zukvnc.sdk.models.Principal;
import org.zukdoor.zukvnc.sdk.models.request.*;
import org.zukdoor.zukvnc.sdk.models.response.*;

/**
 * 为访问Zuk VNC服务提供客户端接口
 * 简单使用示例：
 * <pre>
 * {@code
 * ZukVncClient client = ZukVncClient.create(username, password);
 * client.startRecording(new StartRecordingRequest(display, url, name, callback));
 * // ...
 * }
 * </pre>
 */
public interface ZukVncClient {

	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws BadRequest
	 */
	static ZukVncClient create(String username, String password) throws BadRequest {
		var restTemplate = new RestTemplate();
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		var httpEntity = new HttpEntity<>(new SignInRequest(username, password), headers);
		var token = "Bearer " + restTemplate
			.postForObject(Global.SIGN_IN_URL, httpEntity, SignInResponse.class)
			.getToken();

		var principal = restTemplate.getForObject(Global.TOKEN_URL, Principal.class, token);

		return new ZukVncClientImpl(username, password, token, principal);
	}

	Principal getPrincipal();

	/**
	 * 创建新任务
	 * @param request 创建任务请求参数，参数说明详见{@link CreateTaskRequest}
	 * @return 开始任务响应，响应说明详见{@link CreateTaskResponse}
	 */
	CreateTaskResponse createTask(CreateTaskRequest request);

	/**
	 * 停止任务
	 * @param display 任务id，可以从创建新任务的响应中获得
	 * @throws Forbidden 没有权限停止该任务时会抛出此异常
	 * @throws TaskNotFound 任务id不存在时会抛出此异常
	 * @see CreateTaskResponse
	 */
	void stopTask(Long display)
		throws Forbidden, TaskNotFound;

	/**
	 * 查询任务信息
	 * @param request 查询任务信息请求参数，参数说明详见{@link QueryTasksRequest}
	 * @return 查询任务信息响应，响应说明详见{@link QueryTasksResponse}
	 * @throws Forbidden 没有权限时会抛出此异常
	 */
	QueryTasksResponse queryTasks(QueryTasksRequest request)
		throws Forbidden;

	/**
	 * 开始录制，同一个任务只能有一个进行中的录制请求。
	 * @param request 开始录制请求参数，参数说明详见{@link StartRecordingRequest}
	 * @throws Forbidden 没有权限录制时会抛出此异常
	 * @throws TaskNotFound 任务id不存在时会抛出此异常
	 * @throws TaskAlreadyRunning 该任务正在录制中时会抛出此异常
	 */
	void startRecording(StartRecordingRequest request)
		throws Forbidden, TaskNotFound, TaskAlreadyRunning;

	/**
	 * 停止录制
	 * @param request 停止录制请求参数，参数说明详见{@link StopRecordingRequest}
	 * @throws Forbidden 没有权限停止录制时会抛出此异常
	 * @throws TaskNotFound 任务id不存在时会抛出此异常
	 */
	void stopRecording(StopRecordingRequest request)
		throws Forbidden, TaskNotFound;

	/**
	 * 开始推流，同一个任务只能有一个进行中的推流请求，若需要推至多个地址，请在一次请求内传递所有地址。
	 * @param request 开始推流请求参数，参数说明详见{@link StartPushingRequest}
	 * @throws Forbidden 没有权限推流时会抛出此异常
	 * @throws TaskNotFound 任务id不存在时会抛出此异常
	 * @throws TaskAlreadyRunning 任务id不存在时会抛出此异常
	 */
	void startPushing(StartPushingRequest request)
		throws Forbidden, TaskNotFound, TaskAlreadyRunning;

	/**
	 * 停止推流
	 * @param request 开始推流请求参数，参数说明详见{@link StopPushingRequest}
	 * @throws Forbidden 没有权限停止推流时会抛出此异常
	 * @throws TaskNotFound 任务id不存在时会抛出此异常
	 */
	void stopPushing(StopPushingRequest request)
		throws Forbidden, TaskNotFound;

	/**
	 * 生成录像地址
	 * @param request 生成录像地址请求参数，参数说明详见{@link GenerateUrlRequest}
	 * @throws Forbidden 没有权限生成该录像文件的地址时会抛出此异常
	 * @throws TaskNotFound 任务id或录像文件不存在时会抛出此异常
	 */
	GenerateUrlResponse generateRecordUrl(GenerateUrlRequest request)
		throws Forbidden, TaskNotFound;

}
