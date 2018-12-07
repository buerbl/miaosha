package com.immoc.miaosha.exception;

import com.immoc.miaosha.result.CodeMsg;

/**
 * Created by buer on 2018/12/6.
 */
public class GlobalException extends RuntimeException{
    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
