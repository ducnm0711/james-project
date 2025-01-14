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

package org.apache.james.jmap.cassandra.upload;

import java.time.Duration;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class UploadConfiguration {
    public static UploadConfiguration SINGLETON = new UploadConfiguration(Duration.ofDays(7));

    private final Duration uploadTtlDuration;

    public UploadConfiguration(Duration uploadTtlDuration) {
        this.uploadTtlDuration = uploadTtlDuration;
    }

    public Duration getUploadTtlDuration() {
        return uploadTtlDuration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UploadConfiguration) {
            UploadConfiguration other = (UploadConfiguration) obj;
            return Objects.equal(uploadTtlDuration, other.uploadTtlDuration);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uploadTtlDuration);
    }

    @Override
    public String toString() {
        return MoreObjects
            .toStringHelper(this)
            .add("uploadTtlDuration", uploadTtlDuration)
            .toString();
    }
}
