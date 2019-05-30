package com.adsbynimbus.openrtb.impression;

import android.util.Log;
import androidx.annotation.FloatRange;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.collection.ArrayMap;

import java.lang.annotation.Retention;
import java.util.Map;

import static com.adsbynimbus.openrtb.internal.NimbusRTB.EXTENSION;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Android implementation of a Nimbus OpenRTB {@link Impression} object
 */
public class AndroidImpression extends ArrayMap<String, Object> implements Impression {

    @Retention(SOURCE)
    @StringDef({BANNER, VIDEO, DISPLAY_MANAGER, DISPLAY_MANAGER_SERVER, INTERSTITIAL, BID_FLOOR, REQUIRE_HTTPS})
    public @interface Values { }

    @Retention(SOURCE)
    @IntDef({POSITION_UNKNOWN, ABOVE_THE_FOLD, BELOW_THE_FOLD, HEADER, FOOTER, SIDEBAR, FULL_SCREEN})
    public @interface Position { }

    /**
     * Builder for {@link AndroidImpression}
     */
    public static class Builder implements Impression.Builder {

        protected final AndroidImpression values;
        protected ArrayMap<String, Object> ext;

        public Builder() {
            values = new AndroidImpression();
            if (INCLUDE_DEFAULTS.get()) {
                values.put(BID_FLOOR, 1f);
            }
            values.put(REQUIRE_HTTPS, 1);
        }

        @Override
        public AndroidImpression build() {
            if (ext != null) {
                values.put(EXTENSION, ext);
            }
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
            if (INCLUDE_DEFAULTS.get()) {
                values.put(BID_FLOOR, bidFloor);
                return this;
            }

            if (bidFloor >= 0) {
                if ((int) bidFloor != 1) {
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

        /**
         * Adds an extension object to allow for tracking by a publisher ad unit id
         *
         * @param adUnitIdentifier - Any unique {@link String}
         * @return {@link Builder}
         */
        public Builder withAdUnitIdentifier(String adUnitIdentifier) {
            if (ext == null) {
                ext = new ArrayMap<>(3);
            }
            ext.put(EXT_POSITION, adUnitIdentifier);
            return this;
        }


        /**
         * Set the Facebook App Id
         *
         * @param facebookAppId - {@link String}
         * @return {@link Builder}
         */
        public Builder withFacebookAppId(@NonNull String facebookAppId) {
            if (ext == null) {
                ext = new ArrayMap<>(3);
            }
            ext.put(FACEBOOK_APP_ID, facebookAppId);
            return this;
        }

        /**
         * Set the APS params 
         *
         * @param apsParams - {@link String}
         * @return {@link Builder}
         */
        public Builder withApsParams(@NonNull Map<String, Object> apsParams) {
            if (ext == null) {
                ext = new ArrayMap<>(3);
            }
            ext.put(APS, apsParams);
            return this;
        }
    }
}
