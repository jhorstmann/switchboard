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

import org.junit.gen5.api.Test;
import org.zalando.switchboard.contracts.AtLeastContract;
import org.zalando.switchboard.contracts.AtLeastOnceContract;
import org.zalando.switchboard.contracts.AtMostContract;
import org.zalando.switchboard.contracts.ExactlyOnceContract;
import org.zalando.switchboard.contracts.FailContract;
import org.zalando.switchboard.contracts.FutureContract;
import org.zalando.switchboard.contracts.InspectContract;
import org.zalando.switchboard.contracts.NeverContract;
import org.zalando.switchboard.contracts.RecordContract;
import org.zalando.switchboard.contracts.SubscribeContract;
import org.zalando.switchboard.contracts.TimeoutContract;
import org.zalando.switchboard.contracts.TimesContract;
import org.zalando.switchboard.contracts.UnsubscribeContract;
import org.zalando.switchboard.model.Message;
import org.zalando.switchboard.traits.DirectDeliveryTrait;
import org.zalando.switchboard.traits.MessageSubscriptionTrait;

import static org.junit.gen5.api.Assertions.expectThrows;
import static org.zalando.switchboard.Deliverable.message;
import static org.zalando.switchboard.SubscriptionMode.exactlyOnce;

public final class DirectDeliveryTest implements DirectDeliveryTrait, MessageSubscriptionTrait,
        AtLeastContract<Message>,
        AtLeastOnceContract<Message>,
        AtMostContract<Message>,
        ExactlyOnceContract<Message>,
        FailContract<Message>,
        FutureContract<Message>,
        InspectContract<Message>,
        NeverContract<Message>,
        RecordContract<Message>,
        SubscribeContract<Message>,
        TimeoutContract<Message>,
        TimesContract<Message>,
        UnsubscribeContract<Message> {

    private final Switchboard unit = Switchboard.create();

    @Test // TODO (timeout = TestTimeout.DEFAULT)
    public void shouldThrowWhenDeliveringMessagesToSubscriptions() {
        unit.subscribe(matchA(), exactlyOnce());
        unit.subscribe(matchA(), exactlyOnce());

        expectThrows(IllegalStateException.class, () -> {
            unit.send(message(messageA(), deliveryMode()));
        });
    }

}
