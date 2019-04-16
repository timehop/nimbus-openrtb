package com.adsbynimbus.openrtb.impression;

import android.util.Log;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.collection.ArrayMap;

import java.lang.annotation.Retention;
import java.util.Map;

import static com.adsbynimbus.openrtb.impression.Creative.BID_FLOOR;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Android implementation of a Nimbus OpenRTB {@link Impression} object
 */
public class AndroidImpression extends ArrayMap<String, Object> implements Impression {

    @Retention(SOURCE)
    @StringDef({BANNER, VIDEO, DISPLAY_MANAGER, DISPLAY_MANAGER_SERVER, INTERSTITIAL, BID_FLOOR, REQUIRE_HTTPS})
    public @interface Values { }

    /**
     * Builder for {@link AndroidImpression}
     */
    public static class Builder implements Impression.Builder {

        protected final AndroidImpression values = new AndroidImpression();

        @Override
        public AndroidImpression build() {
            return values;
        }

        @Override
        public Map<String, Object> getValues() {
            return values;
        }

        /**
         * Manually set a value on the builder object
         *
         * @param property - {@link Values}
         * @param value    - {@link Object}
         * @return {@link Builder}
         */
        public Builder setValue(@Values String property, Object value) {
            values.put(property, value);
            return this;
        }

        /**
         * Include a {@link Banner} in the impression auction
         *
         * @param banner - {@link Banner}
         * @return {@link Builder}
         */
        public Builder includeBanner(@NonNull AndroidBanner banner) {
            values.put(BANNER, banner);
            return this;
        }

        /**
         * Include a {@link Video} in the impression auction
         *
         * @param video - {@link Video}
         * @return {@link Builder}
         */
        public Builder includeVideo(@NonNull AndroidVideo video) {
            values.put(VIDEO, video);
            return this;
        }

        /**
         * Set the bid floor. [Default: 1.00]
         *
         * @param bidFloor - bid floor
         * @return {@link Builder}
         */
        public Builder withBidFloor(@FloatRange(from = 0) float bidFloor) {
            if (bidFloor >= 0) {
                if (bidFloor != 1.0f) {
                    values.put(BID_FLOOR, bidFloor);
                } else {
                    // Omit bidFloor == 1 (default)
                    Log.d(AndroidImpression.Builder.class.getName(), String.format(OMIT_FORMAT, BID_FLOOR, '=', 1));
                }
            } else {
                //Omit bidFloor < 0 (invalid)
                Log.d(AndroidImpression.Builder.class.getName(), String.format(OMIT_FORMAT, BID_FLOOR, '<', 0));
            }
            return this;
        }

        /**
         * Request an interstitial impression
         * @return {@link Builder}
         */
        public Builder asFullscreenOrInterstitial() {
            values.put(INTERSTITIAL, 1);
            return this;
        }

        /**
         * Allows an impression to send back assets over HTTP
         * @return {@link Builder}
         */
        public Builder allowInsecureImpression() {
            values.put(REQUIRE_HTTPS, 0);
            return this;
        }
    }
}
