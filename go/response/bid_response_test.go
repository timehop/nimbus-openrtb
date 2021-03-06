package response

import (
	"encoding/json"
	"testing"

	"github.com/francoispqt/gojay"

	"github.com/google/go-cmp/cmp"
)

func TestBidResponseUnmarshaling(t *testing.T) {
	tests := []struct {
		name               string
		ResponseJSONString string
	}{
		{
			name:               "json marshal and gojay marshal should output the same way",
			ResponseJSONString: `{"auction_id":"7d1a4f2b-b7b2-445e-b1f9-61e7ce56ef48","bid_in_cents":100,"content_type":"application/json; charset=utf-8","height":480,"is_interstitial":1,"markup":"{\"type\":\"ID\",\"bid_id\":\"2762638018393877168\",\"placement_id\":\"IMG_16_9_LINK#191445434271484_1906402376109106\",\"resolved_placement_id\":\"IMG_16_9_LINK#191445434271484_1906402376109106\",\"sdk_version\":\"4.99.1\",\"device_id\":\"0F9DC9F9-C7E7-4579-A945-88A87BDF91E8\",\"template\":200,\"payload\":null}","network":"facebook","placement_id":"IMG_16_9_LINK#191445434271484_1906402376109106","trackers":{"impression_trackers":["foobar.com"]},"type":"facebook","width":320,"ext":{"skadn":{"version":"2.0","network":"cDkw7geQsH.skadnetwork","campaign":"45","itunesitem":"123456789","nonce":"473b1a16-b4ef-43ad-9591-fcf3aefa82a7","sourceapp":"880047117","timestamp":"1594406341","signature":"MEQCIEQlmZRNfYzKBSE8QnhLTIHZZZWCFgZpRqRxHss65KoFAiAJgJKjdrWdkLUOCCjuEx2RmFS7daRzSVZRVZ8RyMyUXg=="}}}`,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {

			var regJSON Bid
			err := json.Unmarshal([]byte(tt.ResponseJSONString), &regJSON)
			if err != nil {
				t.Fatalf("json.Marshal failed %v", err)
			}

			var gojayJSON Bid
			gojay.UnmarshalJSONObject([]byte(tt.ResponseJSONString), &gojayJSON)

			if !cmp.Equal(regJSON, gojayJSON) {
				t.Errorf("TestBidRequestMarshalling()\ndiff\n %+v", cmp.Diff(regJSON, gojayJSON))
			}
		})
	}
}
