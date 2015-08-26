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

import org.junit.Test;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.hasFeature;
import static org.junit.Assert.assertThat;

public final class SubscriptionTest {

    @Test
    public void shouldExtractTypeFromGenericTypeArgument() {
        assertThat(new GenericlyTypedSubscription().getEventType(), is(equalTo(String.class)));
    }

    @Test
    public void shouldExtractObjectFromRawTypeArgument() {
        assertThat(new RawTypedSubscription().getEventType(), is(equalTo(Object.class)));
    }

    @Test
    public void shouldHaveNoHintByDefault() {
        final Subscription<String, Object> unit = "foo"::equals;

        assertThat(unit, hasFeature("hint", Subscription::getHint, is(empty())));
    }

}
