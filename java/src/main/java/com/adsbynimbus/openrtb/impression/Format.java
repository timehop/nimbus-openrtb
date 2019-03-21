package com.adsbynimbus.openrtb.impression;

import com.adsbynimbus.openrtb.internal.NimbusRTB;

import java.util.Map;

public interface Format {

    //Share format parameter
    String FORMAT = "format";

    String WIDTH = "w";
    String HEIGHT = "h";

    int getWidth();
    int getHeight();

    interface Builder extends NimbusRTB.Builder {

        default Format build() {
            final Map values = getValues();
            return new Format() {
                public final int w = (int) values.get(WIDTH);
                public final int h = (int) values.get(HEIGHT);

                @Override
                public int getWidth() {
                    return w;
                }

                @Override
                public int getHeight() {
                    return h;
                }
            };
        }
    }
}