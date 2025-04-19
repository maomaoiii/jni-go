package dispatch

import (
	"encoding/json"
	"fmt"
)

const (
	SUCCESS         = 0
	INVALID_PARAM   = 10001
	INVALID_FUNC    = 10002
	INVALID_COIN    = 10003
	INVALID_ERROR   = 10004
	INVALID_ADDRESS = 10005
)

func codeFromErr(er error) uint {
	if er == nil {
		return 0
	}
	if er == InvalidCurrencyError {
		return INVALID_COIN
	}
	if er == AddressValidationFailError {
		return INVALID_ADDRESS
	}
	return INVALID_PARAM
}

func Dispatch(rawParam string) (r string) {
	resp := &CommandResponse{Code: SUCCESS, Msg: "success"}
	defer func() {
		if err := recover(); err != nil {
			resp = &CommandResponse{
				Code:   INVALID_ERROR,
				Msg:    "failed",
				ErrMsg: fmt.Sprintf("recover from unknown error, %s", err),
			}
			r = toJson(resp)
			fmt.Println("recover from unknown error", err)
		}
	}()
	var req *CommandParam
	if err := json.Unmarshal([]byte(rawParam), &req); err != nil {
		resp = &CommandResponse{
			Code:   INVALID_PARAM,
			Msg:    "failed",
			ErrMsg: "INVALD_PARAM",
		}
		return mustJsonMarshal(resp)
	}

	var res string
	var err error
	switch req.FuncName {
	case TransformAddressOp:
		res, err = TransformAddress(req)
	default:
		resp = &CommandResponse{
			Code:   INVALID_FUNC,
			Msg:    "failed",
			ErrMsg: "INVALID_FUNC",
		}
	}
	if err != nil {
		if resp.Code == 0 {
			resp.Code = codeFromErr(err)
		}
		resp.Msg = "failed"
		resp.ErrMsg = err.Error()
		panic("?????")
	} else {
		resp.Data = res
	}
	return mustJsonMarshal(resp)
}

func toJson(v interface{}) string {
	r, err := json.Marshal(v)
	if err != nil {
		return ""
	}
	return string(r)
}

func mustJsonMarshal(v interface{}) string {
	bytes, _ := json.Marshal(v)
	return string(bytes)
}
