package world.xyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Abnormal response
 *
 * @author xyy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespError extends RespResult {

    /**
     * Request address (returned when an exception occurs)
     */
    private String requestUrl;

    /**
     * Exception class (returned when an exception occurs)
     */
    private String exception;
}



