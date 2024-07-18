// Copyright Amazon.com Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0
package com.amazon.corretto.crypto.provider;

import java.security.interfaces.EdECKey;
import java.security.spec.EdDSAParameterSpec;

abstract class EvpEdEcKey extends EvpKey implements EdECKey {
    // private static final long serialVersionUID = 1;

    protected volatile ECParameterSpec params;

    EvpEdEcKey(final InternalKey key, final boolean isPublicKey) {
        super(key, EvpKeyType.Ed25519, isPublicKey);
    }

    @Override
    public EdDSAParameterSpec getParams() {

    }
}
