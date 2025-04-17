package dispatch

import "testing"

func TestDispatch(t *testing.T) {
	type args struct {
		rawParam string
	}

	tests := []struct {
		name  string
		args  args
		wantR string
	}{
		{
			name: "11",
			args: args{
				rawParam: "{\"func_name\": \"transform_address\", \"coin_name\":\"ton\", \"data\":\"UQCNl56EC6TBODS4NiZRnSPDyOwUmJ1wNHGuadwd5YY9325i\"}",
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if gotR := Dispatch(tt.args.rawParam); gotR == "" {
				t.Errorf("Dispatch() = %v, want %v", gotR, tt.wantR)
			}
		})
	}
}
