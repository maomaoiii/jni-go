package ton

import "github.com/maomaoiii/jni-go/coin/ton/address"

func AddressStrings(a string) ([]*address.AddrWithType, error) {
	addr, err := address.ParseAddr(a)
	if err != nil {
		return nil, err
	}
	return addr.Strings(), nil
}
