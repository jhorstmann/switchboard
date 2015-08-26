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

final class QueuedError<T> implements Deliverable<T> {

    private final T event;
    private final DeliveryMode deliveryMode;
    private final RuntimeException exception;

    QueuedError(T event, DeliveryMode deliveryMode, RuntimeException exception) {
        this.event = event;
        this.deliveryMode = deliveryMode;
        this.exception = exception;
    }

    @Override
    public void sendTo(Switchboard board) {
        board.fail(event, deliveryMode, exception);
    }

    @Override
    public void deliverTo(Collection<? super T> target) {
        throw exception;
    }

    @Override
    public T getEvent() {
        return event;
    }

    @Override
    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

}