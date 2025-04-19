package dispatch

type Op string

const (
	TransformAddressOp = Op("transform_address")
	CalcTxIdOp         = Op("calc_tx_id")
)
