package twofive

import "github.com/francoispqt/gojay"

// Source describes the nature and behavior of the entity that is the source of the bid request
// upstream from the exchange. The primary purpose of this object is to define post-auction or upstream
// decisioning when the exchange itself does not control the final decision. A common example of this is
// header bidding, but it can also apply to upstream server entities such as another RTB exchange, a
// mediation platform, or an ad server combines direct campaigns with 3rd party demand in decisioning.
type Source struct {
	Ext SourceExt `json:"ext,omitempty"    valid:"optional"`
}

// SourceExt also for OM SDK extensions to be passed to demand
type SourceExt struct {
	Omidpn string `json:"omidpn,omitempty" valid:"optional"` // identifier of the OM SDK integration, this is the same as the "name" parameter of the OMID Partner object
	Omidpv string `json:"omidpv,omitempty" valid:"optional"` // (optional) Version of the OM SDK version
}

// MarshalJSONObject implements MarshalerJSONObject
func (s *Source) MarshalJSONObject(enc *gojay.Encoder) {
	enc.ObjectKeyNullEmpty("ext", &s.Ext)
}

// IsNil checks if instance is nil
func (s *Source) IsNil() bool {
	return s == nil
}

// UnmarshalJSONObject implements gojay's UnmarshalerJSONObject
func (s *Source) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {

	switch k {
	case "ext":
		return dec.Object(&s.Ext)

	}
	return nil
}

// NKeys returns the number of keys to unmarshal
func (s *Source) NKeys() int { return 0 }

// MarshalJSONObject implements MarshalerJSONObject
func (s *SourceExt) MarshalJSONObject(enc *gojay.Encoder) {
	enc.StringKeyOmitEmpty("omidpn", s.Omidpn)
	enc.StringKeyOmitEmpty("omidpv", s.Omidpv)
}

// IsNil checks if instance is nil
func (s *SourceExt) IsNil() bool {
	return s == nil
}

// UnmarshalJSONObject implements gojay's UnmarshalerJSONObject
func (s *SourceExt) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {

	switch k {
	case "omidpn":
		return dec.String(&s.Omidpn)

	case "omidpv":
		return dec.String(&s.Omidpv)

	}
	return nil
}

// NKeys returns the number of keys to unmarshal
func (s *SourceExt) NKeys() int { return 0 }
