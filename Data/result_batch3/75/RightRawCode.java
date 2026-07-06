// https://github.com/Exrick/xboot/tree/5277af0ea7db3cf085f8ef3be21329df5bb12cd9/xboot-fast/src/main/java/cn/exrick/xboot/common/exception/RestCtrlExceptionHandler.java#L52-L69
public class TempClass {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            sb.append(fieldName + "-" + message + "；");
        });
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return new ResultUtil<>().setErrorMsg(500, result);
    }

    @ExceptionHandler(ConstraintViolationException.class)

}