package com.example;

import com.alibaba.fastjson.annotation.JSONField;

public class CommandParam {
        @JSONField(
            name = "func_name"
        )
        private String funcName;

        @JSONField(
            name = "coin_name"
        )
        private String coinName;

        @JSONField(
                    name = "data"
        )
        private Object Data;

        public CommandParam(String funcName, String coinName, Object data) {
            this.funcName = funcName;
            this.coinName = coinName;
            this.Data = data;
        }

        public String getFuncName() {
            return this.funcName;
        }
        public void setFuncName(String funcName) {
            this.funcName = funcName;
        }
        public String getCoinName() {
            return this.coinName;
        }
        public void setCoinName(String coinName) {
            this.coinName = coinName;
        }
        public Object getData() {
            return this.Data;
        }
        public void setData(Object data) {
            this.Data = data;
        }


}
