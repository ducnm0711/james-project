/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package org.apache.james.jwt;

import java.security.PublicKey;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class PublicKeyProvider {

    private final JwtConfiguration jwtConfiguration;
    private final PublicKeyReader reader;

    public PublicKeyProvider(JwtConfiguration jwtConfiguration, PublicKeyReader reader) {
        this.jwtConfiguration = jwtConfiguration;
        this.reader = reader;
    }

    public List<PublicKey> get() throws MissingOrInvalidKeyException {
        ImmutableList<PublicKey> keys = jwtConfiguration.getJwtPublicKeyPem()
            .stream()
            .flatMap(s -> reader.fromPEM(s).stream())
            .collect(ImmutableList.toImmutableList());
        if (keys.size() != jwtConfiguration.getJwtPublicKeyPem().size()) {
            throw new MissingOrInvalidKeyException();
        }
        return keys;
    }

}
