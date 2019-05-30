package com.adsbynimbus.openrtb.impression;

import com.adsbynimbus.openrtb.internal.NimbusRTB;

import java.util.Map;

public interface Impression extends Creative {

    String BANNER = "banner"; // Banner
    String VIDEO = "video"; // Video
    String DISPLAY_MANAGER = "displaymanager";
    String DISPLAY_MANAGER_SERVER = "displaymanagerserver";
    String INTERSTITIAL = "instl"; //int (default 0; 0 = not interstitial, 1 = interstitial or full screen)
    String REQUIRE_HTTPS = "secure"; //int (default: 1, 0 = not secure, 1 = require https)

    //Extensions
    String EXT_POSITION = "position";
    String FACEBOOK_APP_ID = "facebook_app_id";
    String APS = "aps";

    interface Builder extends NimbusRTB.Builder {

        default Impression build() {
            final Map values = getValues();
            return new Impression() {
                public final Banner banner = (Banner) values.get(BANNER);
                public final Video video = (Video) values.get(VIDEO);
                public final String displaymanager = (String) values.get(DISPLAY_MANAGER);
                public final String displaymanagerserver = (String) values.get(DISPLAY_MANAGER_SERVER);
                public final Integer instl = (Integer) values.get(INTERSTITIAL); // Server default 0
                public final Float bidfloor = (Float) values.get(BID_FLOOR); // Server default 1.0
                public final Integer secure = (Integer) values.get(REQUIRE_HTTPS); // Server default 1
                public final Object ext = values.containsKey(EXT_POSITION) || values.containsKey(FACEBOOK_APP_ID) ||
                        values.containsKey(APS) ? new Object() {
                    public final String position = (String) values.get(EXT_POSITION);
                    public final String facebook_app_id = (String) values.get(FACEBOOK_APP_ID);
                    public final Object aps = values.get(APS);
                } : null;
            };
        }
    }
}
