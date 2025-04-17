package com.example;

import com.alibaba.fastjson.annotation.JSONField;

public class CommandResponse {
        @JSONField(
            name = "code"
        )
        private int code;

        @JSONField(
            name = "data"
        )
        private Object data;

        @JSONField(
            name = "msg"
        )
        private String msg;

        @JSONField(
            name = "err_msg"
        )
        private String errMsg;

        public CommandResponse () {
        }

        public int getCode() {
            return this.code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public Object getData() {
            return this.data;
        }
        public void setData(Object data) {
            this.data = data;
        }
        public String getMsg() {
            return this.msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getErrMsg() {
            return this.errMsg;
        }
        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

}
