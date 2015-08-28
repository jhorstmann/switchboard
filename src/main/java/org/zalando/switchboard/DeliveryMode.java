package org.zalando.switchboard;

/*
 * ⁣​
 * Switchboard
 * ⁣⁣
 * Copyright (C) 2015 Zalando SE
 * ⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ​⁣
 */

import java.util.List;

import static com.google.common.base.Preconditions.checkState;

// TODO should not be an enum, but would require to reveal Delivery (for the API)
public enum DeliveryMode {

    DIRECT {

        @Override
        <S, T> List<Delivery<S, T, ?>> distribute(final List<Delivery<S, T, ?>> deliveries) {
            checkState(deliveries.size() == 1, "Too many subscriptions for event, expected one");
            return deliveries;
        }

    },

    FIRST {

        @Override
        <S, T> List<Delivery<S, T, ?>> distribute(final List<Delivery<S, T, ?>> deliveries) {
            return deliveries.subList(0, 1);
        }

    },

    BROADCAST {

        @Override
        <S, T> List<Delivery<S, T, ?>> distribute(final List<Delivery<S, T, ?>> deliveries) {
            return deliveries;
        }

    };

    // TODO is the order of given subscriptions defined?
    abstract <S, T> List<Delivery<S, T, ?>> distribute(List<Delivery<S, T, ?>> deliveries);

}
