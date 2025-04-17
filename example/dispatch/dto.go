package dispatch

type CommandParam struct {
	FuncName Op          `json:"func_name"`
	CoinName Coin        `json:"coin_name"`
	Data     interface{} `json:"data"`
}

type CommandResponse struct {
	Code   uint   `json:"code"`
	Data   string `json:"data"`
	Msg    string `json:"msg"`
	ErrMsg string `json:"err_msg"`
}
