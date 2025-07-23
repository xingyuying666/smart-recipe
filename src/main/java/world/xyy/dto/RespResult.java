package world.xyy.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import world.xyy.utils.Assert;

import java.util.List;

/**
 * Response result
 *
 * @author xyy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespResult {

    /**
     * Response coding
     */
    protected String code;

    /**
     * Response information
     */
    protected String message;

    /**
     * Response data
     */
    protected Object data;

    /**
     * Request successful
     */
    public static RespResult success() {
        return RespResult.builder()
                .code("SUCCESS")
                .message("Request successful")
                .build();
    }

    /**
     * Request successful
     */
    public static RespResult success(String message) {
        return RespResult.builder()
                .code("SUCCESS")
                .message(message)
                .build();
    }

    /**
     * Request successful
     */
    public static RespResult success(String message, Object data) {
        return RespResult.builder()
                .code("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Request error
     */
    public static RespResult fail() {
        return RespResult.builder()
                .code("FAIL")
                .message("Request error")
                .build();
    }


    /**
     * Request error
     */
    public static RespResult fail(String message) {
        return RespResult.builder()
                .code("FAIL")
                .message(message)
                .build();
    }

    /**
     * No data was found
     */
    public static RespResult notFound(String message, Object data) {
        return RespResult.builder()
                .code("NOT_FOUND")
                .message(message)
                .data(data)
                .build();
    }

    /**
     * No data was found
     */
    public static RespResult notFound() {
        return RespResult.builder()
                .code("NOT_FOUND")
                .message("Request error")
                .build();
    }


    /**
     * No data was found
     */
    public static RespResult notFound(String message) {
        return RespResult.builder()
                .code("NOT_FOUND")
                .message(message)
                .build();
    }

    /**
     * Request error
     */
    public static RespResult fail(String message, Object data) {
        return RespResult.builder()
                .code("FAIL")
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Whether the request was successful
     */
    public boolean isSuccess() {
        return "SUCCESS".equals(code);
    }

    /**
     * Whether the request was successful and whether there was data returned
     */
    public boolean isSuccessWithDateResp() {
        return "SUCCESS".equals(code) && Assert.notEmpty(data);
    }

    /**
     * Whether the request was successful
     */
    public boolean notSuccess() {
        return !isSuccess();
    }

    /**
     * Obtain the data set of the response
     */
    public <T> List<T> getDataList(Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseArray(jsonString, clazz);
    }

    /**
     * Obtain the response data
     */
    public <T> T getDataObj(Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseObject(jsonString, clazz);
    }

}
