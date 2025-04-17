package dispatch

import (
	"errors"

	"github.com/maomaoiii/jni-go/coin/ton"
)

func TransformAddress(param *CommandParam) (res string, err error) {
	address, ok := param.Data.(string)
	if !ok {
		return "", ErrInvalidData
	}
	switch param.CoinName {
	case Ton:
		r, err := ton.AddressStrings(address)
		if err != nil {
			return "", err
		}
		return toJson(r), nil
	default:
		return "", errors.New("invalid coin")
	}
}
