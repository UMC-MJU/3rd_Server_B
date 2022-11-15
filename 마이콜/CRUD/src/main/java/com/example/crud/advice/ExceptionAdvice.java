package com.example.crud.advice;

import com.example.crud.exception.BoardAndWriterNotFoundException;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Restful API를 설계하기 위해 HTTP 응답 상태코드 반환
//예외처리를 일괄적으로 모아 효율적으로 관리할 수 있게 해주며, 정확한 오류코드를 파악할 수 있다.
//더불어 응답을 Json으로 내려준다( + ResponseBody가 같이 붙어있음)
@RestControllerAdvice
public class ExceptionAdvice {
    //지정한 예외가 발생하면 해당 메서드 실행 + 상태코드와 오류 메세지 반환
    @ExceptionHandler(BoardAndWriterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardAndWriterNotFoundException() {
        return Response.failure(404, "게시글을 찾을 수 없습니다. ");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response userNotFoundException() {
        return Response.failure(404,"회원정보를 찾을 수 없습니다. ");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response illegalArgumentException() {
        return Response.failure(500, "내부 서버 오류입니다. ");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentException() {
        return Response.failure(400, "잘못된 문법으로 인하여 서버가 요청을 받지 못했습니다. ");
    }

}
