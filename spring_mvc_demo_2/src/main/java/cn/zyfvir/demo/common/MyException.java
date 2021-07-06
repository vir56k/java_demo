package cn.zyfvir.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "啊呜，找不见了...")
public class MyException extends RuntimeException {

}
