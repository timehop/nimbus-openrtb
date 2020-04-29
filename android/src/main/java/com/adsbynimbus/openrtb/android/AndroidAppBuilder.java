package com.adsbynimbus.openrtb.android;

import android.content.Context;

import androidx.annotation.NonNull;

import com.adsbynimbus.openrtb.targeting.distribution.App;
import com.adsbynimbus.openrtb.targeting.distribution.Publisher;

/**
 * App builder implementation with Androidx annotations for validation
 *
 * @see App
 */
public final class AndroidAppBuilder implements App.Builder {

    /**
     * The app object this builder wraps
     */
    @NonNull
    private final App app;

    /**
     * Constructs a builder that will edit the fields of the app object in place.
     *
     * @param app an app object
     */
    public AndroidAppBuilder(@NonNull App app) {
        this.app = app;
    }

    /**
     * {@inheritDoc}
     *
     * @param name {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder name(String name) {
        app.name = name;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param bundle {@inheritDoc}
     * @return {@inheritDoc}
     * @see Context#getPackageName()
     */
    @Override
    public AndroidAppBuilder bundle(String bundle) {
        app.bundle = bundle;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param domain {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder domain(String domain) {
        app.domain = domain;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param storeUrl {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder storeUrl(String storeUrl) {
        app.storeurl = storeUrl;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param categories {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder categories(String... categories) {
        app.cat = categories;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param paid {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder paid(boolean paid) {
        app.paid = paid ? 1 : 0;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param publisher {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder publisher(@NonNull Publisher publisher) {
        app.publisher = publisher;
        return this;
    }

    /**
     * Returns a builder for the publisher object. A new {@link Publisher} object will be created if none exists.
     *
     * @return a builder wrapping the publisher object
     */
    public AndroidPublisherBuilder publisher() {
        if (app.publisher == null) {
            app.publisher = new Publisher();
        }
        return new AndroidPublisherBuilder(app.publisher);
    }

    /**
     * {@inheritDoc}
     *
     * @param pageCategories {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder pageCategories(String... pageCategories) {
        app.pagecat = pageCategories;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param sectionCategories {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder sectionCategories(String... sectionCategories) {
        app.sectioncat = sectionCategories;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param privacyPolicy {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder privacyPolicy(boolean privacyPolicy) {
        app.privacypolicy = privacyPolicy ? 1 : 0;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param version {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public AndroidAppBuilder version(String version) {
        app.ver = version;
        return this;
    }
}