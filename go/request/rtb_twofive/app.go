package twofive

import "github.com/francoispqt/gojay"

// App object should be included if the ad supported content is a non-browser application (typically in
// mobile) as opposed to a website. A bid request must not contain both an App and a Site object. At a
// minimum, it is useful to provide an App ID or bundle, but this is not strictly required
type App struct {
	Name          string    `json:"name"                 valid:"required"`
	Bundle        string    `json:"bundle"               valid:"required"`
	Domain        string    `json:"domain"               valid:"required"`
	StoreURL      string    `json:"storeurl"             valid:"required"`
	Cat           []string  `json:"cat,omitempty"        valid:"optional"`
	SectionCat    []string  `json:"sectioncat,omitempty" valid:"optional"`
	PageCat       []string  `json:"pagecat,omitempty"    valid:"optional"`
	Ver           string    `json:"ver,omitempty"        valid:"optional"`
	PrivacyPolicy int       `json:"privacypolicy"        valid:"range(0|1),optional"` // no policy 0 policy 1
	Paid          int       `json:"paid"                 valid:"range(0|1),optional"` // free 0 paid 1
	Publisher     Publisher `json:"publisher"            valid:"required"`
}

// MarshalJSONObject implements MarshalerJSONObject
func (a *App) MarshalJSONObject(enc *gojay.Encoder) {
	enc.StringKey("name", a.Name)
	enc.StringKey("bundle", a.Bundle)
	enc.StringKey("domain", a.Domain)
	enc.StringKey("storeurl", a.StoreURL)
	var catSlice = Strings(a.Cat)
	enc.ArrayKeyOmitEmpty("cat", catSlice)
	var sectionCatSlice = Strings(a.SectionCat)
	enc.ArrayKeyOmitEmpty("sectioncat", sectionCatSlice)
	var pageCatSlice = Strings(a.PageCat)
	enc.ArrayKeyOmitEmpty("pagecat", pageCatSlice)
	enc.StringKeyOmitEmpty("ver", a.Ver)
	enc.IntKey("privacypolicy", a.PrivacyPolicy)
	enc.IntKey("paid", a.Paid)
	enc.ObjectKey("publisher", &a.Publisher)
}

// IsNil checks if instance is nil
func (a *App) IsNil() bool {
	return a == nil
}

// UnmarshalJSONObject implements gojay's UnmarshalerJSONObject
func (a *App) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {

	switch k {
	case "name":
		return dec.String(&a.Name)

	case "bundle":
		return dec.String(&a.Bundle)

	case "domain":
		return dec.String(&a.Domain)

	case "storeurl":
		return dec.String(&a.StoreURL)

	case "cat":
		var aSlice = Strings{}
		err := dec.Array(&aSlice)
		if err == nil && len(aSlice) > 0 {
			a.Cat = []string(aSlice)
		}
		return err

	case "sectioncat":
		var aSlice = Strings{}
		err := dec.Array(&aSlice)
		if err == nil && len(aSlice) > 0 {
			a.SectionCat = []string(aSlice)
		}
		return err

	case "pagecat":
		var aSlice = Strings{}
		err := dec.Array(&aSlice)
		if err == nil && len(aSlice) > 0 {
			a.PageCat = []string(aSlice)
		}
		return err

	case "ver":
		return dec.String(&a.Ver)

	case "privacypolicy":
		return dec.Int(&a.PrivacyPolicy)

	case "paid":
		return dec.Int(&a.Paid)

	case "publisher":
		err := dec.Object(&a.Publisher)
		return err

	}
	return nil
}

// NKeys returns the number of keys to unmarshal
func (a *App) NKeys() int { return 0 }
