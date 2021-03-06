package twofive

import "github.com/francoispqt/gojay"

// Data and Segment objects together allow additional data about the user to be specified. This data
// may be from multiple sources whether from the exchange itself or third party providers as specified by
// the id field. A bid request can mix data objects from multiple providers. The specific data providers in
// use should be published by the exchange a priori to its bidders.
type Data struct {
	ID      string    `json:"id,omitempty"      valid:"optional"`
	Name    string    `json:"name,omitempty"    valid:"optional"`
	Segment []Segment `json:"segment,omitempty" valid:"optional"`
}

// MarshalJSONObject implements MarshalerJSONObject
func (d *Data) MarshalJSONObject(enc *gojay.Encoder) {
	enc.StringKeyOmitEmpty("id", d.ID)
	enc.StringKeyOmitEmpty("name", d.Name)
	var segmentSlice = Segments(d.Segment)
	enc.ArrayKeyOmitEmpty("segment", segmentSlice)
}

// IsNil checks if instance is nil
func (d *Data) IsNil() bool {
	return d == nil
}

// UnmarshalJSONObject implements gojay's UnmarshalerJSONObject
func (d *Data) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {

	switch k {
	case "id":
		return dec.String(&d.ID)

	case "name":
		return dec.String(&d.Name)

	case "segment":
		var aSlice = Segments{}
		err := dec.Array(&aSlice)
		if err == nil && len(aSlice) > 0 {
			d.Segment = []Segment(aSlice)
		}
		return err

	}
	return nil
}

// NKeys returns the number of keys to unmarshal
func (d *Data) NKeys() int { return 0 }

// Datas ...
type Datas []Data

// UnmarshalJSONArray ...
func (s *Datas) UnmarshalJSONArray(dec *gojay.Decoder) error {
	var value = Data{}
	if err := dec.Object(&value); err != nil {
		return err
	}
	*s = append(*s, value)
	return nil
}

// MarshalJSONArray ...
func (s Datas) MarshalJSONArray(enc *gojay.Encoder) {
	for i := range s {
		enc.Object(&s[i])
	}
}

// IsNil ...
func (s Datas) IsNil() bool {
	return len(s) == 0
}
