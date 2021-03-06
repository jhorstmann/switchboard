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

import java.util.Collection;

final class Message<T> implements Deliverable<T> {

    private final T message;
    private final DeliveryMode deliveryMode;

    Message(final T message, final DeliveryMode deliveryMode) {
        this.message = message;
        this.deliveryMode = deliveryMode;
    }

    @Override
    public void deliverTo(final Collection<? super T> target) {
        target.add(message);
    }

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }
    
}
