package com.adsbynimbus.openrtb.request

import kotlin.jvm.JvmField

/**
 * This object contains any legal, governmental, or industry regulations that apply to the request.
 *
 * The coppa flag signals whether or not the request falls under the United States Federal Trade Commission’s
 * regulations for the United States Children’s Online Privacy Protection Act ("COPPA").
 *
 * [OpenRTB Section 3.2.3](https://www.iab.com/wp-content/uploads/2016/03/OpenRTB-API-Specification-Version-2-5-FINAL.pdf#page=17)
 */
open class Regs {
    /**
     * Flag indicating if this request is subject to the COPPA regulations established by the USA FTC.
     *
     * * 0 = no
     * * 1 = yes
     *
     * If this value is omitted Nimbus defaults to 0.
     *
     * [OpenRTB Section 7.5](https://www.iab.com/wp-content/uploads/2016/03/OpenRTB-API-Specification-Version-2-5-FINAL.pdf#page=76)
     */
    @JvmField
    var coppa: Int? = null

    /**
     * Regs extension object unique to Nimbus
     */
    @JvmField
    var ext: Extension? = null

    /**
     * Placeholder for exchange-specific extensions to OpenRTB
     */
    open class Extension {
        /**
         * Flag indication if this request is subject to GDPR regulations.
         *
         * This flag will be set automatically by Nimbus based on the received IP address. If this value is omitted
         * Nimbus defaults to 0.
         */
        @JvmField
        var gdpr: Int? = null

        /**
         * A publisher generated string representing compliance with CCPA.
         *
         *
         * The CCPA privacy string is a 4 character string in the following format:
         *
         * Integer - Privacy string version.
         * (Y, N, -) - Publisher has provided explicit user notice.
         * (Y, N, -) - User opted out of sale
         * (Y, N, -) - Publisher operating under the Limited Service Provider Agreement
         *
         *
         * If the user does not fall within a US Privacy jurisdiction, hyphens should be used in
         * the last three positions, generating this privacy string: "1---"
         *
         * @see [US Privacy String Format](https://github.com/InteractiveAdvertisingBureau/USPrivacy/blob/master/CCPA/Version%201.0/US%20Privacy%20String.md.us-privacy-string-format)
         */
        @JvmField
        var us_privacy: String? = null
    }

    /**
     * Builder for constructing a [Regs] object
     */
    interface Builder {
        /**
         * Set to true if COPPA applies to this request
         *
         * @param coppa true if COPPA applies to this request. Default is false
         * @return this builder instance
         * @see [Regs.coppa]
         *
         * [OpenRTB Section 7.5](https://www.iab.com/wp-content/uploads/2016/03/OpenRTB-API-Specification-Version-2-5-FINAL.pdf#page=71)
         */
        fun coppa(coppa: Boolean): Builder

        /**
         * Set to true if GDPR applies to this request
         *
         * @param gdpr true if GDPR applies to this request. Default is false
         * @return this builder instance
         * @see [Extension.gdpr]
         */
        fun gdpr(gdpr: Boolean): Builder

        /**
         * A publisher generated string representing compliance with CCPA.
         *
         * @param usPrivacyString the four character CCPA privacy string
         * @return this builder instance
         * @see [Extension.us_privacy]
         */
        fun ccpa(usPrivacyString: String?): Builder
    }
}