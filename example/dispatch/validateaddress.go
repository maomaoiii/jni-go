package dispatch

import "errors"

var (
	AddressValidationFailError = errors.New("address validation failed")
	InvalidCurrencyError       = errors.New("invalid coin")
)
